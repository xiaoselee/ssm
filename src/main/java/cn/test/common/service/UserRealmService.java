package cn.test.common.service;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.test.common.dao.UserRealmDao;

import cn.test.po.User;

@Service
public class UserRealmService {

	@Resource
	UserRealmDao urdao;
	
	public List<User> getUser(User user){
		List<User> l = null;
		try
		{
			 l = urdao.selectFromUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}
	
	
	public List<User> getUserById(User user) {
		List<User> l = null;
		try
		{
			 l = urdao.selectFromUserById(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}
	
	public HashSet<String> getUserRole(User user){
		HashSet<String> l = null;
		try {
			 l = urdao.slectUserRole(user);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return l;
	}
	
	public HashSet<String> getRolePermission(User user){
		HashSet<String> l = urdao.slectRolePermission(user);
		return l;
	}
	
	
}
