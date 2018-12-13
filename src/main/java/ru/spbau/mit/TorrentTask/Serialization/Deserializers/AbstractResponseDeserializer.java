package ru.spbau.mit.TorrentTask.Serialization.Deserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;

import java.io.DataInputStream;

public interface AbstractResponseDeserializer {
    public @Nullable AbstractResponse deserialize(DataInputStream dis);
}
