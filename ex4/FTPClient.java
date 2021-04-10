// FTP Client

import java.net.*;
import java.io.*;
import java.util.*;

class FTPClient {
	public static void main(String args[]) throws Exception {
		Socket soc = new Socket("192.168.0.209", 9876);
		transferfileClient t = new transferfileClient(soc);
		t.displayMenu();

	}
}
