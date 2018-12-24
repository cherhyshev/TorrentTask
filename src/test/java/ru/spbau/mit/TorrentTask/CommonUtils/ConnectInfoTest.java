package ru.spbau.mit.TorrentTask.CommonUtils;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConnectInfoTest {
    private ConnectInfo connectInfo = new ConnectInfo(new IPInfo(InetAddress.getLoopbackAddress()), (short) 80);


    @Test
    public void fromString() {
        assertNotNull(ConnectInfo.fromString("127.0.0.1 80"));
    }

    @Test
    public void equals() {
        assertEquals(connectInfo, new ConnectInfo(IPInfo.fromString("127.0.0.1"), (short) 80));
    }


    @Test
    public void getIpInfo() {
        assertEquals(connectInfo.getIpInfo(), IPInfo.fromString("127.0.0.1"));
    }

    @Test
    public void getPortInfo() {
        assertEquals(connectInfo.getPortInfo(), 80);
    }
}