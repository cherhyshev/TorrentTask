package ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;
import ru.spbau.mit.TorrentTask.Request.*;

import java.io.DataInputStream;
import java.io.IOException;

public final class TrackerRequestDeserializer {
    public static @Nullable AbstractRequest deserialize(DataInputStream dis) {
        try {
            byte id = dis.readByte();
            switch (id) {
                case 1:
                    return new ListRequest();
                case 2:
                    String name = dis.readUTF();
                    long size = dis.readLong();
                    return new UploadRequest(new FileInfo(name, size));
                case 3:
                    int fileId = dis.readInt();
                    return new SourcesRequest(fileId);
                case 4:
                    short clientPort = dis.readShort();
                    int count = dis.readInt();
                    int[] fileIds = new int[count];
                    for (int i = 0; i < count; i++) {
                        fileIds[i] = dis.readInt();
                    }
                    return new UpdateRequest(clientPort, count, fileIds);
                default:
                    throw new RuntimeException("Unknown request received by TrackerRequestDeserializer!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
