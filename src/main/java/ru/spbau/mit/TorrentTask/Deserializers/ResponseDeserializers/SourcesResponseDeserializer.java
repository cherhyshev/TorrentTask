package ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IPInfo;
import ru.spbau.mit.TorrentTask.Response.SourcesResponse;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class SourcesResponseDeserializer {
    public static @Nullable SourcesResponse deserialize(byte[] bytes) {
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
            int size = dis.readInt();
            ConnectInfo[] connectInfos = new ConnectInfo[size];
            for (int i = 0; i < size; i++) {
                byte byte1 = dis.readByte();
                byte byte2 = dis.readByte();
                byte byte3 = dis.readByte();
                byte byte4 = dis.readByte();
                short clientPort = dis.readShort();
                connectInfos[i] = new ConnectInfo(new IPInfo(byte1, byte2, byte3, byte4), clientPort);
            }
            return new SourcesResponse(size, connectInfos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
