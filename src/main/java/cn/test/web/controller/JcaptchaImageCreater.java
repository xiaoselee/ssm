package cn.test.web.controller;

import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.octo.captcha.service.image.ImageCaptchaService;

import cn.test.util.CaptchaUtil;

@Controller
@RequestMapping("security/captcha")
public class JcaptchaImageCreater {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*@Autowired
	private ImageCaptchaService imageCaptchaService;*/
	
	@Autowired
	private RedisTemplate<String, Object> redis;
	
	@RequestMapping
	public void handleRequest(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try {
			/*ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			String captchaId = "captcha_"+request.getSession().getId()+"_"+new Date().getTime();
			session.setAttribute("captchaId", captchaId);
			RenderedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0L);
			response.setContentType("image/jpeg");
			System.out.println("44");
			ImageIO.write(challenge, "jpeg", jpegOutputStream);
			byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
			System.out.println("47");
			ServletOutputStream respOs = response.getOutputStream();
			respOs.write(captchaChallengeAsJpeg);
			System.out.println("50");
			respOs.flush();
			respOs.close();	*/
			 //通知浏览器不要缓存  
		     response.setHeader("Expires","-1");//控制缓存的失效日期  
		     response.setHeader("Cache-Control","no-cache");//必须先与服务器确认返回的响应是否被更改，然后才能使用该响应来满足后续对同一个网址的请求  
		     response.setHeader("Pragma","-1");  
		     CaptchaUtil util=CaptchaUtil.Instance();  
		     // 将验证码输入到session中，用来验证    
		     String code=util.getString();
		     System.out.println("code:" + code);
		     String captchaId = "captcha_"+request.getSession().getId()+"_"+new Date().getTime();
		     session.setAttribute("captchaId", captchaId);
		     redis.opsForValue().set(captchaId, code);
		    // 输出到web页面    
		     ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generate captcha image error: {}", e.getMessage());
		}
	}

}