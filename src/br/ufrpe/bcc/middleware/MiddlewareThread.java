package br.ufrpe.bcc.middleware;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.json.simple.parser.ParseException;

import java.util.Map;

public abstract class MiddlewareThread implements Runnable {
	Socket s;

	public MiddlewareThread(Socket s) {
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
		ObjectInputStream inFromClient;
		inFromClient = new ObjectInputStream(s.getInputStream());

		String o = (String) inFromClient.readObject();
		return o;
	}

	public void reply(String msg) throws Exception {

		ObjectOutputStream saida = new ObjectOutputStream(s.getOutputStream());
		saida.writeObject(msg);
		s.close();

	}
}
