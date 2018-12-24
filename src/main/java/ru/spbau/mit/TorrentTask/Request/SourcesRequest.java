package ru.spbau.mit.TorrentTask.Request;

import java.util.Objects;

public final class SourcesRequest implements AbstractRequest {
    public static final byte ID = 3;
    private final int fileId;

    public SourcesRequest(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourcesRequest that = (SourcesRequest) o;
        return fileId == that.fileId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId);
    }
}
