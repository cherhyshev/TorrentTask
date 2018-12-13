package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Utils.ClientInfo;

public final class SourcesResponce implements AbstractResponse {

    public SourcesResponce(@NotNull int size, @Nullable ClientInfo[] clientInfos) {
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
