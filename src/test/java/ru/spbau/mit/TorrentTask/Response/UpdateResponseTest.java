package ru.spbau.mit.TorrentTask.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateResponseTest {
    private UpdateResponse response = new UpdateResponse(true);

    @Test
    public void isUpdated() {
        assertTrue(response.isUpdated());
    }

    @Test
    public void equals() {
        assertEquals(response, new UpdateResponse(true));
    }

    @Test
    public void hashCode1() {
        assertEquals(response.hashCode(), new UpdateResponse(true).hashCode());

    }
}