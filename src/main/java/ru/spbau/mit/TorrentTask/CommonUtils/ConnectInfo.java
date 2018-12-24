package ru.spbau.mit.TorrentTask.CommonUtils;

import java.util.Objects;

public final class ConnectInfo {

    private final IPInfo ipInfo;
    private final short portInfo;

    public ConnectInfo(IPInfo ipInfo, short portInfo) {
        this.ipInfo = ipInfo;
        this.portInfo = portInfo;
    }

    static ConnectInfo fromString(String string) {
        String[] arr = string.split(" ");
        return new ConnectInfo(IPInfo.fromString(arr[0]), Short.parseShort(arr[1]));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ConnectInfo) {
            ConnectInfo that = (ConnectInfo) o;
            return this.ipInfo.equals(that.ipInfo)
                    && this.portInfo == that.portInfo;

        }
        return false;
    }

    @Override
    public String toString() {
        return ipInfo.toString() + " " + portInfo;
    }

    public IPInfo getIpInfo() {
        return ipInfo;
    }

    public short getPortInfo() {
        return portInfo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(portInfo, ipInfo);
    }
}
