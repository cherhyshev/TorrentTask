package ru.spbau.mit.TorrentTask.Serialization.Serializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Response.*;
import ru.spbau.mit.TorrentTask.Utils.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class ResponseSerializer {
    public @Nullable byte[] serialize(AbstractResponse response) {
        try (DataOutputStream dis = new DataOutputStream(new ByteArrayOutputStream())) {

            if (response instanceof ListResponse) {
                dis.writeInt(((ListResponse) response).getCount());
                for (IdentifiedFileInfo identifiedFileInfo : ((ListResponse) response).getIdentifiedFileInfos()) {
                    dis.writeInt(identifiedFileInfo.getId());
                    dis.writeUTF(identifiedFileInfo.getFileName());
                    dis.writeLong(identifiedFileInfo.getSize());
                }
            } else if (response instanceof UploadResponse) {
                dis.writeInt(((UploadResponse) response).getId());
            } else if (response instanceof SourcesResponce) {
                dis.writeInt(((SourcesResponce) response).getSize());
                for (ClientInfo clientInfo : ((SourcesResponce) response).getClientInfos()) {
                    IPInfo ipInfo = clientInfo.getIpAddress();
                    dis.writeByte(ipInfo.getByte1());
                    dis.writeByte(ipInfo.getByte2());
                    dis.writeByte(ipInfo.getByte3());
                    dis.writeByte(ipInfo.getByte4());
                    dis.writeShort(clientInfo.getClientPort());
                }
            } else if (response instanceof UpdateResponse) {
                dis.writeBoolean(((UpdateResponse) response).isUpdated());
            } else if (response instanceof StatResponse) {
                dis.writeInt(((StatResponse) response).getCount());
                for (int part : ((StatResponse) response).getParts()) {
                    dis.writeInt(part);
                }
            } else if (response instanceof GetResponse) {
                dis.write(((GetResponse) response).getContent());
            } else {
                throw new IllegalArgumentException("Unknown response received by serializer!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
