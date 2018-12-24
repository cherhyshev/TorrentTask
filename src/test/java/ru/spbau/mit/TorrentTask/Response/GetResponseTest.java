package ru.spbau.mit.TorrentTask.Response;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GetResponseTest {
    private GetResponse response = new GetResponse(new byte[]{(byte) 42, (byte) 13});

    @Test
    public void getContent() {
        assertArrayEquals(response.getContent(), new byte[]{(byte) 42, (byte) 13});
    }

    @Test
    public void equals() {
        assertEquals(response, new GetResponse(new byte[]{(byte) 42, (byte) 13}));
    }

    @Test
    public void hashCode1() {
        assertEquals(response.hashCode(), new GetResponse(new byte[]{(byte) 42, (byte) 13}).hashCode());

    }
}