package ru.spbau.mit.TorrentTask.CommonUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdentifiedFileInfoTest {
    private IdentifiedFileInfo info1 = new IdentifiedFileInfo(0, "42", 42);
    private IdentifiedFileInfo info2 = new IdentifiedFileInfo(0, new FileInfo("42", 42));

    @Test
    public void equals() {
        assertEquals(info1, info2);
    }

    @Test
    public void fromString() {
        assertEquals(info1, IdentifiedFileInfo.fromString("0 42 42"));
    }

    @Test
    public void getId() {
        assertEquals(info1.getId(), 0);
    }

    @Test
    public void getFileInfo() {
        assertEquals(info1.getFileInfo(), FileInfo.fromString("42 42"));
    }
}