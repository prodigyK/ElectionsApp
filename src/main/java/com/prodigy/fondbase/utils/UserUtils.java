package com.prodigy.fondbase.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserUtils {

    public static String getIp(){
        String ip = "";
        try (final DatagramSocket socket = new DatagramSocket()) {

            URL whatIsMyIp = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatIsMyIp.openStream()));

            ip = in.readLine(); //you get the IP as a String

        } catch (Exception e) {

        }



        return ip;
    }

    public static String getCurrentDateTime(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }
}
