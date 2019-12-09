package com.mqb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mqb.dao.ProfessionDao;
import com.mqb.entity.Profession;
import com.mqb.service.ProfessionService;

@Service
public class ProfessionServiceImpl implements ProfessionService {

	@Autowired
	private ProfessionDao professionDao;
	
	@Override
	public List<Profession> findList(Map<String,Object> queryMap) {
		return professionDao.findList(queryMap);
	}

	@Override
	public int add(Profession profession) {
		return professionDao.add(profession);
	}
	
	@Override
	public int edit(Profession profession) {
		return professionDao.edit(profession);
	}
	
	@Override
	public int delete(Integer[] ids) {
		return professionDao.delete(ids);
	}
	
	@Override
	public Profession findByName(String name) {
		return professionDao.findByName(name);
	}

	@Override
	public Profession findByPno(String pno) {
		return professionDao.findByPno(pno);
	}

	@Override
	public int getTotal(Map<String, Object> query) {
		return professionDao.getTotal(query);
	}

}
