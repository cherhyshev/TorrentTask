package ru.spbau.mit.TorrentTask.Serialization.Deserializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Response.SourcesResponce;
import ru.spbau.mit.TorrentTask.Utils.ClientInfo;
import ru.spbau.mit.TorrentTask.Utils.IPInfo;

import java.io.DataInputStream;
import java.io.IOException;

public final class SourcesResponseDeserializer implements AbstractResponseDeserializer {
    @Override
    public @Nullable AbstractResponse deserialize(DataInputStream dis) {
        try {
            int size = dis.readInt();
            ClientInfo[] clientInfos = new ClientInfo[size];
            for (int i = 0; i < size; i++) {
                byte byte1 = dis.readByte();
                byte byte2 = dis.readByte();
                byte byte3 = dis.readByte();
                byte byte4 = dis.readByte();
                short clientPort = dis.readShort();
                clientInfos[i] = new ClientInfo(new IPInfo(byte1, byte2, byte3, byte4), clientPort);
            }
            return new SourcesResponce(size, clientInfos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
