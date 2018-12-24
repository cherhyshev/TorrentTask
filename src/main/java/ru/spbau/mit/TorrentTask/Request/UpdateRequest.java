package ru.spbau.mit.TorrentTask.Request;

import java.util.Arrays;
import java.util.Objects;

public final class UpdateRequest implements AbstractRequest {
    public static final byte ID = 4;
    private final short clientPort;
    private final int count;
    private final int[] fileIDs;

    public UpdateRequest(short clientPort, int count, int[] fileIDs) {
        this.clientPort = clientPort;
        this.count = count;
        this.fileIDs = fileIDs;
    }

    public short getClientPort() {
        return clientPort;
    }

    public int getCount() {
        return count;
    }

    public int[] getFileIDs() {
        return fileIDs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRequest that = (UpdateRequest) o;
        return clientPort == that.clientPort &&
                count == that.count &&
                Arrays.equals(fileIDs, that.fileIDs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(clientPort, count);
        result = 31 * result + Arrays.hashCode(fileIDs);
        return result;
    }
}
