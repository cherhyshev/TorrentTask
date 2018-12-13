package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class UpdateRequest extends AbstractRequest {
    UpdateRequest(@NotNull byte id, @NotNull short clientPort, @NotNull int count, @Nullable int[] fileIDs) {
        super(id);
        this.clientPort = clientPort;
        this.count = count;
        this.fileIDs = fileIDs;
    }

    public short getClientPort() {
        return clientPort;
    }

    public int getCount() {
        return count;
    }

    public int[] getFileIDs() {
        return fileIDs;
    }

    private final short clientPort;
    private final int count;
    private final int[] fileIDs;
}
