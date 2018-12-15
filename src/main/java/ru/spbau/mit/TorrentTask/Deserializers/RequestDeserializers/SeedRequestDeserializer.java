package ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Request.AbstractRequest;
import ru.spbau.mit.TorrentTask.Request.GetRequest;
import ru.spbau.mit.TorrentTask.Request.StatRequest;

import java.io.DataInputStream;
import java.io.IOException;

public class SeedRequestDeserializer {
    public static @Nullable AbstractRequest deserialize(DataInputStream dis) {
        try {
            byte id = dis.readByte();
            int fileId = dis.readInt();
            switch (id) {
                case 1:
                    return new StatRequest(id, fileId);
                case 2:
                    int partId = dis.readInt();
                    return new GetRequest(id, fileId, partId);
                default:
                    throw new RuntimeException("Unknown request received by SeedRequestDeserializer!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
