/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.kieckegard.github.port.scanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Pedro Arthur
 */
public class PortScanner {
    
    public static boolean isPortOpen(String ip, int port, int timeout) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String ip, portRange;
        int initialPort, lastPort;
        
        System.out.print("Enter the IP address: ");
        ip = scanner.nextLine();
        if(ip.isEmpty()) {
            System.out.println("You must specify a host!");
            return;
        }
        
        System.out.print("Enter the port range (Ex. 10000-65000): ");
        portRange = scanner.nextLine();
        String[] ports = portRange.split("-");
        
        int count = 0;
        int portsLength = ports.length;
        try {
            initialPort = Integer.valueOf(ports[0]);
        
            if(portsLength > 1) {  
                lastPort = Integer.valueOf(ports[portsLength-1]);
            } else {
                lastPort = Integer.valueOf(ports[0]);
            }
        } catch (NumberFormatException ex) {
            System.out.println("You must specify at least one valid port.");
            return;
        }
        
        for(int port = initialPort; port <= lastPort; port++) {
            boolean result = isPortOpen(ip, port, 200);
            if(result) {
                count++;
                System.out.println("Port " + port + " is open! ");
            } else {
                System.out.println("Port " + port + " is not open!");
            }
        }
        
        if(count > 0) { 
            System.out.println("There are " + count + " ports open!");
        } else {
            System.out.println("There aren't open ports.");
        }
    }
}
