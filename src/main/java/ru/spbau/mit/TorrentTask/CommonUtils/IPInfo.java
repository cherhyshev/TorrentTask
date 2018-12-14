package ru.spbau.mit.TorrentTask.CommonUtils;

public final class IPInfo {
    public IPInfo(byte byte1, byte byte2, byte byte3, byte byte4) {
        this.byte1 = byte1;
        this.byte2 = byte2;
        this.byte3 = byte3;
        this.byte4 = byte4;
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

    private final byte byte1;
    private final byte byte2;
    private final byte byte3;
    private final byte byte4;
}
