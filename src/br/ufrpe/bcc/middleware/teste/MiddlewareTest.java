package br.ufrpe.bcc.middleware.teste;

import static org.junit.Assert.*;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import br.ufrpe.bcc.middleware.json.JsonConteiner;

public class MiddlewareTest {

	@Test
	public void test() {
		
		String mOut = new String();
		JSONParser parser = new JSONParser();
		try {
			JSONObject request = new JSONObject();
			request.put("op", "soma");
			JSONObject data = new JSONObject();
			data.put("a",1);
			request.put("data", data);
			
			Map json = (Map)parser.parse(request.toJSONString(), new JsonConteiner());
			String op = (String) json.get("op");
			System.out.println(op);
			System.out.println(((Map)json.get("data")).get("a"));
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}

}
