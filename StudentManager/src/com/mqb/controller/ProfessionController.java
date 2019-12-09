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

import com.mqb.entity.Profession;
import com.mqb.service.ProfessionService;
import com.mqb.util.Page;

@RequestMapping("/profession")
@Controller
public class ProfessionController {
	
	@Autowired
	private ProfessionService professionService;
	
	/**
	 * 专业列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("profession/profession_list");
		return model;
	}
	
	/**
	 * 获取专业列表数据
	 * @param name 查询时的关键字
	 * @param page 分页信息
	 * @return
	 */
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Page page
			){
		Map<String,Object> ret=new HashMap<>();
		Map<String,Object> queryMap=new HashMap<>();
		
		queryMap.put("name", "%"+name+"%");
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		
		//从数据库获取专业数据列表
		ret.put("rows", professionService.findList(queryMap));
		ret.put("total", professionService.getTotal(queryMap));
		return ret;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Profession profession){
		Map<String,String> ret=new HashMap<>();
		
		//检查专业编号和专业名称重复
		Profession existPro=professionService.findByName(profession.getName());
		if(existPro!=null) {
			ret.put("type", "error");
			ret.put("msg", "专业名称有重复!");
			return ret;
		}
		
		existPro=professionService.findByPno(profession.getPno());
		if(existPro!=null) {
			ret.put("type", "error");
			ret.put("msg", "专业编号有重复!");
			return ret;
		}
		
		//将数据添加到数据库
		if(!(professionService.add(profession)>0)) {
			ret.put("type", "error");
			ret.put("msg", "数据添加失败!");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Profession profession){
		Map<String,String> ret=new HashMap<>();
		
		//检查专业编号和专业名称重复
		Profession existPro=professionService.findByName(profession.getName());
		if(existPro!=null && existPro.getId()!=profession.getId()) {
			ret.put("type", "error");
			ret.put("msg", "专业名称有重复!");
			return ret;
		}
		
		existPro=professionService.findByPno(profession.getPno());
		if(existPro!=null && existPro.getId()!=profession.getId()) {
			ret.put("type", "error");
			ret.put("msg", "专业编号有重复!");
			return ret;
		}
		
		//将数据修改到数据库
		if(!(professionService.edit(profession)>0)) {
			ret.put("type", "error");
			ret.put("msg", "数据修改失败!");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(
			@RequestParam(value="ids[]",required=true)Integer[] ids){
		Map<String,String> ret=new HashMap<>();
		
		if(ids==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的数据");
			return ret;
		}
		
		//将数据从数据库删除
		if(!(professionService.delete(ids)>0)) {
			ret.put("type", "error");
			ret.put("msg", "删除数据失败");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
}
