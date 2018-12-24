package ru.spbau.mit.TorrentTask.Response;

import java.util.Objects;

public final class UpdateResponse implements AbstractResponse {

    private final boolean updated;

    public UpdateResponse(boolean updated) {
        this.updated = updated;
    }

    public boolean isUpdated() {
        return updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateResponse that = (UpdateResponse) o;
        return updated == that.updated;
    }

    @Override
    public int hashCode() {
        return Objects.hash(updated);
    }
}
