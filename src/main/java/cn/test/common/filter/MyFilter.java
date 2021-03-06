package cn.test.common.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.test.common.dao.CommonDao;
import cn.test.common.util.SystemConfig;

/**
 * 
 * @Type MyFilter
 * @Desc 用于自定义过滤器，过滤用户请求是否被授权
 * @author shengte Lee
 * @date 2017年5月26日
 * @version
 */
public class MyFilter extends AuthorizationFilter {

	@Autowired
	public CommonDao dao;

	@SuppressWarnings("unchecked")
	@Override
	protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object arg2) throws Exception {
		HttpServletRequest request = (HttpServletRequest) req;
		// 获取请求路径
		String path = request.getServletPath();
		Subject subject = getSubject(req, resp);
		PrincipalCollection pc = subject.getPrincipals();
		if (null != pc) {
			// 根据session中存放的用户权限，比对路径，如果拥有该权限则放行
			Set<String> userPrivileges = (Set<String>) request.getSession().getAttribute(SystemConfig.PRIVILEGES);
			if (null != userPrivileges && userPrivileges.contains(path)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 会话超时或权限校验未通过的，统一返回401，由前端页面弹窗提示
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		if (isAjax((HttpServletRequest) request)) {
			WebUtils.toHttp(response).sendError(401);
		} else {
			String unauthorizedUrl = getUnauthorizedUrl();
			if (StringUtils.hasText(unauthorizedUrl)) {
				WebUtils.issueRedirect(request, response, unauthorizedUrl);
			} else {
				WebUtils.toHttp(response).sendError(401);
			}
		}

		return false;
	}

	private boolean isAjax(HttpServletRequest request) {
		String header = request.getHeader("x-requested-with");
		if (null != header && "XMLHttpRequest".endsWith(header)) {
			return true;
		}
		return false;
	}

}
