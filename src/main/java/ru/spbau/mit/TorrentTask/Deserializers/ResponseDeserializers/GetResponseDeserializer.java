package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.GetResponse;

import java.io.DataInputStream;
import java.io.IOException;

public final class GetResponseDeserializer implements AbstractResponseDeserializer {
    @Override
    public @Nullable AbstractResponse deserialize(DataInputStream dis) {
        try {
            byte[] content = new byte[dis.available()];
            dis.readFully(content);
            return new GetResponse(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
