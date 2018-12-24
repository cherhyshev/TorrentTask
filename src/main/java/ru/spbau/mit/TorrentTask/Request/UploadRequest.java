package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;

public final class UploadRequest implements AbstractRequest {
    public static final byte ID = 2;
    private final FileInfo fileInfo;

    public UploadRequest(@NotNull FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadRequest that = (UploadRequest) o;
        return fileInfo.equals(that.fileInfo);
    }

    @Override
    public int hashCode() {
        return fileInfo.hashCode();
    }
}
