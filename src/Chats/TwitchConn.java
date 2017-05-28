/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chats;

/**
 *
 * @author Алексей
 */
import java.net.*;
import java.io.*;
import java.util.*;

class TwitchConn {

    static void sendString(BufferedWriter bw, String str) {
        try {
            bw.write(str + "\r\n");
            bw.flush();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void main(String args[]) {
        try {

            String TwitchServer = "irc.chat.twitch.tv";
            int port = 6667;
            String nickname = "tsunamiwarning";
            String password = "oauth:xq0dob4aq994ccbmz5yvzx236tkq8g";
           // String channel = "#twaryna";
            String channel = "#candlelightsm";
            //oauth:sac48jmrafgifwsfxnipqx61finv28 - password
            Socket socket = new Socket(TwitchServer, port);
            System.out.println("*** Connected to server->" + socket.isConnected());
            OutputStreamWriter outputTwitch = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter writerTwitch = new BufferedWriter(outputTwitch);
            sendString(writerTwitch, "PASS " + password);
            sendString(writerTwitch, "NICK " + nickname);
            sendString(writerTwitch, "JOIN " + channel);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // sendString(bwriter, "PRIVMSG "+ channel + " :Kappa Keepo Kappa");
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
//                if (line.indexOf("004") >= 0) {
//                      System.out.println("0004");
//                    break;
//                } else if (line.indexOf("433") >= 0) {
//                    System.out.println("Nickname is already in use.");
//                    return;
//                }
            }

//            sendString(bwriter, "NICK " + nickname);
//            sendString(bwriter, "USER chatterBot  8 * :chatterBot 0.0.1 Java IRC Bot - www.chat.org");
//            sendString(bwriter, "JOIN " + channel);
//                        sendString(bwriter, "PRIVMSG " + channel + " :" + message);
            /*
      InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
      BufferedReader breader = new BufferedReader(inputStreamReader);
      String line = null;
      int tries = 1;
      while ((line = breader.readLine()) != null) {
        System.out.println(">>> "+line);
        int firstSpace = line.indexOf(" ");
        int secondSpace = line.indexOf(" ", firstSpace + 1);
        if (secondSpace >= 0) {
          String code = line.substring(firstSpace+1, secondSpace);
          if (code.equals("004")) {
            break;
          }
        }
      }
             */
            writerTwitch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
