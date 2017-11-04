package cn.test.core.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.common.po.DefaultRequest;
import cn.test.common.po.PageHandler;
import cn.test.common.po.Role;
import cn.test.core.menu.dao.IMenuDao;
import cn.test.po.Menu;

@Service
public class MenuService {

	@Autowired
	private IMenuDao menuDao;
	
	
	public List<Menu> getMenuList() {
		List<Menu> l = menuDao.selectFromMenu();
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		//生成根目录
		for(Menu menu : l){
			String pid = menu.getPid();
			if(pid.equals("0") || pid == null){
				menuList.add(menu);
				menu.setSunList(getSunList(menu.getId(),l));
			}
		}
		
		return menuList;
	}


	private ArrayList<Menu> getSunList(String pid, List<Menu> l) {
		ArrayList<Menu> sunList = new ArrayList<Menu>();
		for(Menu menu : l){
			if(menu.getPid().equals(pid)){
				sunList.add(menu);
				menu.setSunList(getSunList(menu.getId(), l));
			}
		}
		if(sunList.size() < 0){
			return null;
		}
		return sunList;
	}
	
	public DefaultRequest<Menu> getMenuList(PageHandler<Menu> page) {
		DefaultRequest<Menu> d = new DefaultRequest<>();
		ArrayList<Menu> l = menuDao.selectFromMenuByPageInfo();
		if(l != null && l.size() > 0){
			d.setRows(l);
			d.setTotal(100);
		}
		return d;		
	}


	public DefaultRequest<Menu> addMenu(Menu menu) {
		DefaultRequest<Menu> d = new DefaultRequest<>();
		ArrayList<Menu> l = new ArrayList<>();
		try {
			int i = menuDao.addMenu(menu);
			System.out.println(i);
			l.add(menu);
			d.setRows(l);	
			d.setType("0");
		} catch (Exception e) {
			d.setType("1");
		}
		return d;
	}


	public DefaultRequest<Menu> updateMenu(Menu menu) {
		DefaultRequest<Menu> d = new DefaultRequest<>();
		ArrayList<Menu> l = new ArrayList<>();
		try {
			int i = menuDao.updateMenu(menu);
			if(i != 0){
				d.setType("0");
			}
		} catch (Exception e) {
			d.setType("1");
		}
		return d;
	}
	
}
