package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractRequest {
    AbstractRequest(@NotNull byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    private final byte id;
}
