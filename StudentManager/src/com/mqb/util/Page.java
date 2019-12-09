package com.mqb.util;

import org.springframework.stereotype.Component;

@Component
public class Page {

	private int page;//当前页码
	private int rows;//每页显示数量
	private int offset;//从第offset条数据开始查询
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
	public int getOffset() {
		this.offset = (page-1)*rows;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = (page-1)*rows;
	}
	
	

	
}
