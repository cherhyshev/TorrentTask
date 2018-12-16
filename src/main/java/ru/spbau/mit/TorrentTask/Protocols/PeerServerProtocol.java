package ru.spbau.mit.TorrentTask.Protocols;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.ClientUtils.PartedFile;
import ru.spbau.mit.TorrentTask.Request.GetRequest;
import ru.spbau.mit.TorrentTask.Request.StatRequest;
import ru.spbau.mit.TorrentTask.Response.GetResponse;
import ru.spbau.mit.TorrentTask.Response.StatResponse;

import java.util.Map;

public final class PeerServerProtocol {
    private final Map<Integer, PartedFile> partedFileMap;

    public PeerServerProtocol(Map<Integer, PartedFile> partedFileMap) {
        this.partedFileMap = partedFileMap;
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
                    .getPartByIndex(getRequest.getPart());
            if (part != null) {
                return new GetResponse(part);
            }
        }
        return null;

    }

}
