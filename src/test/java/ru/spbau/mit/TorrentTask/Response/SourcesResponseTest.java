package ru.spbau.mit.TorrentTask.Response;

import org.junit.Test;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IPInfo;

import java.net.InetAddress;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SourcesResponseTest {
    private SourcesResponse response = new SourcesResponse(2,
            new ConnectInfo[]{
                    new ConnectInfo(new IPInfo(InetAddress.getLoopbackAddress()), (short) 80),
                    new ConnectInfo(IPInfo.fromString("8.8.8.8"), (short) 80)});

    @Test
    public void getSize() {
        assertEquals(response.getSize(), 2);
    }

    @Test
    public void getConnectInfos() {
        assertArrayEquals(response.getConnectInfos(), new ConnectInfo[]{
                new ConnectInfo(new IPInfo(InetAddress.getLoopbackAddress()), (short) 80),
                new ConnectInfo(IPInfo.fromString("8.8.8.8"), (short) 80)});
    }

    @Test
    public void equals() {
        assertEquals(response, new SourcesResponse(2, new ConnectInfo[]{
                new ConnectInfo(new IPInfo(InetAddress.getLoopbackAddress()), (short) 80),
                new ConnectInfo(IPInfo.fromString("8.8.8.8"), (short) 80)}));

    }

    @Test
    public void hashCode1() {
        assertEquals(response.hashCode(), new SourcesResponse(2, new ConnectInfo[]{
                new ConnectInfo(new IPInfo(InetAddress.getLoopbackAddress()), (short) 80),
                new ConnectInfo(IPInfo.fromString("8.8.8.8"), (short) 80)}).hashCode());

    }
}