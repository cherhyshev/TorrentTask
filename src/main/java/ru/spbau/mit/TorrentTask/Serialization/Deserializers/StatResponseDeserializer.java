package ru.spbau.mit.TorrentTask.Serialization.Deserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.StatResponse;

import java.io.DataInputStream;
import java.io.IOException;

public final class StatResponseDeserializer  implements AbstractResponseDeserializer {
    @Override
    public @Nullable AbstractResponse deserialize(DataInputStream dis) {
        try {
            int count = dis.readInt();
            int[] parts = new int[count];
            for (int i = 0; i < count; i++){
                int partNum = dis.readInt();
                parts[i] = partNum;
            }
            return new StatResponse(count, parts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
