package com.mqb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mqb.entity.Course;

@Service
public interface CourseService {

	public List<Course> findList(Map<String, Object> queryMap);

	public int getTotal(Map<String, Object> queryMap);

	public int add(Course course);
	

}
