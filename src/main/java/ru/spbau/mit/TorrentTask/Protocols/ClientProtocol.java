package ru.spbau.mit.TorrentTask.Protocols;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.ClientUtils.PartedFile;
import ru.spbau.mit.TorrentTask.CommonUtils.ClientInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;
import ru.spbau.mit.TorrentTask.Request.GetRequest;
import ru.spbau.mit.TorrentTask.Request.StatRequest;
import ru.spbau.mit.TorrentTask.Response.*;

import java.util.HashMap;
import java.util.Map;

public class ClientProtocol {
    private final Map<Integer, PartedFile> partedFileMap = new HashMap<>();

    // Просто выводим в консоль результат запроса
    public static void processListResponse(ListResponse listResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total count of files: ")
                .append(listResponse.getCount()).append("\n");

        for (IdentifiedFileInfo info : listResponse.getIdentifiedFileInfos()) {
            stringBuilder.append(info.getId()).append(" ")
                    .append(info.getFileName()).append(" ")
                    .append(info.getSize()).append("\n");
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
        for (ClientInfo info : sourcesResponse.getClientInfos()) {
            stringBuilder.append(info.getIpAddress().toString()).append(" ")
                    .append(info.getClientPort()).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    // Выводим в консоль и возвращаем результат запроса
    public static boolean processUpdateResponse(UpdateResponse updateResponse) {
        boolean result = updateResponse.isUpdated();
        System.out.println("File updating " + (result ? "completed successfully" : "failed"));
        return result;
    }

    // Просто выводим в консоль результат запроса
    public static void processStatResponse(StatResponse statResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total available parts: ")
                .append(statResponse.getCount()).append("\n");
        for (int part : statResponse.getParts()) {
            stringBuilder.append(part).append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

    public @Nullable StatResponse executeStatRequest(@Nullable StatRequest statRequest) {
        if (statRequest != null && partedFileMap.containsKey(statRequest.getFileId())) {
            int[] readyParts = partedFileMap.get(statRequest.getFileId()).getReadyParts();
            if (readyParts != null) {
                return new StatResponse(readyParts.length, readyParts);
            }
        }
        return null;
    }

    public @Nullable GetResponse executeGetRequest(@Nullable GetRequest getRequest) {
        if (getRequest != null && partedFileMap.containsKey(getRequest.getFileId())) {
            byte[] part = partedFileMap.get(getRequest.getFileId())
                    .getPartbyIndex(getRequest.getPart());
            if (part != null) {
                return new GetResponse(part);
            }
        }
        return null;

    }

}
