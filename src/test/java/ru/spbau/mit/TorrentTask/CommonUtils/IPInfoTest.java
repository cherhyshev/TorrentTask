package ru.spbau.mit.TorrentTask.CommonUtils;

import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class IPInfoTest {
    private IPInfo ipInfo1;
    private IPInfo ipInfo2;

    @Before
    public void setUp() {
        ipInfo1 = new IPInfo((byte) 127, (byte) 0, (byte) 0, (byte) 1);
        ipInfo2 = new IPInfo(InetAddress.getLoopbackAddress());
    }

    @Test
    public void equals() {
        assertEquals(ipInfo1, ipInfo2);
    }

    @Test
    public void fromString() {
        IPInfo ipInfo3 = IPInfo.fromString("127.0.0.1");
        assertEquals(ipInfo1, ipInfo3);
    }

    @Test
    public void getByte1() {
        assertEquals(ipInfo1.getByte1(), 127);
    }

    @Test
    public void getByte2() {
        assertEquals(ipInfo1.getByte2(), 0);
    }

    @Test
    public void getByte3() {
        assertEquals(ipInfo1.getByte3(), 0);
    }

    @Test
    public void getByte4() {
        assertEquals(ipInfo1.getByte4(), 1);
    }
}