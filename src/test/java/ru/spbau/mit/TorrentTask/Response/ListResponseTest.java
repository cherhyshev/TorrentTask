package ru.spbau.mit.TorrentTask.Response;

import org.junit.Test;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ListResponseTest {
    private ListResponse response = new ListResponse(2,
            new IdentifiedFileInfo[]{IdentifiedFileInfo.fromString("0 42 42"),
                    IdentifiedFileInfo.fromString("1 13 13")});

    @Test
    public void getCount() {
        assertEquals(response.getCount(), 2);
    }

    @Test
    public void getIdentifiedFileInfos() {
        assertArrayEquals(response.getIdentifiedFileInfos(),
                new IdentifiedFileInfo[]{new IdentifiedFileInfo(0, "42", 42),
                        new IdentifiedFileInfo(1, "13", 13)});
    }

    @Test
    public void equals() {
        assertEquals(response, new ListResponse(2,
                new IdentifiedFileInfo[]{new IdentifiedFileInfo(0, "42", 42),
                        new IdentifiedFileInfo(1, "13", 13)}));
    }

    @Test
    public void hashCode1() {
        assertEquals(response.hashCode(), new ListResponse(2,
                new IdentifiedFileInfo[]{new IdentifiedFileInfo(0, "42", 42),
                        new IdentifiedFileInfo(1, "13", 13)}).hashCode());

    }
}