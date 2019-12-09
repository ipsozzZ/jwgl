package com.mqb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mqb.entity.Course;

@Repository
public interface CourseDao {

	List<Course> findList(Map<String, Object> queryMap);

	int getTotal(Map<String, Object> queryMap);

	int add(Course course);

}
