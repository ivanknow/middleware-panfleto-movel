package br.ufrpe.bcc.middleware.json;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.ContainerFactory;

public class JsonConteiner implements ContainerFactory{
	 public List creatArrayContainer() {
	      return new LinkedList();
	    }

	    public Map createObjectContainer() {
	      return new LinkedHashMap();
	    }
}
