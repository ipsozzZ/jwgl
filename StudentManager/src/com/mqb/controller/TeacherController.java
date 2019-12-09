package com.mqb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mqb.entity.Teacher;
import com.mqb.service.TeacherService;
import com.mqb.util.Page;

@RequestMapping("/teacher")
@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("teacher/teacher_list");
		return model;
	}
	
	
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="name",required=false,defaultValue="") String name,
			@RequestParam(value="jno",required=false,defaultValue="") String jno,
			Page page
			){
		Map<String,Object> ret=new HashMap<>();
		Map<String,Object> queryMap=new HashMap<>();
		queryMap.put("name", "%"+name+"%");
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		
//		if(name!="")
//			ret.put("rows", teacherService.findList(queryMap));
//		if(jno!="")
//			ret.put("rows", teacherService.findByJno(jno));

		ret.put("rows", teacherService.findList(queryMap));
		ret.put("total", teacherService.getTotal(queryMap));
		
		return ret;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Teacher teacher){
		Map<String,String> ret=new HashMap<>();
		
		if(teacher==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错，请联系开发人员");
			return ret;
		}
		
//		if(teacher.getName()==null) {
//			ret.put("type", "error");
//			ret.put("msg", "教师姓名不能为空");
//			return ret;
//		}
//		
//		if(teacher.getPass()==null) {
//			ret.put("type", "error");
//			ret.put("msg", "登录密码不能为空");
//			return ret;
//		}
		
		//检验教师工号不能重复
		if(teacherService.findByJno(teacher.getJno())!=null) {
			ret.put("type", "error");
			ret.put("msg", "教师编号重复，请重新输入！");
			return ret;
		}
		
		//添加教师到数据库
		if(!(teacherService.add(teacher)>0)) {
			ret.put("type", "error");
			ret.put("msg", "数据添加失败");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "数据添加成功");
		return ret;
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Teacher teacher){
		Map<String,String> ret=new HashMap<>();
		if(teacher==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定失败，请与开发人员联系");
			return ret;
		}
		
		//不需要校验姓名...是否重复，直接修改到数据库
		if(!(teacherService.edit(teacher)>0)) {
			ret.put("type", "error");
			ret.put("msg", "数据修改失败");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "数据修改成功");
		return ret;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(
			@RequestParam(value="ids[]",required=true)Integer[] ids) {
		Map<String,String> ret=new HashMap<>();
		if(ids==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定失败，请与开发人员联系");
			return ret;
		}
		
		if(!(teacherService.delete(ids)>0)) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		
		
		//判断该教师是否还有执教的课程
		
		
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}
}
