package ru.spbau.mit.TorrentTask.Protocols;

import ru.spbau.mit.TorrentTask.ClientUtils.PartedFile;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;
import ru.spbau.mit.TorrentTask.Request.SourcesRequest;
import ru.spbau.mit.TorrentTask.Request.UploadRequest;
import ru.spbau.mit.TorrentTask.Response.ListResponse;
import ru.spbau.mit.TorrentTask.Response.SourcesResponse;
import ru.spbau.mit.TorrentTask.Response.UpdateResponse;
import ru.spbau.mit.TorrentTask.Response.UploadResponse;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;


public class TrackerClientProtocol {
    private final Map<Integer, PartedFile> partedFileMap;
    private final Map<Integer, FileInfo> knownIndexedFiles;
    private final Map<Integer, ArrayList<ConnectInfo>> knownPeersInfo;


    public TrackerClientProtocol(Map<Integer, PartedFile> partedFileMap,
                                 Map<Integer, FileInfo> knownIndexedFiles,
                                 Map<Integer, ArrayList<ConnectInfo>> knownPeersInfo) {
        this.partedFileMap = partedFileMap;
        this.knownIndexedFiles = knownIndexedFiles;
        this.knownPeersInfo = knownPeersInfo;
    }


    // Узнаем о файлах имена и размер - кладем в мапу известных FileInfo
    // (используется для корректной обработки GetResponse в PeerClientProtocol
    public void processListResponse(ListResponse listResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total count of files: ")
                .append(listResponse.getCount()).append("\n");

        for (IdentifiedFileInfo info : listResponse.getIdentifiedFileInfos()) {
            // Узнаем о файлах имена и размер - кладем в мапу с ID
            knownIndexedFiles.put(info.getId(), info.getFileInfo());

            stringBuilder.append("File with ID ").append(info.getId())
                    .append(" has name ").append(info.getFileInfo().getFileName())
                    .append(" and size ").append(info.getFileInfo().getSize()).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    // Выводим в консоль резулььтат работы,
    // помещаем в мапу с полученным индексом PartedFile из файла в request
    public void processUploadResponse(UploadRequest request, UploadResponse response) {
        int idx = response.getId();
        partedFileMap.put(idx, new PartedFile(Paths.get(request.getFileInfo().getFileName()).toFile()));
        System.out.println("Uploaded file id: " + idx + "\n");
    }


    // обновляем мапу из ID и данных о пирах, раздающих этот файл
    // выводим в консоль результат запроса
    public void processSourcesResponse(SourcesRequest sourcesRequest, SourcesResponse sourcesResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total count of peers: ")
                .append(sourcesResponse.getSize()).append("\n");
        for (ConnectInfo info : sourcesResponse.getConnectInfos()) {
            if (knownPeersInfo.containsKey(sourcesRequest.getFileId())) {
                knownPeersInfo.get(sourcesRequest.getFileId()).add(info);
            } else {
                ArrayList<ConnectInfo> infoArrayList = new ArrayList<>();
                infoArrayList.add(info);
                knownPeersInfo.put(sourcesRequest.getFileId(), infoArrayList);
            }
            stringBuilder.append(info.getIpInfo().toString()).append(" ")
                    .append(info.getPortInfo()).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    // Выводим в консоль и возвращаем результат запроса
    // TorrentClient вроде ничего не делает
    public void processUpdateResponse(UpdateResponse updateResponse) {
        System.out.println("File updating " + (updateResponse.isUpdated() ? "completed successfully" : "failed"));
    }

}
