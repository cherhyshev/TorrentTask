package ru.spbau.mit.TorrentTask.CommonUtils;

public final class IdentifiedFileInfo {
    private final FileInfo fileInfo;

    public IdentifiedFileInfo(int id, String fileName, long size) {
        fileInfo = new FileInfo(fileName, size);
        this.id = id;
    }

    public IdentifiedFileInfo(int id, FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        this.id = id;
    }

    public static IdentifiedFileInfo fromString(String str) {
        String[] s = str.split(" ");
        return new IdentifiedFileInfo(Integer.parseInt(s[0]), FileInfo.fromString(s[1] + " " + s[2]));
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "" + id + " " + fileInfo.toString();
    }

    private final int id;

    public FileInfo getFileInfo() {
        return fileInfo;
    }
}
