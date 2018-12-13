package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;

public final class StatRequest extends AbstractRequest {
    public StatRequest(@NotNull byte id, @NotNull int fileId) {
        super(id);
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    private final int fileId;
}
