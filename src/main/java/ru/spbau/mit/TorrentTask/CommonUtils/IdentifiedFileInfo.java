package ru.spbau.mit.TorrentTask.CommonUtils;

import java.util.Objects;

public final class IdentifiedFileInfo {
    private final FileInfo fileInfo;
    private final int id;

    public IdentifiedFileInfo(int id, String fileName, long size) {
        this.id = id;
        fileInfo = new FileInfo(fileName, size);
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof IdentifiedFileInfo) {
            IdentifiedFileInfo other = (IdentifiedFileInfo) o;
            return fileInfo.equals(other.fileInfo) && id == other.id;
        }
        return false;
    }



    public FileInfo getFileInfo() {
        return fileInfo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileInfo, id);
    }
}
