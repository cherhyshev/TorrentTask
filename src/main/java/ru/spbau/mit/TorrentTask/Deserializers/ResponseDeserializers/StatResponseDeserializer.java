package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.StatResponse;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class StatResponseDeserializer {
    public static @Nullable StatResponse deserialize(byte[] bytes) {
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
            int count = dis.readInt();
            int[] parts = new int[count];
            for (int i = 0; i < count; i++) {
                int partNum = dis.readInt();
                parts[i] = partNum;
            }
            return new StatResponse(count, parts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
