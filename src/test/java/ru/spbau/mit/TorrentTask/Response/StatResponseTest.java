package ru.spbau.mit.TorrentTask.Response;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StatResponseTest {
    private StatResponse response = new StatResponse(2, new int[]{42, 13});

    @Test
    public void getParts() {
        assertArrayEquals(response.getParts(), new int[]{13, 42});
    }

    @Test
    public void getCount() {
        assertEquals(response.getCount(), 2);
    }

    @Test
    public void equals() {
        assertEquals(response, new StatResponse(2, new int[]{13, 42}));
    }

    @Test
    public void hashCode1() {
        assertEquals(response.hashCode(), new StatResponse(2, new int[]{13, 42}).hashCode());
    }
}