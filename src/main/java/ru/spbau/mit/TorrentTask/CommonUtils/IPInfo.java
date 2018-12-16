package ru.spbau.mit.TorrentTask.CommonUtils;

import java.net.InetAddress;
import java.util.Objects;

public final class IPInfo {
    private final byte byte1;
    private final byte byte2;
    private final byte byte3;
    private final byte byte4;

    public IPInfo(byte byte1, byte byte2, byte byte3, byte byte4) {
        this.byte1 = byte1;
        this.byte2 = byte2;
        this.byte3 = byte3;
        this.byte4 = byte4;
    }

    public IPInfo(InetAddress inetAddress) {
        byte[] bytes = inetAddress.getAddress();
        byte1 = bytes[0];
        byte2 = bytes[1];
        byte3 = bytes[2];
        byte4 = bytes[3];
    }

    public static IPInfo fromString(String str) {
        String[] vals = str.split("\\.");
        return new IPInfo((byte) Integer.parseInt(vals[0]), (byte) Integer.parseInt(vals[1]),
                (byte) Integer.parseInt(vals[2]), (byte) Integer.parseInt(vals[3]));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IPInfo) {
            IPInfo that = (IPInfo) o;
            return this.byte1 == that.byte1
                    && this.byte2 == that.byte2
                    && this.byte3 == that.byte3
                    && this.byte4 == that.byte4;

        }
        return false;
    }

    @Override
    public String toString() {
        return "" + byte1 + "." + byte2 + "." + byte3 + "." + byte4;
    }

    public byte getByte1() {
        return byte1;
    }

    public byte getByte2() {
        return byte2;
    }

    public byte getByte3() {
        return byte3;
    }

    public byte getByte4() {
        return byte4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(byte1, byte2, byte3, byte4);
    }
}
