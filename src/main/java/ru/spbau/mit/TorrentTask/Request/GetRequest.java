package ru.spbau.mit.TorrentTask.Request;

public final class GetRequest extends AbstractRequest {
    public GetRequest(byte id, int fileId, int part) {
        super(id);
        this.fileId = fileId;
        this.part = part;
    }

    public int getFileId() {
        return fileId;
    }

    public int getPart() {
        return part;
    }

    private final int fileId;
    private final int part;
}
