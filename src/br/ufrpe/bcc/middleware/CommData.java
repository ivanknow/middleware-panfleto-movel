package br.ufrpe.bcc.middleware;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.ufrpe.bcc.negocio.Endereco;


public class CommData {
	private int port;
	private String host;
	ServerSocket welcomeSocket;
	Socket connectionSocket;
	Endereco rs;
	
	public CommData(Endereco rs) {
		this.port = Integer.parseInt(rs.getPort());
		this.host = rs.getIp();
	}

	
	public String requestAndReceive(String m) throws Exception {

		Socket clientSocket = null;
		clientSocket = new Socket(host, port);
		DataOutputStream outToServer;
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
	
		outToServer.writeUTF(m);
	
		String retorno = "";
		DataInputStream inFromClient; 
		inFromClient = new DataInputStream(clientSocket.getInputStream());
		


        StringBuffer inputLine = new StringBuffer();
        String tmp; 
        while ((tmp = inFromClient.readLine()) != null) {
            inputLine.append(tmp);
            }
		String s = inputLine.toString();
		clientSocket.close();

		return s;

}

	public String receive() throws Exception{
		welcomeSocket = new ServerSocket(port);
		connectionSocket = welcomeSocket.accept();
		ObjectInputStream inFromClient; 
		inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
		
		String o = (String)inFromClient.readObject();
		
		Thread.sleep(2);
		
		return o;
		
	}

		public void reply(String msg) throws Exception {
		
		ObjectOutputStream saida = new ObjectOutputStream(connectionSocket.getOutputStream());
		saida.writeObject(msg);
		welcomeSocket.close();
		connectionSocket.close();
		Thread.sleep(2);
	
	}
		
		public Socket receiveThread() throws Exception{
			welcomeSocket = new ServerSocket(port);
			Socket s = welcomeSocket.accept();
			welcomeSocket.close();
			return s;
		}

			
}
