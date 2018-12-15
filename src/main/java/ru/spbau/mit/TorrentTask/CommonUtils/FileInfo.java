package ru.spbau.mit.TorrentTask.CommonUtils;

public final class FileInfo {

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

    public String toString() {
        return fileName.replace(" ", "%20") + " " + size;
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
