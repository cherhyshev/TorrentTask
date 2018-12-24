package ru.spbau.mit.TorrentTask.Request;

import java.util.Objects;

public final class GetRequest implements AbstractRequest {
    public static final byte ID = 2;
    private final int fileId;
    private final int part;

    public GetRequest(int fileId, int part) {
        this.fileId = fileId;
        this.part = part;
    }

    public int getFileId() {
        return fileId;
    }

    public int getPart() {
        return part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetRequest that = (GetRequest) o;
        return fileId == that.fileId &&
                part == that.part;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, part);
    }
}
