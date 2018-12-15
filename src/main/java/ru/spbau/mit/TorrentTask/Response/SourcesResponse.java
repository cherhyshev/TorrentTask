package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;

public final class SourcesResponse implements AbstractResponse {

    private final ConnectInfo[] connectInfos;

    public SourcesResponse(int size, @Nullable ConnectInfo[] connectInfos) {
        this.size = size;
        this.connectInfos = connectInfos;
    }

    public int getSize() {
        return size;
    }

    private final int size;

    public ConnectInfo[] getConnectInfos() {
        return connectInfos;
    }
}
