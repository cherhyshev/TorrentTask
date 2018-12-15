package ru.spbau.mit.TorrentTask.ClientUtils;

import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartedFile {
    private static final int TEN_MB = 10 * 1024 * 1024;

    private List<byte[]> partsList;
    private List<Integer> readyPartsList = new ArrayList<>();
    private int fileSize;
    private int partsNum;

    public PartedFile(Path path) {
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            fileSize = fis.available();
            partsNum = fileSize / TEN_MB + 1;
            partsList = new ArrayList<>(partsNum);
            byte[] buffer = new byte[TEN_MB];
            while (fis.read(buffer) > 0) {
                partsList.add(buffer);
                buffer = new byte[TEN_MB];
            }
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    public PartedFile(int fileSize) {
        this.fileSize = fileSize;
        partsNum = fileSize / TEN_MB + 1;
        partsList = new ArrayList<>(partsNum);
    }

    public void updatePartByIndex(int index, byte[] part) {
        partsList.set(index, part);
    }

    private void prepareReadyParts() {
        for (int i = 0; i < partsList.size(); i++) {
            // Если часть полная или она последняя и полная
            if (partsList.get(i).length == TEN_MB
                    || ((i == partsList.size() - 1)
                    && partsList.get(i).length == fileSize - TEN_MB * partsNum)) {
                readyPartsList.add(i);
            }
        }
    }

    public @Nullable byte[] getPartByIndex(int idx) {
        if (readyPartsList.contains(idx)) {
            return partsList.get(idx);
        } else {
            return null;
        }
    }

    public @Nullable int[] getReadyParts() {
        prepareReadyParts();
        return readyPartsList.stream().mapToInt(i -> i).toArray();
    }


}
