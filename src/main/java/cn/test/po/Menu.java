package cn.test.po;

import java.util.ArrayList;

public class Menu {
	
	private String id;
	private String name;
	private String type;
	private String pid;
	private String status;
	private String createTime;
	private String updateTime;
	private String creater;
	private int order;
	private int reviser;
	private String url;
	private String icon;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private ArrayList<Menu> sunList;
	
	public ArrayList<Menu> getSunList() {
		if(this.sunList == null){
			this.sunList = new ArrayList<>();
		}
		return sunList;
	}
	public void setSunList(ArrayList<Menu> sunList) {
		this.sunList = sunList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getReviser() {
		return reviser;
	}
	public void setReviser(int reviser) {
		this.reviser = reviser;
	}
	

}
