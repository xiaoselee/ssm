package cn.test.dao;

import java.util.ArrayList;

import cn.test.common.po.PageHandler;
import cn.test.common.po.Role;

public interface IRoleDao {

	ArrayList<Role> getRoleList(PageHandler<Role> page);

	int insertRole(Role role);

}
