package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;

import java.util.Arrays;
import java.util.Objects;

public final class SourcesResponse implements AbstractResponse {

    private final ConnectInfo[] connectInfos;
    private final int size;

    public SourcesResponse(int size, @Nullable ConnectInfo[] connectInfos) {
        this.size = size;
        this.connectInfos = connectInfos;
    }

    public int getSize() {
        return size;
    }

    public ConnectInfo[] getConnectInfos() {
        return connectInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourcesResponse that = (SourcesResponse) o;
        return size == that.size &&
                Arrays.equals(connectInfos, that.connectInfos);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(connectInfos);
        return result;
    }
}
