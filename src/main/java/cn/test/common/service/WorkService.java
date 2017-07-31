package cn.test.common.service;import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import cn.test.common.po.Work;


/**
 * 
 * @ClassName:  WorkService   
 * @Description:任务  WorkService
 * @author: shengte lee
 * @date:   2017年7月15日 下午8:02:53   
 *     
 * @Copyright: 2017  Inc. All rights reserved. 
 * 注意：
 */

@Service
public class WorkService {
	
	public void execWork() {
		ArrayList<Work> list = getWork();
		ArrayList<String> idList = new ArrayList<String>();
		ArrayList<String> idList_c = new ArrayList<String>();
		ArrayList<Work> List_e = new ArrayList<Work>();
		if(list != null && list.size() > 0){
			//获取id数组
			for(Work w : list){
				idList.add(w.getId());
			}
			//讲id数组内的任务设置为 执行中
			//....
			//判断执行周期类型，拼接时间
			filterWork(list, idList_c, List_e);
			//重新打开idList_c的任务
			if(idList_c.size() > 0){
				//...				
			}
			//执行idList_e的任务
			if(List_e.size() > 0){
				//...				
			}
		}
	}
	
	public ArrayList<Work> getWork() {
		return new ArrayList<Work>();
	}
	
	public void filterWork(ArrayList<Work> list,
			ArrayList<String> idList_c,ArrayList<Work> List_e) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("");
		for(Work w : list){
			String timetype = w.getTimetype();
			switch (timetype) {
			case "hh"://每小时
				
				break;
			case "mm"://每月
				
				break;
			case "ww"://每小时
				
				break;
			case "ds"://定时
				
				break;

			default:
				break;
			}
		}
		
	}
	
	public void execWorkList(ArrayList<Work> list) {
		for(Work w : list){
			
		}
	}
	
	public void execWork(Work work) {
		
	}

}
