package com.mqb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mqb.dao.TeacherDao;
import com.mqb.entity.Teacher;
import com.mqb.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public List<Teacher> findList(Map<String, Object> queryMap) {
		return teacherDao.findList(queryMap);
	}

	@Override
	public Teacher findByJno(String jno) {
		return teacherDao.findByJno(jno);
	}

	@Override
	public int add(Teacher teacher) {
		return teacherDao.add(teacher);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return teacherDao.getTotal(queryMap);
	}

	@Override
	public int edit(Teacher teacher) {
		return teacherDao.edit(teacher);
	}

	@Override
	public int delete(Integer[] ids) {
		return teacherDao.delete(ids);
	}

}
