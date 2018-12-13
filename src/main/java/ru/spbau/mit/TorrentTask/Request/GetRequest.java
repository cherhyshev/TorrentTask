package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;

public final class GetRequest extends AbstractRequest {
    GetRequest(@NotNull byte id, @NotNull int fileId, @NotNull int part) {
        super(id);
        this.fileId = fileId;
        this.part = part;
    }

    public int getFileId() {
        return fileId;
    }

    public int getPart() {
        return part;
    }

    private final int fileId;
    private final int part;
}
