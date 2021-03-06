package com.company;

import com.company.game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ClientThread extends Thread {
    private final ServerSocket serverSocket;
    private final Socket socket;
    //Player player;

    //Pentru a putea inchide serverSocketul transmitem serverSocketul ca parametru
    public ClientThread(ServerSocket serverSocket,Socket socket) {
        this.serverSocket = serverSocket;
        this.socket = socket;
    }

    public void closeServerSocket() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (Throwable ignored) {}
        }
    }

    public void run() {
        try {
            //player = new Player();
            while (true) {
                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();

                // Send the response to the oputput stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                if (request.equals("exit"))
                    // atunci clientul s-a inchis deci nu mai trimitem niciun response
                    // si iesim din bucla while(true)
                    break;
                if (request.equals("stop")) {
                    // incidem serverSocketul si trimitem mesajul aferent
                    // si iesim din bucla while(true)
                    closeServerSocket();
                    out.println("Server stopped");
                    out.flush();
                    break;
                }
                // alfel trimitem confirmarea primiri mesajului si continuam bucla while(true)
                out.println("Server received the request ... " + request );
                out.flush();
            }
            System.out.println("Client: end!");

        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
    }
}
