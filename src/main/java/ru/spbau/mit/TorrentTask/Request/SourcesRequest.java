package ru.spbau.mit.TorrentTask.Request;

public final class SourcesRequest extends AbstractRequest {
    public SourcesRequest(byte id, int fileId) {
        super(id);
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    private final int fileId;
}
