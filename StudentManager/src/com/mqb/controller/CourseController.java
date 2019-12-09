package com.mqb.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mqb.entity.Course;
import com.mqb.entity.Teacher;
import com.mqb.service.CourseService;
import com.mqb.service.TeacherService;
import com.mqb.util.Page;
import com.sun.org.glassfish.gmbal.ParameterNames;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@RequestMapping("/course")
@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("course/course_list");
		return model;
	}
	
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Page page){
		Map<String,Object> ret=new HashMap<>();
		Map<String,Object> queryMap=new HashMap<>();
		
		queryMap.put("name", "%"+name+"%");
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		
		ret.put("rows", courseService.findList(queryMap));
		ret.put("total", courseService.getTotal(queryMap));
		
		return ret;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Course course,
			@RequestParam("teacher_jno") String teacher_jno){
		Map<String,String> ret=new HashMap<>();
		
		System.out.println("i am add and teacher_jno is"+teacher_jno);
		
		Teacher teacher =teacherService.findByJno(teacher_jno);
		
		if(teacher==null) {
			ret.put("type", "error");
			ret.put("msg", "该教师不存在");
			return ret;
		}
		
		if(course==null) {
			ret.put("type", "error");
			ret.put("msg","数据绑定出错");
			return ret;
		}
		
		course.setId(teacher.getId());
//		course.
		
		//添加到数据库
//		if(!(courseService.add(course)>0)) {
//			ret.put("type", "error");
//			ret.put("msg", "课程添加失败");
//			return ret;
//		}
		
		
		ret.put("type", "success");
		ret.put("msg", "课程添加成功");
		return ret;	
	}
}
