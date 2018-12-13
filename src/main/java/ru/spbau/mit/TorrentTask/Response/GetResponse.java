package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.NotNull;

public final class GetResponse implements AbstractResponse {

    public GetResponse(@NotNull byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    private final byte[] content;
}
