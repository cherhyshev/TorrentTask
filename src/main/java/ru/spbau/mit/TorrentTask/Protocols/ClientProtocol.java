package ru.spbau.mit.TorrentTask.Protocols;

import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;
import ru.spbau.mit.TorrentTask.Response.ListResponse;
import ru.spbau.mit.TorrentTask.Response.SourcesResponse;
import ru.spbau.mit.TorrentTask.Response.UpdateResponse;
import ru.spbau.mit.TorrentTask.Response.UploadResponse;


public class ClientProtocol {

    // Просто выводим в консоль результат запроса
    public static void processListResponse(ListResponse listResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total count of files: ")
                .append(listResponse.getCount()).append("\n");

        for (IdentifiedFileInfo info : listResponse.getIdentifiedFileInfos()) {
            stringBuilder.append(info.getId()).append(" ")
                    .append(info.getFileInfo().getFileName()).append(" ")
                    .append(info.getFileInfo().getSize()).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    // Выводим в консоль и возвращаем результат запроса
    public static int processUploadResponse(UploadResponse uploadResponse) {
        int idx = uploadResponse.getId();
        System.out.println("Uploaded file id: " + idx);
        return idx;
    }

    // Просто выводим в консоль результат запроса
    public static void processSourcesResponse(SourcesResponse sourcesResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total count of peers: ")
                .append(sourcesResponse.getSize()).append("\n");
        for (ConnectInfo info : sourcesResponse.getConnectInfos()) {
            stringBuilder.append(info.getIpInfo().toString()).append(" ")
                    .append(info.getPortInfo()).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    // Выводим в консоль и возвращаем результат запроса
    public static boolean processUpdateResponse(UpdateResponse updateResponse) {
        boolean result = updateResponse.isUpdated();
        System.out.println("File updating " + (result ? "completed successfully" : "failed"));
        return result;
    }

}
