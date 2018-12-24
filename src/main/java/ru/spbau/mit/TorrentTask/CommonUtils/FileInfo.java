package ru.spbau.mit.TorrentTask.CommonUtils;

import java.util.Objects;

public final class FileInfo {

    private final String fileName;
    private final long size;

    public FileInfo(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
    }

    FileInfo(FileInfo fileInfo) {
        fileName = fileInfo.getFileName();
        size = fileInfo.getSize();
    }

    public static FileInfo fromString(String string) {
        String[] strs = string.split(" ");
        return new FileInfo(strs[0].replace("%20", " "),
                Long.parseLong(strs[1]));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FileInfo) {
            FileInfo other = (FileInfo) o;
            return fileName.equals(other.fileName) && size == other.size;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, size);
    }

    public String toString() {
        return fileName.replace(" ", "%20") + " " + size;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }
}
