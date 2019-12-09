package com.mqb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mqb.entity.Teacher;

@Service
public interface TeacherService {
	
	public List<Teacher> findList(Map<String,Object> queryMap);
	
	public int getTotal(Map<String,Object> queryMap);

	public Teacher findByJno(String jno);

	public int add(Teacher teacher);

	public int edit(Teacher teacher);

	public int delete(Integer[] ids);
}
