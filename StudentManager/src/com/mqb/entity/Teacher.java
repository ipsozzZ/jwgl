package com.mqb.entity;

import org.springframework.stereotype.Component;

@Component
public class Teacher {
	
	int id;
	String jno;//教师工号
	String profession;//教师的专业
	String phone;
	String pass;
	String email;
	String name;
	String logo;
	String intro;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJno() {
		return jno;
	}
	public void setJno(String jno) {
		this.jno = jno;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", jno=" + jno + ", profession=" + profession + ", phone=" + phone + ", pass="
				+ pass + ", email=" + email + ", name=" + name + ", intro=" + intro + "]";
	}
	
	
	
}
