package ru.spbau.mit.TorrentTask.Serialization.Serializers;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.TorrentTask.Request.*;

import java.io.*;

public final class RequestSerializer {

    public @Nullable DataOutputStream serialize(AbstractRequest request) {
        try (DataOutputStream dos = new DataOutputStream(new ByteArrayOutputStream())) {
            dos.writeByte(request.getId());
            if (request instanceof ListRequest) {
                // No unique information
            } else if (request instanceof UploadRequest) {
                dos.writeUTF(((UploadRequest) request).getFileInfo().getFileName());
                dos.writeLong(((UploadRequest) request).getFileInfo().getSize());
            } else if (request instanceof SourcesRequest) {
                dos.writeInt(((SourcesRequest) request).getFileId());
            } else if (request instanceof UpdateRequest) {
                dos.writeShort(((UpdateRequest) request).getClientPort());
                dos.writeInt(((UpdateRequest) request).getCount());
                for (int id : ((UpdateRequest) request).getFileIDs()) {
                    dos.writeInt(id);
                }
            } else if (request instanceof StatRequest) {
                dos.writeInt(((StatRequest) request).getFileId());
            } else if (request instanceof GetRequest) {
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
