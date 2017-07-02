package cn.test.common.realm;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.test.common.po.Role;
import cn.test.common.service.UserRealmService;
import cn.test.common.util.SystemConfig;
import cn.test.po.User;



public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserRealmService urs;
	
	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String userid = (String) principals.getPrimaryPrincipal();
		System.out.println(new Date().toLocaleString()+" UserRealm："+userid+" 进行 获取授权信息");
		System.out.println(new Date().toLocaleString()+" UserRealm："+userid+" 进行 获取角色信息");
		User user = new User();
		user.setId(userid);
		List<User> userList = urs.getUserById(user);		
		if(userList != null && userList.size() > 0){
			HashSet<String> roleList = urs.getUserRole(userList.get(0));
			if(roleList != null && roleList.size() > 0){
				//获取角色
				System.out.println("角色："+roleList);
				authorizationInfo.setRoles(roleList);				
				//获取权限
				System.out.println(new Date().toLocaleString()+" UserRealm："+userid+" 进行 获取权限信息。");
				HashSet<String> permissionsbyquery = urs.getRolePermission(userList.get(0));
				if(permissionsbyquery != null && permissionsbyquery.size()>0 ){			
					System.out.println("权限："+permissionsbyquery);
					authorizationInfo.setStringPermissions(permissionsbyquery);
				}
			}
			System.out.println(new Date().toLocaleString()+" UserRealm："+userid+" 无角色信息。");
		}
		return authorizationInfo;
		
	}

	/**
	 * 身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username = (String) authenticationToken.getPrincipal();
		String password = new String((char[]) authenticationToken.getCredentials());
		System.out.println(new Date().toLocaleString()+" UserRealm："+username+" 进行 身份验证");
		//Result<RcUser> result = biz.login(userno, password);此处应从数据库中查询出用户名和加密后的密码
		User user = new User();
		user.setName(username);
		try {
			List<User> userList = urs.getUser(user);
			if (userList != null && userList.size()>0) {
				Session session = SecurityUtils.getSubject().getSession();
				session.setAttribute(SystemConfig.Systemname, userList.get(0));
				SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(userList.get(0).getId(), userList.get(0).getPassword(), getName());
				return ai;
			}
			System.out.println(new Date().toLocaleString()+" UserRealm："+username+" 不存在");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
