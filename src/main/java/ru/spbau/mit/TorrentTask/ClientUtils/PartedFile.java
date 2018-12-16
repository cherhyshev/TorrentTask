package ru.spbau.mit.TorrentTask.ClientUtils;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PartedFile {
    private static final int TEN_MB = 10 * 1024 * 1024;

    private Map<Integer, byte[]> numeratedParts;
    private long fileSize;
    private int partsNum;
    private String fileName;

    public PartedFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            fileName = file.getName();
            fileSize = fis.available();
            partsNum = (int) (fileSize / TEN_MB + 1);
            numeratedParts = new HashMap<>();
            byte[] buffer = new byte[TEN_MB];
            while (fis.read(buffer) != -1) {
                numeratedParts.put(numeratedParts.size(), buffer);
                buffer = new byte[TEN_MB];
            }
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    public PartedFile(FileInfo fileInfo) {
        this.fileName = fileInfo.getFileName();
        this.fileSize = fileInfo.getSize();
        partsNum = (int) (fileSize / TEN_MB + 1);
        numeratedParts = new HashMap<>();
    }


    public void updatePartByIndex(int index, byte[] part) {
        numeratedParts.put(index, part);
    }

    public @Nullable byte[] getPartByIndex(int idx) {
        return numeratedParts.getOrDefault(idx, null);
    }

    public @Nullable int[] getReadyParts() {
        int[] result = new int[numeratedParts.size()];
        int count = 0;
        for (int i : numeratedParts.keySet()) {
            result[count] = i;
            count++;
        }
        return result;
    }

}
