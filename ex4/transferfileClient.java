import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class transferfileClient {
	Socket ClientSoc;

	DataInputStream din;
	DataOutputStream dout;
	BufferedReader br;

	transferfileClient(Socket soc) {
		try {
			ClientSoc = soc;
			din = new DataInputStream(ClientSoc.getInputStream());
			dout = new DataOutputStream(ClientSoc.getOutputStream());
			br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception ex) {
		
		}
	}
	
	public String esLoDoA() throws IOException {	//	escolher o local do arquivo
		System.out.print("Do you want to make a choice about the destin directory (Y/N) ?:\t");
		String Option = br.readLine();
		
		System.out.println();
		String dir = "";
		if (Option.toUpperCase().charAt(0) == 'Y') {
			System.out.print("Choose your preferred directory:\t");
			System.out.println();
			dir = br.readLine();
		}
		return dir;
	}
	
	void SendFile() throws Exception {
		
		String filename;
		System.out.print("Enter File Name:\t");
		filename = br.readLine();
		filename = "Nova pasta\\TRABALHO P1-SD-2021-1.pdf";
		filename = "TRABALHO P1-SD-2021-1.pdf";
		System.out.println("File Name:\t"+filename);
		
		String local = esLoDoA();		
		
		File f = new File(filename);
		if (!f.exists()) {
			System.out.println("File not Exists...");
			dout.writeUTF("File not found");
			return;
		}

		dout.writeUTF(filename);	//	envia o arquivo
		dout.writeUTF(local);
		
		String msgFromServer = din.readUTF();
		if (msgFromServer.compareTo("File Already Exists") == 0) {
			String Option;
			System.out.print("File Already Exists. Want to OverWrite (Y/N) ?:\t");
			Option = br.readLine();
			System.out.println();
			if (Option == "Y") {
				dout.writeUTF("Y");
			} else {
				dout.writeUTF("N");
				return;
			}
		}

		System.out.println("Sending File ...");
		FileInputStream fin = new FileInputStream(f);
				
		int ch;
		do {
			ch = fin.read();
			dout.writeUTF(String.valueOf(ch));
		} while (ch != -1);
		fin.close();
		System.out.println(din.readUTF());

	}

	void ReceiveFile() throws Exception {
		String fileName;
		System.out.print("Enter File Name :");
		fileName = br.readLine();
		
		String local = esLoDoA();
		
		dout.writeUTF(fileName);
		String msgFromServer = din.readUTF();

		if (msgFromServer.compareTo("File Not Found") == 0) {
			System.out.println("File not found on Server ...");
			return;
		} else if (msgFromServer.compareTo("READY") == 0) {
			System.out.println("Receiving File ...");
			File theDir = new File(local);
			if(!theDir.exists())
				theDir.mkdir();
			Path source = Paths.get(local, fileName);
			fileName = source.toString();
			
			File f = new File(fileName);
			if (f.exists()) {
				String Option;
				System.out.println("File Already Exists. Want to OverWrite (Y/N) ?");
				Option = br.readLine();
				if (Option == "N") {
					dout.flush();
					return;
				}
			}
			FileOutputStream fout = new FileOutputStream(f);
			int ch;
			String temp;
			do {
				temp = din.readUTF();
				ch = Integer.parseInt(temp);
				if (ch != -1) {
					fout.write(ch);
				}
			} while (ch != -1);
			fout.close();
			System.out.println(din.readUTF());

		}

	}

	public void displayMenu() throws Exception {
		while (true) {
			System.out.println("[ MENU ]\n");
			System.out.println("1. Send File");
			System.out.println("2. Receive File");
			System.out.println("3. Exit");
			System.out.print("\nEnter Choice:\t");
			int choice;
			choice = Integer.parseInt(br.readLine());
			if (choice == 1) {
				dout.writeUTF("SEND");
				SendFile();
			} else if (choice == 2) {
				dout.writeUTF("GET");
				ReceiveFile();
			} else {
				dout.writeUTF("DISCONNECT");
				System.exit(1);
			}
		}
	}
}