package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.UpdateResponse;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class UpdateResponseDeserializer {
    public static @Nullable AbstractResponse deserialize(byte[] bytes) {
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
            boolean uploaded = dis.readBoolean();
            return new UpdateResponse(uploaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
