package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;

public final class SourcesRequest extends AbstractRequest {
    SourcesRequest(@NotNull byte id, int fileId) {
        super(id);
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    private final int fileId;
}
