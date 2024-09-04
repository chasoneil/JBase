package com.chason.test.kv;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KVServer {

    // 真实存放数据的Map
    private static final Map<String, String> store = new HashMap<>();

    public static void main(String[] args) {

        System.out.print("kvserver(1.0.0):");

        Scanner scanner = new Scanner(System.in);

        String cmd = scanner.nextLine();
        String[] loginCmds = cmd.split(" ");

        if (loginCmds.length != 2) {
            System.out.println();
            System.out.println("Command Error, Usage: kvserver -p<port>");
            System.exit(0);
        }

        if (!"kvserver".equalsIgnoreCase(loginCmds[0])) {
            System.out.println();
            System.out.println("Command Error, Usage: kvserver -p<port>");
            System.exit(0);
        }

        if (!loginCmds[1].contains("-p")) {
            System.out.println();
            System.out.println("Command Error, Usage: kvserver -p<port>");
            System.exit(0);
        }

        int port = Integer.parseInt(loginCmds[1].replace("-p", ""));

        if (port < 1024 || port > 65535) {
            System.out.println();
            System.out.println("port range : 1024 - 65535");
            System.exit(0);
        }

        // 判断端口是否正确
        if (args.length > 1 && "-p".equals(args[0])) {
            port = Integer.parseInt(args[1]);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("KVServer started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler
            implements Runnable {

        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }


        @Override
        public void run() {

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String input;
                while ((input = in.readLine()) != null) {
                    String[] parts = input.split(" ");
                    String command = parts[0];

                    switch (command) {
                        case "set":
                        case "SET":
                            String[] keyValue = parts[1].split("=");
                            String key = keyValue[0];
                            String value = keyValue[1];
                            store.put(key, value);
                            out.println("execute ok.");
                            break;

                        case "get":
                        case "GET":
                            key = parts[1];
                            value = store.getOrDefault(key, "NULL");
                            out.println("value:" + value);
                            break;

                        case "delete":
                        case "DELETE":
                            key = parts[1];
                            store.remove(key);
                            out.println("execute ok.");
                            break;

                        default:
                            out.println("ERROR: Unknown command");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
