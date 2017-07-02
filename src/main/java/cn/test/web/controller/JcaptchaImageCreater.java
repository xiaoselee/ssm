package cn.test.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
@RequestMapping("security/captcha")
public class JcaptchaImageCreater {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	

	
	@RequestMapping
	public void handleRequest(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try {
			ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			String captchaId = "captcha_"+request.getSession().getId()+"_"+new Date().getTime();
			session.setAttribute("captchaId", captchaId);
			BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());
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
			respOs.close();	
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("generate captcha image error: {}", e.getMessage());
		}
	}

}