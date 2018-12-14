package ru.spbau.mit.TorrentTask.Request;

public final class UpdateRequest extends AbstractRequest {
    public UpdateRequest(byte id, short clientPort, int count, int[] fileIDs) {
        super(id);
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

    private final short clientPort;
    private final int count;
    private final int[] fileIDs;
}
