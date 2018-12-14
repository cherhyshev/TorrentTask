package ru.spbau.mit.TorrentTask.Protocols;

import ru.spbau.mit.TorrentTask.CommonUtils.*;
import ru.spbau.mit.TorrentTask.Request.SourcesRequest;
import ru.spbau.mit.TorrentTask.Request.UpdateRequest;
import ru.spbau.mit.TorrentTask.Request.UploadRequest;
import ru.spbau.mit.TorrentTask.Response.ListResponse;
import ru.spbau.mit.TorrentTask.Response.SourcesResponse;
import ru.spbau.mit.TorrentTask.Response.UpdateResponse;
import ru.spbau.mit.TorrentTask.Response.UploadResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackerProtocol {
    private Map<Integer, FileInfo> filesMap = new HashMap<>();
    private List<PeerInfo> peerInfos = new ArrayList<>();

    public ListResponse executeListRequest() {
        int count = filesMap.size();
        IdentifiedFileInfo[] identifiedFileInfos = new IdentifiedFileInfo[count];
        for (int i = 0; i < count; i++) {
            identifiedFileInfos[i] = new IdentifiedFileInfo(i, filesMap.get(i));
        }
        return new ListResponse(count, identifiedFileInfos);
    }

    public UploadResponse executeUploadRequest(UploadRequest uploadRequest) {
        FileInfo fileInfo = uploadRequest.getFileInfo();
        int idx = filesMap.size();
        filesMap.put(idx, fileInfo);
        return new UploadResponse(idx);
    }

    public SourcesResponse executeSourcesRequest(SourcesRequest sourcesRequest) {
        int idx = sourcesRequest.getFileId();
        List<ClientInfo> clientInfoList = new ArrayList<>();
        for (PeerInfo peerInfo : peerInfos) {
            if (peerInfo.isPeering(idx)) {
                clientInfoList.add(peerInfo.getClientInfo());
            }
        }
        return new SourcesResponse(clientInfoList.size(),
                (ClientInfo[]) clientInfoList.toArray());

    }

    // Я хотел для консистентности избежать передачи сюда IP, но он берется из сокета,
    // поэтому пришлось чуток испортить внешний вид
    public UpdateResponse executeUpdateRequest(IPInfo ipInfo, UpdateRequest updateRequest) {
        PeerInfo targetPeer = null;
        boolean result = false;
        for (PeerInfo peerInfo : peerInfos) {
            if (peerInfo.getClientInfo().getIpAddress().equals(ipInfo)) {
                result = true;
                targetPeer = peerInfo;
                for (int idx : updateRequest.getFileIDs()) {
                    result = result && peerInfo.updateByID(idx);
                }
            }
        }
        if (targetPeer == null) {
            targetPeer = new PeerInfo(new ClientInfo(ipInfo, updateRequest.getClientPort()));
            result = true;
            for (int idx : updateRequest.getFileIDs()) {
                result = result && targetPeer.updateByID(idx);
            }
            peerInfos.add(targetPeer);
        }
        return new UpdateResponse(result);
    }

    public void checkActivePeers() {
        long curTime = System.currentTimeMillis();
        for (PeerInfo peerInfo : peerInfos) {
            peerInfo.removeNonUpdatedFiles(curTime);
        }
    }
}
