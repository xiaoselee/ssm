package cn.test.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import cn.test.ws.MarcoHandler;



//@Configuration
//@EnableWebSocket
public class WsConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry arg0) {
		arg0.addHandler(marcoHandler(), "/marco");
		arg0.addHandler(marcoHandler(), "/marco").withSockJS();
	}
	
	@Bean
	public MarcoHandler marcoHandler() {
		return new MarcoHandler();
	}
	
	
}
