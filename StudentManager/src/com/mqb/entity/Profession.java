package com.mqb.entity;

import org.springframework.stereotype.Component;

@Component
public class Profession {
	int id;
	String name;//专业名称
	String pno;//专业编号
	String intro;//专业简介
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
}
