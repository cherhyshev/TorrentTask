package ru.spbau.mit.TorrentTask.CommonUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileInfoTest {
    private FileInfo fileInfo = new FileInfo("42", 42);
    private FileInfo fileInfo2;

    @Test
    public void fromString() {
        fileInfo2 = FileInfo.fromString("42 42");
        assertNotNull(fileInfo2);
    }

    @Test
    public void equals() {
        assertEquals(fileInfo, FileInfo.fromString("42 42"));
    }

    @Test
    public void hashcode() {
        assertEquals(fileInfo.hashCode(), FileInfo.fromString("42 42").hashCode());
    }


    @Test
    public void getFileName() {
        assertEquals(fileInfo.getFileName(), "42");
    }

    @Test
    public void getSize() {
        assertEquals(fileInfo.getSize(), 42);
    }
}