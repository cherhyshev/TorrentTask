package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.GetResponse;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class GetResponseDeserializer {

    public static @Nullable AbstractResponse deserialize(byte[] bytes) {
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
            byte[] content = new byte[dis.available()];
            dis.readFully(content);
            return new GetResponse(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
