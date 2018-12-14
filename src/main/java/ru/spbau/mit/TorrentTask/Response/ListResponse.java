package ru.spbau.mit.TorrentTask.Response;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;

public final class ListResponse implements AbstractResponse {

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

    private final int count;
    private final IdentifiedFileInfo[] identifiedFileInfos;
}
