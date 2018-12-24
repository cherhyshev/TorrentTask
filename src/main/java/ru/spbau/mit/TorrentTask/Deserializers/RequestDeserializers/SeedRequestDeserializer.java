package ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Request.AbstractRequest;
import ru.spbau.mit.TorrentTask.Request.GetRequest;
import ru.spbau.mit.TorrentTask.Request.StatRequest;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class SeedRequestDeserializer {
    public static @Nullable AbstractRequest deserialize(byte[] bytes) {
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
            byte id = dis.readByte();
            int fileId = dis.readInt();
            switch (id) {
                case 1:
                    return new StatRequest(fileId);
                case 2:
                    int partId = dis.readInt();
                    return new GetRequest(fileId, partId);
                default:
                    throw new RuntimeException("Unknown request received by SeedRequestDeserializer!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
