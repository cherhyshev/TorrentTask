package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.NotNull;

public final class UploadResponse implements AbstractResponse {
    public UploadResponse(@NotNull int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private final int id;
}
