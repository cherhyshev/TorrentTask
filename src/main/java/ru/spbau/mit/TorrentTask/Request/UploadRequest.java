package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;

public final class UploadRequest extends AbstractRequest {
    public UploadRequest(byte id, @NotNull FileInfo fileInfo) {
        super(id);
        this.fileInfo = fileInfo;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    private final FileInfo fileInfo;
}
