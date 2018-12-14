package ru.spbau.mit.TorrentTask.CommonUtils;

public class FileInfo {

    public FileInfo(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
    }

    FileInfo(FileInfo fileInfo) {
        fileName = fileInfo.getFileName();
        size = fileInfo.getSize();
    }

    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }

    private final String fileName;
    private final long size;
}
