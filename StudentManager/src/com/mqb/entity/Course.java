package com.mqb.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Course {
	int id;
	int cno;//�γ̱��
	int tid;//���ν�ʦ���
	Date date;//��������
	String name;//�γ�����
	String intro;//300���ڿγ̽���
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}
