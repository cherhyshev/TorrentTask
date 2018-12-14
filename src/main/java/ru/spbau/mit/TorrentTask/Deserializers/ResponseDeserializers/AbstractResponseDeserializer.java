package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;

import java.io.DataInputStream;

public interface AbstractResponseDeserializer {
    @Nullable AbstractResponse deserialize(DataInputStream dis);
}
