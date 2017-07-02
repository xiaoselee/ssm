package cn.test.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.test.common.po.DefaultRequest;
import cn.test.common.po.PageHandler;
import cn.test.common.po.Role;
import cn.test.dao.IRoleDao;

@Service
public class RoleService {

	@Resource
	private IRoleDao roleDao;
	
	public DefaultRequest<Role> getRoleList(PageHandler<Role> page) {
		DefaultRequest<Role> d = new DefaultRequest<Role>();
		try {
			ArrayList<Role> roleList = roleDao.getRoleList(page);
			//获取总数
			d.setTotal(100);
			//
			d.setRows(roleList);
		} catch (Exception e) {
			d.setType("1");
			d.setTotal(0);
			d.setRows(null);
		}

		return d;
	}

	public int save(Role role) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime = sdf.format(new Date());
		role.setCreatetime(createtime);
		role.setUpdatetime(createtime);
		int i = roleDao.insertRole(role);	
		return i;
	}
	
}