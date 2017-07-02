package cn.test.common.po;

public class PageHandler<T> {
	
	private int page;
	private int limitStart;
	private int rows;
	private int total;
	private T objest;
	
	public int getLimitStart() {
		return (page-1)*10;
	}

	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}

	public PageHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public PageHandler(T entity) {
		this.objest = entity;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public T getObjest() {
		return objest;
	}
	public void setObjest(T objest) {
		this.objest = objest;
	}
	
	
	

}
