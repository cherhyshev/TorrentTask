package ru.spbau.mit.TorrentTask.Utils;

public class FileInfo {

    public FileInfo(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
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
