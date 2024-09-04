package com.chason.test.kv;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class KVClient {
    public static void main(String[] args) {

        System.out.println("Welcome kv client version:1.0.0.");
        System.out.println();

        while (true) {
            execute();
        }
    }


    private static void execute() {

        System.out.print("kvclient(1.0.0):");

        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();

        String[] args = cmd.split(" ");

        if (args[0].equalsIgnoreCase("quit")) {
            System.out.println("Bye");
            System.exit(0);
        }

        if (args.length < 5) {
            System.out.println("Usage: kvclient <command> <arguments> -h<host> -p<port>");
            return;
        }

        String command = args[1];
        String argument = args[2];
        String host = args[3].substring(2);
        int port = Integer.parseInt(args[4].substring(2));

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(command + " " + argument);
            String response = in.readLine();
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
