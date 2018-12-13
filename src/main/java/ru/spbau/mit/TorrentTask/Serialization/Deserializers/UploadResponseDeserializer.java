package ru.spbau.mit.TorrentTask.Serialization.Deserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.UploadResponse;

import java.io.DataInputStream;
import java.io.IOException;

public final class UploadResponseDeserializer implements AbstractResponseDeserializer {
    @Override
    public @Nullable AbstractResponse deserialize(DataInputStream dis) {
        try {
            int id = dis.readInt();

            return new UploadResponse(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
