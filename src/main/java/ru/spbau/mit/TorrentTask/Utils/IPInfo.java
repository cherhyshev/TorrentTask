package ru.spbau.mit.TorrentTask.Utils;

public final class IPInfo {
    public IPInfo(byte byte1, byte byte2, byte byte3, byte byte4) {
        this.byte1 = byte1;
        this.byte2 = byte2;
        this.byte3 = byte3;
        this.byte4 = byte4;
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
