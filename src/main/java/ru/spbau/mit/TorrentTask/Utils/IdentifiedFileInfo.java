package ru.spbau.mit.TorrentTask.Utils;

public final class IdentifiedFileInfo extends FileInfo {
    public IdentifiedFileInfo(int id, String fileName, long size) {
        super(fileName, size);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private final int id;
}
