package TP2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    //Needed Variables for the Multi-Threading
    private static final int THREADS_MAX = 4;
    private static int THREADS_COUNT = 0;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(THREADS_MAX);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5422);

        while (true){
            System.out.println("Server is waiting for a connection...");
            Socket socket = serverSocket.accept();

            //Send a Message to the last connected client if the server is full
            THREADS_COUNT++;
            if (THREADS_COUNT > THREADS_MAX){
                PrintWriter pr = new PrintWriter(socket.getOutputStream(),true);
                pr.println("Server is Full.");
            }
            System.out.println("Client Connected : " + socket);

            //Creating a thread
            ClientHandler clientThread = new ClientHandler(socket);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}
