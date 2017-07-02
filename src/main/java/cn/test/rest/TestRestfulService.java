package cn.test.rest;

import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Path("/test")
@Scope("prototype")
public class TestRestfulService {
	
	
	@GET
	@Path("/test1")
    @Produces(MediaType.APPLICATION_JSON) 
	public Object name() {
		HashMap<String, String> m = new HashMap<>();
		m.put("name", "candy");
		m.put("type", "admin");
		m.put("time", new Date().toLocaleString());
		return m;
	}
	
	@GET
	@Path("/test2/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
	public Object name2(@PathParam("id") String id) {
		HashMap<String, String> m = new HashMap<>();
		m.put("name", "candy");
		m.put("type", "admin");
		m.put("id", id);
		return m;
	}

}
