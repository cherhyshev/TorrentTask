package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.UploadResponse;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class UploadResponseDeserializer {
    public static @Nullable AbstractResponse deserialize(byte[] bytes) {
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
            int id = dis.readInt();

            return new UploadResponse(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
