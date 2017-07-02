package cn.test.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

import cn.test.po.User;
import cn.test.util.RedisUtil;
import redis.clients.jedis.Jedis;

@Service
public class MessageService {
	
	public static final String REDIS_CHANNEL = "WS";
	private Map<WebSocketSession, User> users;
	
	
    /**推送消息*/
    public void broadcast(String message) throws IOException {
    	if(users != null && users.size() > 0){
            for (WebSocketSession session : users.keySet()) {
                session.sendMessage(new TextMessage(message));
            }
            System.out.println("消息已群发！");
    	}else{
    		System.out.println("users集合为0");
    	}
    }
	
	public void publish(String message) {
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if(jedis == null){
				System.out.println("jedis 为空");
			}else{
				jedis.publish(REDIS_CHANNEL, message);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally {
            if (jedis != null) {
                jedis.close();
            }
		}	
	}
	
    public String buildMessage(String type, String message) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("value", message);
        Gson g = new Gson();       
        return g.toJson(map);
    }
	
    public String getNames() {
        StringBuilder names = new StringBuilder("<b>在线用户</b><br>");
        for (User userInfo : users.values()) {
            names.append(userInfo.getName()).append("<br>");
        }
        return names.toString();
    }
	
	public void setUser(Map<WebSocketSession, User> users) {
		this.users = users;
	}

}
