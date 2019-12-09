package com.mqb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mqb.entity.Profession;

@Repository
public interface ProfessionDao {
	
	public List<Profession> findList(Map<String,Object> queryMap);
	
	public int add(Profession profession);
	
	public int edit(Profession profession);

	public int delete(Integer[] ids);
	
	public Profession findByName(String name);

	public Profession findByPno(String pno);
	
	public int getTotal(Map<String,Object> query);


}
