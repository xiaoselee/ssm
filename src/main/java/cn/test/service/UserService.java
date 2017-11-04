package cn.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.common.po.DefaultRequest;
import cn.test.common.po.PageHandler;
import cn.test.common.po.Role;
import cn.test.core.menu.dao.IMenuDao;
import cn.test.dao.IUserDao;
import cn.test.po.Menu;
import cn.test.po.User;

@Service
public class UserService {

	@Autowired
	private IUserDao userDao;
	
	public DefaultRequest<User> add(User user) {
		DefaultRequest<User> d = new DefaultRequest<>();
		ArrayList<User> l = new ArrayList<>();
		try {
			int i = userDao.add(user);
			System.out.println(i);
			l.add(user);
			d.setRows(l);	
			d.setType("0");
		} catch (Exception e) {
			d.setType("1");
		}
		return d;
	}


	public DefaultRequest<User> update(User user) {
		DefaultRequest<User> d = new DefaultRequest<>();
		ArrayList<User> l = new ArrayList<>();
		try {
			int i = userDao.update(user);
			if(i != 0){
				d.setType("0");
			}
		} catch (Exception e) {
			d.setType("1");
		}
		return d;
	}


	public DefaultRequest<User> list(PageHandler<User> page) {
		DefaultRequest<User> d = new DefaultRequest<>();
		ArrayList<User> l =  userDao.list(page);
		if(l != null && l.size() > 0){
			d.setRows(l);
			d.setType("0");			
		}else{
			d.setType("1");
		}
		return d;
	}



	
}
