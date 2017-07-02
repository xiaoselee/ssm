package cn.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.test.dao.ITestDao;
import cn.test.dao.TestDao;
import cn.test.po.PopLink;
import cn.test.po.User;

@Service("testservice")
@Transactional
public class TestServiceImpl implements ITestService {
	
	@Resource
	TestDao testdao;
	
	@Autowired
	ITestDao it;

	public List testService() {
		System.out.println("testService");
		//testdao.testDao();
		//List<PopLink> l = it.selectFromPopLink();
		List<User> l = it.selectFromUser();
		return l;
	}

}
