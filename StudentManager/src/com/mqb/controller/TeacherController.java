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
			ret.put("msg", "���ݰ󶨳�������ϵ������Ա");
			return ret;
		}
		
//		if(teacher.getName()==null) {
//			ret.put("type", "error");
//			ret.put("msg", "��ʦ��������Ϊ��");
//			return ret;
//		}
//		
//		if(teacher.getPass()==null) {
//			ret.put("type", "error");
//			ret.put("msg", "��¼���벻��Ϊ��");
//			return ret;
//		}
		
		//�����ʦ���Ų����ظ�
		if(teacherService.findByJno(teacher.getJno())!=null) {
			ret.put("type", "error");
			ret.put("msg", "��ʦ����ظ������������룡");
			return ret;
		}
		
		//��ӽ�ʦ�����ݿ�
		if(!(teacherService.add(teacher)>0)) {
			ret.put("type", "error");
			ret.put("msg", "�������ʧ��");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "������ӳɹ�");
		return ret;
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Teacher teacher){
		Map<String,String> ret=new HashMap<>();
		if(teacher==null) {
			ret.put("type", "error");
			ret.put("msg", "���ݰ�ʧ�ܣ����뿪����Ա��ϵ");
			return ret;
		}
		
		//����ҪУ������...�Ƿ��ظ���ֱ���޸ĵ����ݿ�
		if(!(teacherService.edit(teacher)>0)) {
			ret.put("type", "error");
			ret.put("msg", "�����޸�ʧ��");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "�����޸ĳɹ�");
		return ret;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(
			@RequestParam(value="ids[]",required=true)Integer[] ids) {
		Map<String,String> ret=new HashMap<>();
		if(ids==null) {
			ret.put("type", "error");
			ret.put("msg", "���ݰ�ʧ�ܣ����뿪����Ա��ϵ");
			return ret;
		}
		
		if(!(teacherService.delete(ids)>0)) {
			ret.put("type", "error");
			ret.put("msg", "ɾ��ʧ��");
			return ret;
		}
		
		
		//�жϸý�ʦ�Ƿ���ִ�̵Ŀγ�
		
		
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ�");
		return ret;
	}
}
