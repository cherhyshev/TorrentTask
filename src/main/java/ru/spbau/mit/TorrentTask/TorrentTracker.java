package ru.spbau.mit.TorrentTask;

import ru.spbau.mit.TorrentTask.CommonUtils.IPInfo;
import ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers.TrackerRequestDeserializer;
import ru.spbau.mit.TorrentTask.Protocols.TrackerProtocol;
import ru.spbau.mit.TorrentTask.Request.*;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Serializers.ResponseSerializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TorrentTracker {
    private final TrackerProtocol trackerProtocol = new TrackerProtocol();
    private final TrackerRequestDeserializer requestDeserializer = new TrackerRequestDeserializer();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        TorrentTracker tracker = new TorrentTracker();
        tracker.runTracker();
    }

    private void runTracker() {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    executorService.submit(new TrackerRequestHandler(clientSocket));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class TrackerRequestHandler implements Runnable {

        private Socket socket;

        TrackerRequestHandler(Socket connection) {
            this.socket = connection;
        }

        public void run() {
            try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
                AbstractRequest request = requestDeserializer.deserialize(dis);
                byte[] answer;
                AbstractResponse response = null;
                if (request instanceof ListRequest) {
                    response = trackerProtocol.executeListRequest();
                } else if (request instanceof UploadRequest) {
                    response = trackerProtocol.executeUploadRequest((UploadRequest) request);
                } else if (request instanceof SourcesRequest) {
                    response = trackerProtocol.executeSourcesRequest((SourcesRequest) request);
                } else if (request instanceof UpdateRequest) {
                    byte[] ip = socket.getInetAddress().getAddress();
                    response = trackerProtocol.executeUpdateRequest(new IPInfo(ip[0], ip[1], ip[2], ip[3]),
                            (UpdateRequest) request);
                }
                answer = ResponseSerializer.serialize(response);
                try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
                    if (answer != null) {
                        dos.write(answer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
