package ru.spbau.mit.TorrentTask.Request;

import java.util.Objects;

public final class StatRequest implements AbstractRequest {
    public static final byte ID = 1;
    private final int fileId;

    public StatRequest(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatRequest that = (StatRequest) o;
        return fileId == that.fileId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId);
    }
}
