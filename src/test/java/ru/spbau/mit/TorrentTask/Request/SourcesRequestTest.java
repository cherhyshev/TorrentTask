package ru.spbau.mit.TorrentTask.Request;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SourcesRequestTest {
    private SourcesRequest request = new SourcesRequest(42);

    @Test
    public void ID() {
        assertEquals(SourcesRequest.ID, 3);
    }

    @Test
    public void getFileId() {
        assertEquals(request.getFileId(), 42);
    }

    @Test
    public void equals() {
        assertEquals(request, new SourcesRequest(42));
    }

    @Test
    public void hashCode1() {
        assertEquals(request.hashCode(), new SourcesRequest(42).hashCode());

    }
}