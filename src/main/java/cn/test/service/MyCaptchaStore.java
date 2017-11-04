package cn.test.service;

import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.google.gson.Gson;
import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.octo.captcha.service.captchastore.CaptchaStore;

import cn.test.util.SerializationUtil;

public class MyCaptchaStore implements CaptchaStore {

	@Autowired
	private RedisTemplate<String, Object> redis;

	@Override
	public void empty() {
		// TODO Auto-generated method stub

	}

	@Override
	public Captcha getCaptcha(String key) throws CaptchaServiceException {
		/*
		 * String value = JedisUtils.process(jedisCluster, jedis -> { return
		 * jedis.get(key); });
		 */
		CaptchaAndLocale captchaAndLocale = null;
		try {
			//byte[] b = (byte[]) redis.opsForValue().get(key);
			Object o = redis.opsForValue().get(key);
			captchaAndLocale = (CaptchaAndLocale) o;
					
			return captchaAndLocale != null ? (captchaAndLocale.getCaptcha()) : null;
		} catch (Exception e) {
			System.err.println("getCaptcha 错误：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Locale getLocale(String arg0) throws CaptchaServiceException {
		// TODO Auto-generated method stub
		CaptchaAndLocale captchaAndLocale = null;
		try {
			//byte[] b = (byte[]) redis.opsForValue().get(arg0);
			captchaAndLocale = (CaptchaAndLocale) redis.opsForValue().get(arg0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return captchaAndLocale != null ? (captchaAndLocale.getLocale()) : null;
	}

	@Override
	public Collection getKeys() {
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasCaptcha(String id) {
		// TODO Auto-generated method stub
		try {
			//byte[] b = (byte[]) redis.opsForValue().get(id);
			CaptchaAndLocale captcha = (CaptchaAndLocale) redis.opsForValue().get(id);
			return captcha == null ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeCaptcha(String arg0) {
		redis.delete(arg0);
		return false;
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha) throws CaptchaServiceException {
		try {
			CaptchaAndLocale captchaAndLocale = new CaptchaAndLocale(captcha);
			Gson g = new Gson();
			String _s = g.toJson(captchaAndLocale, CaptchaAndLocale.class);
			byte[] b = SerializationUtil.serialize(captchaAndLocale);
			System.out.println("生成captcha1：" + _s);
			redis.opsForValue().set(id, b);
			System.out.println("保存到redis。1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha, Locale locale) throws CaptchaServiceException {
		// TODO Auto-generated method stub
		try {
			CaptchaAndLocale captchaAndLocale = new CaptchaAndLocale(captcha, locale);
			Gson g = new Gson();
			String _s = g.toJson(captchaAndLocale, CaptchaAndLocale.class);
			System.out.println("生成captcha2：" + _s);
			//byte[] b = SerializationUtil.serialize(captchaAndLocale);
			redis.opsForValue().set(id, captchaAndLocale);
			System.out.println("保存到redis。2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
