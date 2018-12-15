package ru.spbau.mit.TorrentTask.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UploadResponseTest {
    private UploadResponse response = new UploadResponse(42);

    @Test
    public void getId() {
        assertEquals(response.getId(), 42);
    }

    @Test
    public void equals() {
        assertEquals(response, new UploadResponse(42));
    }

    @Test
    public void hashCode1() {
        assertEquals(response.hashCode(), new UploadResponse(42).hashCode());
    }
}