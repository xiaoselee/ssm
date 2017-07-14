package cn.test.ws;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import cn.test.po.User;
import cn.test.service.MessageService;
import cn.test.web.ws.MessageDuf;

@Service
public class MarcoHandler extends TextWebSocketHandler{
	
	@Autowired
	private MessageService ms;
	
	public static Map<WebSocketSession,User> userSessionMap;
	static{
		userSessionMap = new ConcurrentHashMap<WebSocketSession,User>();
	}
	

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO 自动生成的方法存根
		super.afterConnectionEstablished(session);
		System.out.println("id:"+session.getId()+"建立连接："+new Date()+",加入map");
		String _id = session.getId();
		if(userSessionMap.get(session) == null){
			userSessionMap.put(session,new User(_id, "用户"+_id));
		}
		session.sendMessage(new TextMessage(ms.buildMessage("link", _id)));
		 ms.setUser(userSessionMap);
	        String message = ms.buildMessage("message",
	            String.format("[%s]进入了聊天室", "用户"+_id));
	        ms.publish(message);
	        ms.publish(ms.buildMessage("users", ms.getNames()));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO 自动生成的方法存根
		super.afterConnectionClosed(session, status);
		System.out.println("id:"+session.getId()+"关闭连接："+new Date()+",清除map");
        User user = userSessionMap.remove(session);
        String message = ms.buildMessage("message",
            String.format("[%s]离开了聊天室", user.getName()));
        ms.publish(message);		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO 自动生成的方法存根
		super.handleTextMessage(session, message);
		MessageDuf m = new MessageDuf();
		m.setId(session.getId());
		m.setInfo(message.getPayload());
		System.out.println("收到："+session.getId()+"发送的信息："+message.getPayload());
//		session.sendMessage(new TextMessage("来着服务器："+message.getPayload()));
		//session.sendMessage(new TextMessage(m.toString()));
		//sendAll(m);
        User user = userSessionMap.get(session);
        String messages = String.format("[%s]说：%s", user.getName(), message.getPayload());
        messages = ms.buildMessage("message", messages);
        ms.publish(messages);
		
	}
	
	public void sendAll(Object o) {
//		 Iterator<Entry<String, WebSocketSession>> it = userSessionMap
//	                .entrySet().iterator();
//	        // 多线程群发
//	        while (it.hasNext()) {
//	            final Entry<String, WebSocketSession> entry = it.next();
//	            if (entry.getValue().isOpen()) {
//	                new Thread(new Runnable() {
//	                    public void run() {
//	                        try {
//	                            if (entry.getValue().isOpen()) {
//	                                entry.getValue().sendMessage(new TextMessage(o.toString()));
//	                            }
//	                        } catch (IOException e) {
//	                            e.printStackTrace();
//	                        }
//	                    }
//	                }).start();
//	            }
//	 
//	        }
/*		
		for(Map.Entry<String, WebSocketSession> s : userSessionMap.entrySet()){
			final Map.Entry<String, WebSocketSession> e = s;
			final String m = new String(o.toString());
			if(e.getValue().isOpen()){
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							e.getValue().sendMessage(new TextMessage(m));
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}).start();;
			}
		}
	}
	
	public void sendToOne() {
		
	}
	*/
	}
}
