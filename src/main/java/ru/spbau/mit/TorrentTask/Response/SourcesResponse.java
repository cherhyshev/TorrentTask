package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.ClientInfo;

public final class SourcesResponse implements AbstractResponse {

    public SourcesResponse(int size, @Nullable ClientInfo[] clientInfos) {
        this.size = size;
        this.clientInfos = clientInfos;
    }

    public ClientInfo[] getClientInfos() {
        return clientInfos;
    }

    public int getSize() {
        return size;
    }

    private final int size;
    private final ClientInfo[] clientInfos;
}
