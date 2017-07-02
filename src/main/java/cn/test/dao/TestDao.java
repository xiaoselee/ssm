package cn.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import cn.test.po.PopLink;

@Repository
public class TestDao {
	
	@Resource
	private SqlSessionFactory ssf;
	
	public void testDao() {
		System.out.println("testDao");
		
		List<PopLink> l = ssf.openSession().selectList("test.selectFromPopLink");
		ssf.openSession().close();
		System.out.println("l size is :"+l.size());
		
	}

}
