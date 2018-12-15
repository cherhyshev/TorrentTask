package ru.spbau.mit.TorrentTask.Request;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatRequestTest {
    private StatRequest request = new StatRequest(42);

    @Test
    public void getFileId() {
        assertEquals(request.getFileId(), 42);

    }

    @Test
    public void equals() {
        assertEquals(request, new StatRequest(42));
    }

    @Test
    public void hashCode1() {
        assertEquals(request.hashCode(),
                new StatRequest(42).hashCode());
    }
}