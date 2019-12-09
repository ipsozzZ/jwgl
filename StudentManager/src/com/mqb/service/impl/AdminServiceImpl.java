package com.mqb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mqb.dao.AdminDao;
import com.mqb.entity.Admin;
import com.mqb.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin findByName(String name) {
		
		return adminDao.findByName(name);
	}

	@Override
	public int add(Admin admin) {
		
		return adminDao.add(admin);
	}
	
	@Override
	public int edit(Admin admin) {
		return adminDao.edit(admin);
	}
	

	@Override
	public int delete(Integer[] ids) {
		return adminDao.delete(ids);
	}

	@Override
	public List<Admin> findList(Map<String,Object> queryMap) {
		return adminDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> query) {
		return adminDao.getTotal(query);
	}

	
}
