package ru.job4j.io;

import java.io.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                     out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                     String str = in.readLine();
                     System.out.println(str);
                     if (str.contains("?msg=Bye")) {
                         out.write("Good bye, dear friend.".getBytes());
                         server.close();
                     } else if (str.contains("?msg=Hello")) {
                         out.write("Hello, dear friend.".getBytes());
                     } else {
                         out.write("What.".getBytes());
                     }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Exception", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Exception", e);
        }
    }
}

