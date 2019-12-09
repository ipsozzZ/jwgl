package com.mqb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mqb.entity.Admin;

@Service
public interface AdminService {
	public Admin findByName(String name);
	public int add(Admin admin);
	public int edit(Admin admin);
	public int delete(Integer[] ids);
	public List<Admin> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> query);
}
