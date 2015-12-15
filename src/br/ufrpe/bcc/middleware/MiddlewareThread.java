package br.ufrpe.bcc.middleware;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.simple.parser.ParseException;

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
		/*ObjectInputStream inFromClient;
		inFromClient = new ObjectInputStream(s.getInputStream());

		String o = (String) inFromClient.readObject();
		return o;*/

		InputStream in = s.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int n;
		for (n = in.read(buffer); 0 < n; n = in.read(buffer))
		{
		    out.write(buffer, 0, n);
		}
		out.flush();
		byte[] blob = out.toByteArray();
		
		return new String(blob);
	}

	public void reply(String msg) throws Exception {

		OutputStream saida = s.getOutputStream();
		saida.write(msg.getBytes());
		s.close();

	}
}
