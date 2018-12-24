package ru.spbau.mit.TorrentTask.Request;

import org.junit.Test;
import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;

import static org.junit.Assert.assertEquals;

public class UploadRequestTest {
    private UploadRequest request = new UploadRequest(FileInfo.fromString("42 42"));

    @Test
    public void ID() {
        assertEquals(UpdateRequest.ID, 4);
    }


    @Test
    public void getFileInfo() {
        assertEquals(request.getFileInfo(), new FileInfo("42", 42));
    }

    @Test
    public void equals() {
        assertEquals(request, new UploadRequest(new FileInfo("42", 42)));
    }

    @Test
    public void hashCode1() {
        assertEquals(request.hashCode(),
                new UploadRequest(new FileInfo("42", 42)).hashCode());
    }
}