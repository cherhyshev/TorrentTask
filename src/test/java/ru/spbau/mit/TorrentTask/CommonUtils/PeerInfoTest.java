package ru.spbau.mit.TorrentTask.CommonUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PeerInfoTest {
    private PeerInfo peerInfo = new PeerInfo(new ConnectInfo(IPInfo.fromString("127.0.0.1"), (short) 80));

    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            peerInfo.updateByID(i);
        }
    }

    @Test
    public void fromString() {
        String peerState = peerInfo.toString();
        PeerInfo info = PeerInfo.fromString(peerState);
        assertEquals(peerInfo, info);
    }

    @Test
    public void isPeering() {
        boolean result = true;
        for (int i = 0; i < 10; i++) {
            result = result && peerInfo.isPeering(i);
        }
    }

    @Test
    public void getConnectInfo() {
        assertEquals(peerInfo.getConnectInfo(), ConnectInfo.fromString("127.0.0.1 80"));
    }

}