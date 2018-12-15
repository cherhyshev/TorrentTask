package ru.spbau.mit.TorrentTask.Protocols;

import ru.spbau.mit.TorrentTask.ClientUtils.PartedFile;
import ru.spbau.mit.TorrentTask.Response.GetResponse;
import ru.spbau.mit.TorrentTask.Response.StatResponse;

import java.util.HashMap;

public class SeedProtocol {
    private final HashMap<Integer, PartedFile> partedFileMap;

    public SeedProtocol(HashMap<Integer, PartedFile> partedFileMap) {
        this.partedFileMap = partedFileMap;
    }

    // Просто выводим в консоль результат запроса
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
        partedFileMap.get(fileId).updatePartByIndex(partNum, getResponse.getContent());
    }

}
