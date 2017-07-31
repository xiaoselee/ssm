package cn.test.common.po;

/**
 * 
 * @ClassName:  Work   
 * @Description:任务实体类 
 * @author: shengte lee
 * @date:   2017年7月15日 下午7:48:01   
 *     
 * @Copyright: 2017  Inc. All rights reserved. 
 * 注意：
 */
public class Work {
	
	String id;
	String type;//类型：存储过程、类方法
	String timetype;//执行周期类型
	String name;
	String workname;//存储过程名或类方法名;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTimetype() {
		return timetype;
	}
	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkname() {
		return workname;
	}
	public void setWorkname(String workname) {
		this.workname = workname;
	}
	
	
	

}
