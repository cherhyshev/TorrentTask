package ru.spbau.mit.TorrentTask;

import ru.spbau.mit.TorrentTask.CommonUtils.FileInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.IPInfo;
import ru.spbau.mit.TorrentTask.CommonUtils.PeerInfo;
import ru.spbau.mit.TorrentTask.Deserializers.RequestDeserializers.TrackerRequestDeserializer;
import ru.spbau.mit.TorrentTask.Protocols.TrackerProtocol;
import ru.spbau.mit.TorrentTask.Request.*;
import ru.spbau.mit.TorrentTask.Response.AbstractResponse;
import ru.spbau.mit.TorrentTask.Serializers.ResponseSerializer;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class TorrentTracker {
    private static final String STUPID_SEPARATOR = "#%#";
    private final File sandBox = new File(System.getProperty("user.home") + "torrent_sandbox/");
    private final File trackerState = new File(sandBox.getAbsolutePath() + "tracker_state.txt");
    private final Map<Integer, FileInfo> filesMap = new HashMap<>();
    private final List<PeerInfo> peerInfos = new ArrayList<>();
    private final TrackerProtocol trackerProtocol = new TrackerProtocol(filesMap, peerInfos);
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        TorrentTracker tracker = new TorrentTracker();
        tracker.launch();
    }

    private void launch() {
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

    private void saveState() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(filesMap.size()).append("\n");
        for (int id : filesMap.keySet()) {
            stringBuilder.append(id).append(" ").append(filesMap.get(id).toString()).append("\n");
        }
        stringBuilder.append(peerInfos.size()).append("\n");
        for (PeerInfo peerInfo : peerInfos) {
            stringBuilder.append(peerInfo.toString()).append(STUPID_SEPARATOR);
        }

        assert sandBox.mkdirs();
        trackerState.createNewFile();
        try (FileOutputStream fis = new FileOutputStream(trackerState, false)) {
            fis.write(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadState() {
        if (!trackerState.exists()) {
            return;
        }
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(trackerState.getAbsolutePath())
                , Charset.forName("UTF-8"))) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] stateStringArray = contentBuilder.toString().split("\n");
        int filesMapSize = Integer.parseInt(stateStringArray[0]);
        for (int i = 1; i <= filesMapSize; i++) {
            String[] s = stateStringArray[i].split(" ");
            filesMap.put(Integer.parseInt(s[0]),
                    FileInfo.fromString(s[1] + " " + s[2]));
        }
        assert filesMapSize == filesMap.size();
        int peerInfoSize = Integer.parseInt(stateStringArray[filesMapSize + 1]);

        for (String s : String.join("\n",
                Arrays.copyOfRange(stateStringArray, filesMapSize + 2, stateStringArray.length))
                .split(STUPID_SEPARATOR)) {
            peerInfos.add(PeerInfo.fromString(s));
        }
        assert peerInfoSize == peerInfos.size();
    }

    private class TrackerRequestHandler implements Runnable {

        private Socket socket;

        TrackerRequestHandler(Socket connection) {
            this.socket = connection;
        }

        public void run() {
            try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
                AbstractRequest request = TrackerRequestDeserializer.deserialize(dis);
                byte[] answer;
                AbstractResponse response;
                if (request instanceof ListRequest) {
                    response = trackerProtocol.executeListRequest();
                } else if (request instanceof UploadRequest) {
                    response = trackerProtocol.executeUploadRequest((UploadRequest) request);
                } else if (request instanceof SourcesRequest) {
                    response = trackerProtocol.executeSourcesRequest((SourcesRequest) request);
                } else if (request instanceof UpdateRequest) {
                    InetAddress inetAddress = socket.getInetAddress();
                    response = trackerProtocol.executeUpdateRequest(new IPInfo(inetAddress), (UpdateRequest) request);
                } else {
                    throw new IllegalArgumentException("Unknown request on tracker");
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
