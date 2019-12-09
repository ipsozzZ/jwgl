package com.mqb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mqb.entity.Admin;
import com.mqb.service.AdminService;
import com.mqb.util.Page;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 管理员列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model)
	{
		model.setViewName("admin/admin_list");
		return model;
	}
	
	
	/**
	 * 获取分页的管理员列表
	 * @param name
	 * @param page
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
		
		ret.put("rows", adminService.findList(queryMap));
		ret.put("total", adminService.getTotal(queryMap));
		
		//easyui 官方手册要求返回rows和total两个属性
		
		return ret;
	}
	
	
	/**
	 * 添加管理员
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Admin admin){
		Map<String,String> ret=new HashMap<String,String>();
		if(admin==null) {
			ret.put("type", "error");
			ret.put("msg","数据绑定出错,请联系mqb");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getName())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getPass())) {
			ret.put("type", "error");
			ret.put("msg","密码不能为空");
			return ret;
		}
		if(!StringUtils.isEmpty(admin.getEmail())) {
			Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
	        Matcher m = p.matcher(admin.getEmail());
	        if(!m.matches()) {
	        	ret.put("type", "error");
				ret.put("msg","邮箱不合法");
				return ret;
	        }
		}
		
		//查询是否有重复
		Admin existAdmin =adminService.findByName(admin.getName());
		if(existAdmin!=null) {
			ret.put("type","error");
			ret.put("msg", "用户名重复,添加失败");
			return ret;
		}
		
		
		//添加到数据库
		if(!(adminService.add(admin)>0)) {
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg", "添加成功");
		
		return ret;
	}
	
	/**
	 * 编辑管理员
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Admin admin){
		Map<String,String> ret=new HashMap<String,String>();
		if(admin==null) {
			ret.put("type", "error");
			ret.put("msg","数据修改出错,请联系mqb");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getName())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getPass())) {
			ret.put("type", "error");
			ret.put("msg","密码不能为空");
			return ret;
		}
		if(!StringUtils.isEmpty(admin.getEmail())) {
			Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
	        Matcher m = p.matcher(admin.getEmail());
	        if(!m.matches()) {
	        	ret.put("type", "error");
				ret.put("msg","邮箱不合法");
				return ret;
	        }
		}
		
		//查询是否有重复
		Admin existAdmin =adminService.findByName(admin.getName());
		if(existAdmin!=null) {
			if(existAdmin.getId()!=admin.getId()) {
				ret.put("type","error");
				ret.put("msg", "用户名重复,修改失败");
				return ret;
			}
		}
		
		
		//添加到数据库
		if(!(adminService.edit(admin)>0)) {
			ret.put("type","error");
			ret.put("msg", "修改失败");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg", "修改成功");
		
		return ret;
	}
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(
			@RequestParam(value="ids[]",required=true) Integer[] ids){
		Map<String,String> ret=new HashMap<>();
		if(ids==null) {
			ret.put("type","error");
			ret.put("msg", "请选择要删除的数据");
			return ret;
		}
		System.out.println(ids);
		//数据库中删除
		if(!(adminService.delete(ids)>0)) {
			ret.put("type","error");
			ret.put("msg", "删除失败");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg", "成功删除"+ids.length+"条数据");
		return ret;
	}
	

}
