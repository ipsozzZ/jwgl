package com.mqb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mqb.entity.Admin;

@Repository
public interface AdminDao {

	public Admin findByName(String name);
	
	public int add(Admin admin);
	
	public int edit(Admin admin);
	
	public int delete(Integer[] ids);
	
	public List<Admin> findList(Map<String,Object> queryMap);
	
	public int getTotal(Map<String,Object> query);
}
