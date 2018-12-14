package ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers;

import ru.spbau.mit.TorrentTask.Request.AbstractRequest;

import java.io.DataInputStream;

public interface AbstractRequestDeserializer {
    AbstractRequest deserialize(DataInputStream dis);
}
