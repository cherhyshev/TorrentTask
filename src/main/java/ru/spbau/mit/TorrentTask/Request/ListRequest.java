package ru.spbau.mit.TorrentTask.Request;

import org.jetbrains.annotations.NotNull;

public final class ListRequest extends AbstractRequest {

    public ListRequest(@NotNull byte id) {
        super(id);
    }
}
