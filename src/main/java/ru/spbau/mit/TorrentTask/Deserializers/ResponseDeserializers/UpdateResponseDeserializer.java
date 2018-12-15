package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.UpdateResponse;

import java.io.DataInputStream;
import java.io.IOException;

public final class UpdateResponseDeserializer {
    public static @Nullable AbstractResponse deserialize(DataInputStream dis) {
        try {
            boolean uploaded = dis.readBoolean();
            return new UpdateResponse(uploaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
