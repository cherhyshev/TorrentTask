package ru.spbau.mit.TorrentTask.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public final class PeerInfo {
    private static final int FIVE_MINUTES = 5 * 60 * 1000;
    private final ConnectInfo connectInfo;
    private final Map<Integer, Long> peeredFilesIdsWithTime = new HashMap<>();

    public PeerInfo(ConnectInfo connectInfo) {
        this.connectInfo = connectInfo;
    }

    public boolean isPeering(int idx) {
        return peeredFilesIdsWithTime.containsKey(idx);
    }

    public static PeerInfo fromString(String str) {
        String[] strings = str.split("\n");
        ConnectInfo connectInfo = ConnectInfo.fromString(strings[0]);
        PeerInfo peerInfo = new PeerInfo(connectInfo);
        for (int i = 1; i < strings.length; i++) {
            String[] s = strings[i].split(" ");
            peerInfo.peeredFilesIdsWithTime.put(Integer.parseInt(s[0]),
                    Long.parseLong(s[1]));
        }
        return peerInfo;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(connectInfo.toString()).append("\n");
        for (int i : peeredFilesIdsWithTime.keySet()) {
            stringBuilder.append(i).append(" ")
                    .append(peeredFilesIdsWithTime.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean updateByID(int idx) {
        peeredFilesIdsWithTime.put(idx, System.currentTimeMillis());
        return true;
    }

    public ConnectInfo getConnectInfo() {
        return connectInfo;
    }

    public void removeNonUpdatedFiles(long curTime) {
        for (Map.Entry<Integer, Long> entry : peeredFilesIdsWithTime.entrySet()) {
            if ((curTime - entry.getValue()) >= FIVE_MINUTES) {
                peeredFilesIdsWithTime.remove(entry.getKey());
            }
        }
    }
}
