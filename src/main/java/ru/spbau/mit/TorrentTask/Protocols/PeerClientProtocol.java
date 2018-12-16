package ru.spbau.mit.TorrentTask.Protocols;

import ru.spbau.mit.TorrentTask.ClientUtils.PartedFile;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;
import ru.spbau.mit.TorrentTask.Response.GetResponse;
import ru.spbau.mit.TorrentTask.Response.StatResponse;

import java.util.ArrayList;
import java.util.Map;

public final class PeerClientProtocol {
    private final Map<Integer, PartedFile> partedFileMap;
    private final Map<Integer, ArrayList<ConnectInfo>> knownPeersInfo;

    public PeerClientProtocol(Map<Integer, PartedFile> partedFileMap, Map<Integer, ArrayList<ConnectInfo>> knownPeersInfo) {
        this.partedFileMap = partedFileMap;
        this.knownPeersInfo = knownPeersInfo;
    }

    //
    // Выводим в консоль результат запроса
    public void processStatResponse(StatResponse statResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total available parts: ")
                .append(statResponse.getCount()).append("\n");
        stringBuilder.append("Available parts numbers:\n");
        for (int part : statResponse.getParts()) {
            stringBuilder.append(part).append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

    public void processGetResponse(int fileId, int partNum, GetResponse getResponse) {
        if (partedFileMap.keySet().contains(fileId)) {
            partedFileMap.get(fileId).updatePartByIndex(partNum, getResponse.getContent());
        } else {

        }
    }

}
