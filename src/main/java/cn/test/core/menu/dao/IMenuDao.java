package cn.test.core.menu.dao;

import java.util.ArrayList;
import java.util.List;

import cn.test.po.Menu;

public interface IMenuDao {

	List<Menu> selectFromMenu();
	ArrayList<Menu> selectFromMenuByPageInfo();
	int addMenu(Menu menu);
	int updateMenu(Menu menu);
}
