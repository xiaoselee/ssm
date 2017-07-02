package cn.test.common.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import cn.test.po.User;

public interface UserRealmDao {
	
	ArrayList<User> selectFromUser(User user);
	
	HashSet<String> slectUserRole(User user);
	
	HashSet<String> slectRolePermission(User user);

	List<User> selectFromUserById(User user);
	
}
