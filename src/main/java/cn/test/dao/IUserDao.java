package cn.test.dao;

import java.util.ArrayList;
import cn.test.common.po.PageHandler;
import cn.test.po.User;

public interface IUserDao {
	int add(User user);
	int update(User user);
	ArrayList<User> list(PageHandler<User> page);

}
