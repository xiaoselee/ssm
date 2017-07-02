package cn.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.test.po.PopLink;
import cn.test.po.User;

public interface ITestDao {	
	public List<PopLink> selectFromPopLink();
	

	List<User> selectFromUser();

}
