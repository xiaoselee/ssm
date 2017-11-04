package cn.test.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.octo.captcha.service.image.ImageCaptchaService;

import cn.test.common.controller.BaseController;
import cn.test.common.util.SystemConfig;
import cn.test.po.Result;
import cn.test.po.User;

@Controller
public class LoginController extends BaseController {

	/*@Autowired
	private ImageCaptchaService imageCaptchaService;*/

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView toRoot(HttpSession session) {
		String _url = "redirect:/security/login";
		Object token = session.getAttribute(SystemConfig.Systemname);
		if (token != null) {
			_url = "redirect:/system/main";
		}
		return new ModelAndView(_url);
	}

	@RequestMapping(value = "/security/login", method = RequestMethod.GET)
	public ModelAndView toLoginPage(HttpSession session) {
		String _url = "login/index.jsp";
		Object token = session.getAttribute(SystemConfig.Systemname);
		Session session2 = SecurityUtils.getSubject().getSession();
		Object o = session2.getAttribute(SystemConfig.Systemname);
		if (o != null) {
			_url = "redirect:/system/main";
		}
		return new ModelAndView(_url);
	}

	@Autowired
	private RedisTemplate<String, Object> redis;
	
	@RequestMapping(value = "/security/login", method = RequestMethod.POST)
	@ResponseBody
	public Object Login(HttpServletRequest request, User user, HttpSession session, String captcha) {
		Boolean result = Boolean.FALSE;
		Result<String> re = new Result<String>();
		Subject subject = SecurityUtils.getSubject();
		Boolean isResponseCorrect = Boolean.FALSE;
		System.out.println("id: "+request.getSession().getId());
		String captchaId = (String) session.getAttribute("captchaId");
		try {
			String code = (String) redis.opsForValue().get(captchaId);
			if(code.equalsIgnoreCase(captcha)){
				isResponseCorrect = true;
			}
			//isResponseCorrect = imageCaptchaService.validateResponseForID(captchaId, captcha);
		} catch (Exception e) {
			re.setStatus(false);
			re.setErrMsg("二维码已过期");
			return re;
		}
		if (isResponseCorrect) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
			token.setRememberMe(true);
			try {
				// 登录，即身份验证
				subject.login(token);
				re.setStatus(true);
			} catch (AuthenticationException e) {
				// 身份验证失败
				System.err.println(e.getLocalizedMessage());
				re.setStatus(false);
				re.setErrMsg("身份验证失败");
			}
		} else {
			re.setStatus(false);
			re.setErrMsg("验证码错误");
		}
		return re;
	}

	@RequestMapping(value = "/successlogin")
	public ModelAndView successLogin() {
		return new ModelAndView("index.jsp");
	}

	@RequestMapping("/security/nonauth")
	public ModelAndView name(HttpSession session) {
		Object token = session.getAttribute(SystemConfig.Systemname);
		ModelAndView m = new ModelAndView();
		if (token != null) {
			m.addObject("noauth", "true");
		} else {
			m.addObject("noauth", "false");
		}
		m.setViewName("common/403.jsp");
		return m;
	}

	/**
	 * 退出登陆操作
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String exit(RedirectAttributes redirectAttributes, HttpSession session) {
		session.removeAttribute(SystemConfig.Systemname);
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("msg", "您已经安全退出");
		return redirect("/login");
	}

}
