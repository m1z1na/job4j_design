package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class EchoServer {
    final static String HELLO = "Hello";
    final static String EXIT = "Exit";

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String message = "";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (message == "") {
                            if (str.contains(EXIT) && message == "") {
                                break;
                            } else if (str.contains(HELLO)) {
                                message = "Hello\r\n\r\n";
                            } else {
                                message = "What?\r\n\r\n";
                            }
                        }
                    }
                    if (message != null) {
                        out.write(message.getBytes());
                    } else {
                        System.out.println("Вы завершили работу");
                        server.close();
                        break;
                    }
                    out.flush();
                }
            }
        }

    }
}