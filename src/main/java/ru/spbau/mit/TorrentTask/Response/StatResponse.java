package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

public final class StatResponse implements AbstractResponse {

    private final int count;
    private final int[] parts;

    public StatResponse(int count, @Nullable int[] parts) {
        this.count = count;
        this.parts = parts;
        if (parts != null) {
            Arrays.sort(parts);
        }
    }

    public int[] getParts() {
        return parts;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatResponse that = (StatResponse) o;
        return count == that.count &&
                Arrays.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(count);
        result = 31 * result + Arrays.hashCode(parts);
        return result;
    }
}
