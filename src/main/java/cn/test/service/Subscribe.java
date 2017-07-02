package cn.test.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.test.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

//@Service
public class Subscribe {

	@Autowired
	public Subscribe(@Qualifier("messageService") final MessageService ms) {
		System.out.println("初始化 Jedis Subscribe 监听服务");
		new Thread(new Runnable() {

			@Override
			public void run() {
				Jedis jedis = RedisUtil.getJedis();

				jedis.subscribe(new JedisPubSub() {

					@Override
					public void onUnsubscribe(String channel, int subscribedChannels) {
						System.out.println("onUnsubscribe:" + channel);
					}

					@Override
					public void onSubscribe(String channel, int subscribedChannels) {
						System.out.println("---------->onSubscribe:" + channel);
					}

					@Override
					public void onPUnsubscribe(String pattern, int subscribedChannels) {
						System.out.println("onPUnsubscribe:" + pattern);
					}

					@Override
					public void onPSubscribe(String pattern, int subscribedChannels) {
						System.out.println("onPSubscribe:" + pattern);
					}

					@Override
					public void onPMessage(String pattern, String channel, String message) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onMessage(String channel, String message) {
						System.out.print("onMessage:" + channel);
						System.out.println("\t" + message);
						try {
							ms.broadcast(message);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}, MessageService.REDIS_CHANNEL);
			}
		}).start();
		;
	}

}
