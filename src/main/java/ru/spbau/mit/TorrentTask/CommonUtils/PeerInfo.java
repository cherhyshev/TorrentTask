package ru.spbau.mit.TorrentTask.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public final class PeerInfo {
    private static final int FIVE_MINUTES = 5 * 60 * 1000;
    private final ClientInfo clientInfo;
    private final Map<Integer, Long> peeredFilesIdsWithTime = new HashMap<>();

    public PeerInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public boolean isPeering(int idx) {
        return peeredFilesIdsWithTime.containsKey(idx);
    }

    public boolean updateByID(int idx) {
        peeredFilesIdsWithTime.put(idx, System.currentTimeMillis());
        return true;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void removeNonUpdatedFiles(long curTime) {
        for (Map.Entry<Integer, Long> entry : peeredFilesIdsWithTime.entrySet()) {
            if ((curTime - entry.getValue()) >= FIVE_MINUTES) {
                peeredFilesIdsWithTime.remove(entry.getKey());
            }
        }
    }
}
