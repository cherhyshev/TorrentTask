package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;

import java.util.Arrays;
import java.util.Objects;

public final class ListResponse implements AbstractResponse {

    private final int count;
    private final IdentifiedFileInfo[] identifiedFileInfos;

    public ListResponse(int count, @Nullable IdentifiedFileInfo[] identifiedFileInfos) {
        this.count = count;
        this.identifiedFileInfos = identifiedFileInfos;
    }

    public int getCount() {
        return count;
    }

    public IdentifiedFileInfo[] getIdentifiedFileInfos() {
        return identifiedFileInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListResponse response = (ListResponse) o;
        return count == response.count &&
                Arrays.equals(identifiedFileInfos, response.identifiedFileInfos);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(count);
        result = 31 * result + Arrays.hashCode(identifiedFileInfos);
        return result;
    }
}
