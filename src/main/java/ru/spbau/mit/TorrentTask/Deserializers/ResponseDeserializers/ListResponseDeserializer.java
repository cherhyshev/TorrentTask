package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.ListResponse;

import java.io.DataInputStream;
import java.io.IOException;

public final class ListResponseDeserializer {

    public static @Nullable AbstractResponse deserialize(DataInputStream dis) {
        try {
            int count = dis.readInt();
            IdentifiedFileInfo[] identifiedFileInfoList = new IdentifiedFileInfo[count];
            for (int i = 0; i < count; i++) {
                int id = dis.readInt();
                String name = dis.readUTF();
                long size = dis.readLong();
                identifiedFileInfoList[i] = new IdentifiedFileInfo(id, name, size);
            }
            return new ListResponse(count, identifiedFileInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
