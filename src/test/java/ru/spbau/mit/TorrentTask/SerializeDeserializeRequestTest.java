package ru.spbau.mit.TorrentTask;

import org.junit.Test;
import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;
import ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers.SeedRequestDeserializer;
import ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers.TrackerRequestDeserializer;
import ru.spbau.mit.TorrentTask.Request.*;
import ru.spbau.mit.TorrentTask.Serializers.RequestSerializer;

import static org.junit.Assert.*;


public class SerializeDeserializeRequestTest {


    @Test
    public void serializeAndDeserializeListRequest() {
        ListRequest request = new ListRequest();
        assertTrue(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof ListRequest);
        assertFalse(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof UploadRequest);

    }

    @Test
    public void serializeAndDeserializeUploadRequest() {
        UploadRequest request = new UploadRequest(FileInfo.fromString("42 42"));
        assertTrue(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof UploadRequest);
        assertFalse(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof ListRequest);
        assertEquals(request, TrackerRequestDeserializer.deserialize(
                RequestSerializer.serialize(new UploadRequest(new FileInfo("42", 42)))));
    }

    @Test
    public void serializeAndDeserializeSourcesRequest() {
        SourcesRequest request = new SourcesRequest(42);
        assertTrue(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof SourcesRequest);
        assertFalse(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof ListRequest);
        assertEquals(request, TrackerRequestDeserializer.deserialize(
                RequestSerializer.serialize(new SourcesRequest(42))));
    }

    @Test
    public void serializeAndDeserializeUpdateRequest() {
        UpdateRequest request = new UpdateRequest((short) 80, 2, new int[]{42, 666});
        assertTrue(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof UpdateRequest);
        assertFalse(TrackerRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof ListRequest);
        assertEquals(request, TrackerRequestDeserializer.deserialize(
                RequestSerializer.serialize(new UpdateRequest((short) 80, 2, new int[]{42, 666}))));
    }

    @Test
    public void serializeAndDeserializeStatRequest() {
        StatRequest request = new StatRequest(42);
        assertTrue(SeedRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof StatRequest);
        assertFalse(SeedRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof GetRequest);
        assertEquals(request, SeedRequestDeserializer.deserialize(
                RequestSerializer.serialize(new StatRequest(42))));
    }

    @Test
    public void serializeAndDeserializeGetRequest() {
        GetRequest request = new GetRequest(42, 42);
        assertTrue(SeedRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof GetRequest);
        assertFalse(SeedRequestDeserializer.deserialize(RequestSerializer.serialize(request)) instanceof StatRequest);
        assertEquals(request, SeedRequestDeserializer.deserialize(
                RequestSerializer.serialize(new GetRequest(42, 42))));
    }


}
