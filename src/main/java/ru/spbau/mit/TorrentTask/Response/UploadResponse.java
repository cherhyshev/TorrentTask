package ru.spbau.mit.TorrentTask.Response;

import java.util.Objects;

public final class UploadResponse implements AbstractResponse {
    private final int id;

    public UploadResponse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadResponse response = (UploadResponse) o;
        return id == response.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
