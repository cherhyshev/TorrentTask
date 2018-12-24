package ru.spbau.mit.TorrentTask.Request;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class UpdateRequestTest {
    private UpdateRequest request = new UpdateRequest((short) 80, 2, new int[]{42, 666});

    @Test
    public void getClientPort() {
        assertEquals(request.getClientPort(), 80);
    }

    @Test
    public void getCount() {
        assertEquals(request.getCount(), 2);
    }

    @Test
    public void getFileIDs() {
        assertArrayEquals(request.getFileIDs(), new int[]{42, 666});
    }

    @Test
    public void equals() {
        assertEquals(request, new UpdateRequest((short) 80, 2, new int[]{42, 666}));
    }

    @Test
    public void hashCode1() {
        assertEquals(request.hashCode(),
                new UpdateRequest((short) 80, 2, new int[]{42, 666}).hashCode());
    }
}