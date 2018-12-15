package ru.spbau.mit.TorrentTask.Serializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Request.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class RequestSerializer {

    public static @Nullable DataOutputStream serialize(AbstractRequest request) {
        try (DataOutputStream dos = new DataOutputStream(new ByteArrayOutputStream())) {
            if (request instanceof ListRequest) {
                dos.writeByte(ListRequest.ID);
            } else if (request instanceof UploadRequest) {
                dos.writeByte(UploadRequest.ID);
                dos.writeUTF(((UploadRequest) request).getFileInfo().getFileName());
                dos.writeLong(((UploadRequest) request).getFileInfo().getSize());
            } else if (request instanceof SourcesRequest) {
                dos.writeByte(SourcesRequest.ID);
                dos.writeInt(((SourcesRequest) request).getFileId());
            } else if (request instanceof UpdateRequest) {
                dos.writeByte(UpdateRequest.ID);
                dos.writeShort(((UpdateRequest) request).getClientPort());
                dos.writeInt(((UpdateRequest) request).getCount());
                for (int id : ((UpdateRequest) request).getFileIDs()) {
                    dos.writeInt(id);
                }
            } else if (request instanceof StatRequest) {
                dos.writeByte(StatRequest.ID);
                dos.writeInt(((StatRequest) request).getFileId());
            } else if (request instanceof GetRequest) {
                dos.writeByte(GetRequest.ID);
                dos.writeInt(((GetRequest) request).getFileId());
                dos.writeInt(((GetRequest) request).getPart());
            } else {
                throw new IllegalArgumentException("Unknown request received by serializer!");
            }
            return dos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
