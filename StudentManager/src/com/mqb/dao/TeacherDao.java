package com.mqb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mqb.entity.Teacher;

@Repository
public interface TeacherDao {

	public List<Teacher> findList(Map<String, Object> queryMap);

	public Teacher findByJno(String jno);

	public int add(Teacher teacher);

	public int getTotal(Map<String, Object> queryMap);

	public int edit(Teacher teacher);

	public int delete(Integer[] ids);

}
