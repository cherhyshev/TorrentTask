package ru.spbau.mit.TorrentTask.Utils;

public final class ClientInfo {

        public ClientInfo(IPInfo ipInfo, short clientPort) {
            this.ipInfo = ipInfo;
            this.clientPort = clientPort;
        }

        public IPInfo getIpAddress() {
            return ipInfo;
        }

        public short getClientPort() {
            return clientPort;
        }

        private final IPInfo ipInfo;
        private final short clientPort;
    }
