package TP2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static TP2.Services.*;

public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        printWriter = new PrintWriter(client.getOutputStream(),true);
    }

    @Override
    public void run() {
        try {
            while(true){
                printWriter.println(menu());
                String Choice = bufferedReader.readLine();
                //Question (in client side)
                printWriter.println("How much is your salary ?");
                double salary = Double.parseDouble(bufferedReader.readLine());
                //finalAnswer (in client side)
                switch (Choice){
                    case "1" : printWriter.println("Your salary after Tax is " + Tax(salary)); break;
                    case "2" : printWriter.println("Your salary after Insurance is " + Insurance(salary)); break;
                    case "3" : printWriter.println("Your salary after the added Bonus is " + Bonus(salary)); break;
                    case "4" : printWriter.println("Your net salary is " + SalaireNet(salary)); break;
                    default: printWriter.println("Choose an option please."); break;
                }
            }
        } catch (IOException e) {
            System.err.println("An error has occurred on the client handler side");
            System.err.println(e.getStackTrace());
        } finally {
            System.out.println("Client Disconnected : " + client);
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
