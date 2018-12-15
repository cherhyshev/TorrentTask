package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.UploadResponse;

import java.io.DataInputStream;
import java.io.IOException;

public final class UploadResponseDeserializer {
    public static @Nullable AbstractResponse deserialize(DataInputStream dis) {
        try {
            int id = dis.readInt();

            return new UploadResponse(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
