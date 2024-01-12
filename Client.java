package TP2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 5422);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(in);

        while (true){
            //Loop for displaying the Menu
            String line;
            for (int i = 0; i < 9; i++) {
                line = bufferedReader.readLine();
                System.out.println(line);
            }
            System.out.println(">");
            String Choice = scanner.nextLine();
            printWriter.println(Choice);
            if(Choice.equals("exit"))break;

            String Question = bufferedReader.readLine();
            System.out.println(Question);

            String salary = scanner.nextLine();
            printWriter.println(salary);

            String finalAnswer = bufferedReader.readLine();
            System.out.println(finalAnswer);
        }

        printWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
