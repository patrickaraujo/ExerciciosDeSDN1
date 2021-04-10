// FTP Server

import java.net.*;
import java.io.*;
import java.util.*;

public class FTPServer {
	public static void main(String args[]) throws Exception {
		int porta = 9876;
		ServerSocket soc = new ServerSocket(porta);
		System.out.println("FTP Server Started on Port Number "+porta);
		while (true) {
			System.out.println("Waiting for Connection ...");
			transferfile t = new transferfile(soc.accept());
		}
	}
}