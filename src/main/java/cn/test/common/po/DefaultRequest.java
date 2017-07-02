package cn.test.common.po;

import java.util.ArrayList;

public class DefaultRequest<T>  {

	private String type;
	private String message;
	private int total;
	private ArrayList<T> rows;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<T> getRows() {
		return rows;
	}
	public void setRows(ArrayList<T> rows) {
		this.rows = rows;
	}
	
}
