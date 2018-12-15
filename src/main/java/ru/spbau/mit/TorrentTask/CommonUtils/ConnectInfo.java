package ru.spbau.mit.TorrentTask.CommonUtils;

public final class ConnectInfo {

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
    public String toString() {
        return ipInfo.toString() + " " + portInfo;
        }

    public IPInfo getIpInfo() {
            return ipInfo;
        }

        private final IPInfo ipInfo;

    public short getPortInfo() {
        return portInfo;
    }
    }
