package com.mqb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mqb.entity.Profession;

@Service
public interface ProfessionService {
	
	public List<Profession> findList(Map<String,Object> queryMap);
	
	public int add(Profession profession);
	
	public int edit(Profession profession);
	
	public Profession findByName(String name);

	public Profession findByPno(String pno);
	
	public int getTotal(Map<String,Object> query);

	public int delete(Integer[] ids);

	
	
}
