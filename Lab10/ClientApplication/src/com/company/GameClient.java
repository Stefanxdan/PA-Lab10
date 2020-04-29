package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {

    public String getRequestFromKeyboard() throws IOException {
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Return reading data using readLine
        return reader.readLine();
    }


    public GameClient() {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(socket.getInputStream())) )
        {
            while (true) {
                String request = getRequestFromKeyboard();
                // Send a request to the server
                out.println(request);

                // iesim din bucla while(true) ceea ce va duce la inchiderea clientului
                if(request.equals("exit"))
                    break;

                // altfel:
                // Wait the response from the server (Server received the request)
                String response = in.readLine();
                System.out.println(response);

                // si iesim din bucla while(true)
                if(request.equals("stop"))
                    break;
            }
            System.out.println("App closed!");
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
