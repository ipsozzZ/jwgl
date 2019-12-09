package com.mqb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mqb.dao.CourseDao;
import com.mqb.entity.Course;
import com.mqb.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Override
	public List<Course> findList(Map<String, Object> queryMap) {
		return courseDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return courseDao.getTotal(queryMap);
	}

	@Override
	public int add(Course course) {
		return courseDao.add(course);
	}


}
