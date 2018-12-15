package ru.spbau.mit.TorrentTask.Serializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IPInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;
import ru.spbau.mit.TorrentTask.Response.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class ResponseSerializer {
    public static @Nullable byte[] serialize(AbstractResponse response) {
        try (DataOutputStream dis = new DataOutputStream(new ByteArrayOutputStream())) {

            if (response instanceof ListResponse) {
                dis.writeInt(((ListResponse) response).getCount());
                for (IdentifiedFileInfo identifiedFileInfo : ((ListResponse) response).getIdentifiedFileInfos()) {
                    dis.writeInt(identifiedFileInfo.getId());
                    dis.writeUTF(identifiedFileInfo.getFileInfo().getFileName());
                    dis.writeLong(identifiedFileInfo.getFileInfo().getSize());
                }
            } else if (response instanceof UploadResponse) {
                dis.writeInt(((UploadResponse) response).getId());
            } else if (response instanceof SourcesResponse) {
                dis.writeInt(((SourcesResponse) response).getSize());
                for (ConnectInfo connectInfo : ((SourcesResponse) response).getConnectInfos()) {
                    IPInfo ipInfo = connectInfo.getIpInfo();
                    dis.writeByte(ipInfo.getByte1());
                    dis.writeByte(ipInfo.getByte2());
                    dis.writeByte(ipInfo.getByte3());
                    dis.writeByte(ipInfo.getByte4());
                    dis.writeShort(connectInfo.getPortInfo());
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
