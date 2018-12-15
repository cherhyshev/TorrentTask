package ru.spbau.mit.TorrentTask.Request;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetRequestTest {
    private GetRequest request = new GetRequest(42, 42);

    @Test
    public void ID() {
        assertEquals(GetRequest.ID, 2);
    }

    @Test
    public void getFileId() {
        assertEquals(request.getFileId(), 42);
    }

    @Test
    public void getPart() {
        assertEquals(request.getPart(), 42);
    }

    @Test
    public void equals() {
        assertEquals(request, new GetRequest(42, 42));
    }

    @Test
    public void hashCode1() {
        assertEquals(request.hashCode(), new GetRequest(42, 42).hashCode());
    }
}