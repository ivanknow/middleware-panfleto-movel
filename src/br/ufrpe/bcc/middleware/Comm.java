package br.ufrpe.bcc.middleware;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.ufrpe.bcc.negocio.Endereco;
import sun.misc.IOUtils;


public class Comm {
	private int port;
	private String host;
	ServerSocket welcomeSocket;
	Socket connectionSocket;
	Endereco rs;
	
	public Comm(Endereco rs) {
		this.port = Integer.parseInt(rs.getPort());
		this.host = rs.getIp();
	}

	
	public String requestAndReceive(String m) throws Exception {

		Socket clientSocket = null;
		clientSocket = new Socket(host, port);
		OutputStream outToServer;
		outToServer =	clientSocket.getOutputStream();
	
		outToServer.write(m.getBytes());
	
		String retorno = null;
		InputStream inFromClient; 
		inFromClient = clientSocket.getInputStream();
		
		retorno = new String(IOUtils.readFully(inFromClient, 0, true));
		
		clientSocket.close();

		return retorno;

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
