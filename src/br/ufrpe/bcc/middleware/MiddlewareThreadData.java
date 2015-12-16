package br.ufrpe.bcc.middleware;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.simple.parser.ParseException;

public abstract class MiddlewareThreadData implements Runnable {
	Socket s;

	public MiddlewareThreadData(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		String m;
		try {
			m = recieve();

			reply(exec(m));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public abstract String exec(String m) throws ParseException;

	private String recieve() throws IOException, ClassNotFoundException {
		/*
		 * ObjectInputStream inFromClient;
		 *  inFromClient = new
		 * ObjectInputStream(s.getInputStream());
		 * 
		 * String o = (String) inFromClient.readObject();
		 *  return o;
		 */
		DataInputStream inFromClient = new DataInputStream(s.getInputStream());

		StringBuffer inputLine = new StringBuffer();
		String tmp;
		while ((tmp = inFromClient.readLine()) != null) {
			inputLine.append(tmp);
		}

		return inputLine.toString();
	}

	public void reply(String msg) throws Exception {

		/*ObjectOutputStream saida = new ObjectOutputStream(s.getOutputStream());
		saida.writeObject(msg);
		s.close();*/
		
		DataOutputStream outToServer = new DataOutputStream(s.getOutputStream());
		outToServer.writeUTF(msg);
		outToServer.close();
		s.close();
	}
}
