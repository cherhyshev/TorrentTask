package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StatResponse implements AbstractResponse {

    public StatResponse(@NotNull int count, @Nullable int[] parts) {
        this.count = count;
        this.parts = parts;
    }

    public int[] getParts() {
        return parts;
    }

    public int getCount() {
        return count;
    }

    private final int count;
    private final int[] parts;
}
