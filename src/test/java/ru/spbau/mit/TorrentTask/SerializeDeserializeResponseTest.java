package ru.spbau.mit.TorrentTask;

import org.junit.Test;
import ru.spbau.mit.TorrentTask.CommonUtils.ConnectInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IPInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IdentifiedFileInfo;
import ru.spbau.mit.TorrentTask.Deserializers.ResponseDeserializers.*;
import ru.spbau.mit.TorrentTask.Response.*;
import ru.spbau.mit.TorrentTask.Serializers.ResponseSerializer;

import java.net.InetAddress;

import static org.junit.Assert.*;


public class SerializeDeserializeResponseTest {


    @Test
    public void serializeAndDeserializeListResponse() {
        ListResponse response = new ListResponse(2,
                new IdentifiedFileInfo[]{IdentifiedFileInfo.fromString("0 42 42"),
                        IdentifiedFileInfo.fromString("1 13 13")});
        assertTrue(ListResponseDeserializer.deserialize(ResponseSerializer.serialize(response)) instanceof ListResponse);

    }

    @Test
    public void serializeAndDeserializeUploadResponse() {
        UploadResponse response = new UploadResponse(42);
        assertTrue(UploadResponseDeserializer.deserialize(ResponseSerializer.serialize(response)) instanceof UploadResponse);
        assertEquals(response, UploadResponseDeserializer.deserialize(
                ResponseSerializer.serialize(new UploadResponse(42))));
    }

    @Test
    public void serializeAndDeserializeSourcesResponse() {
        SourcesResponse response = new SourcesResponse(2, new ConnectInfo[]{
                new ConnectInfo(new IPInfo(InetAddress.getLoopbackAddress()), (short) 80),
                new ConnectInfo(IPInfo.fromString("8.8.8.8"), (short) 80)});
        assertTrue(SourcesResponseDeserializer.deserialize(ResponseSerializer.serialize(response)) instanceof SourcesResponse);
        assertEquals(response, SourcesResponseDeserializer.deserialize(
                ResponseSerializer.serialize(new SourcesResponse(2, new ConnectInfo[]{
                        new ConnectInfo(new IPInfo(InetAddress.getLoopbackAddress()), (short) 80),
                        new ConnectInfo(IPInfo.fromString("8.8.8.8"), (short) 80)}))));
    }

    @Test
    public void serializeAndDeserializeUpdateResponse() {
        UpdateResponse response = new UpdateResponse(true);
        assertTrue(UpdateResponseDeserializer.deserialize(ResponseSerializer.serialize(response)) instanceof UpdateResponse);
        assertEquals(response, UpdateResponseDeserializer.deserialize(
                ResponseSerializer.serialize(new UpdateResponse(true))));
    }

    @Test
    public void serializeAndDeserializeStatResponse() {
        StatResponse response = new StatResponse(2, new int[]{42, 13});
        assertTrue(StatResponseDeserializer.deserialize(ResponseSerializer.serialize(response)) instanceof StatResponse);
        assertEquals(response, StatResponseDeserializer.deserialize(ResponseSerializer.serialize(response)));
    }

    @Test
    public void serializeAndDeserializeGetResponse() {
        GetResponse response = new GetResponse(new byte[]{(byte) 42, (byte) 13});
        assertTrue(GetResponseDeserializer.deserialize(ResponseSerializer.serialize(response)) instanceof GetResponse);
        assertFalse(GetResponseDeserializer.deserialize(ResponseSerializer.serialize(response)) instanceof StatResponse);
        assertEquals(response, GetResponseDeserializer.deserialize(ResponseSerializer.serialize(response)));
    }
}
