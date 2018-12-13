package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.NotNull;

public final class UpdateResponse implements AbstractResponse {

    public UpdateResponse(@NotNull boolean updated) {
        this.updated = updated;
    }

    public boolean isUpdated() {
        return updated;
    }

    private final boolean updated;
}
