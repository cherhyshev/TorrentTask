package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class GetResponse implements AbstractResponse {

    private final byte[] content;

    public GetResponse(@NotNull byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetResponse that = (GetResponse) o;
        return Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(content);
    }
}
