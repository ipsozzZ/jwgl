package com.mqb.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.util.StringUtil;
import com.mqb.entity.Admin;
import com.mqb.service.AdminService;
import com.mqb.util.CpachaUtil;

/**
 * 系统主页控制器
 * @author MQB
 *
 */

@RequestMapping("/system")
@Controller
public class SystemController {

	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("system/index");
		model.addObject("admin", "mqb");
		return model;
	}
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		model.addObject("user", "mqb");
		return model;
	}
	
	/**
	 * 登录表单提交
	 * @param username	用户名
	 * @param password	密码	
	 * @param vcode	验证码
	 * @param type 用户类型，1-admin，2-teacher，3-student
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,
			@RequestParam(value="vcode",required=true) String vcode,
			@RequestParam(value="type",required=true,defaultValue="3") Integer type,
 			HttpServletRequest request) {
		
		Map<String,String> ret=new HashMap<>();
		if(StringUtils.isEmpty(username)) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(password)) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空！");
			return ret;
		}
		
		if(StringUtils.isEmpty(vcode)) {
			ret.put("type", "error");
			ret.put("msg", "验证码不能为空！");
			return ret;
		}
		String loginCpacha=(String) request.getSession().getAttribute("loginCpacha");
		if(StringUtils.isEmpty(loginCpacha)) {
			ret.put("type", "error");
			ret.put("msg", "长时间未操作，会话已失效，请刷新后重试！");
			return ret;
		}
		
		if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {
			ret.put("type", "error");
			ret.put("msg", "验证码错误!");
			return ret;
		}
		
		request.getSession().setAttribute("loginCpacha", null);
		
		//从数据库查找用户
		if(type==1) {
			Admin admin=adminService.findByName(username);
			if(admin==null) {
				ret.put("type", "error");
				ret.put("msg", "不存在该用户!");
				return ret;
			}
			
			if(!password.equals(admin.getPass())) {
				ret.put("type", "error");
				ret.put("msg", "密码错误!");
				return ret;
			}	
			request.getSession().setAttribute("admin", admin);
		}
		else if(type==2) {}
		else if(type==3) {
			
		}
		ret.put("type", "success");
		ret.put("msg", "登录成功");
		return ret;
	}
	
	/***
	 * 获取验证码
	 * @param request
	 * @param response
	 * @param vl 验证码代码个数
	 * @param w	图片宽
	 * @param h 图片高
	 */
	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
	public void getCpacha(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
			@RequestParam(value="w",defaultValue="98",required=false) Integer w,
			@RequestParam(value="h",defaultValue="33",required=false) Integer h) {
		CpachaUtil cpachaUtil=new CpachaUtil(vl,w,h);
		//1.创建验证码值，2.将验证码的值放入session，3.创建验证码图片
		String generatorVCode = cpachaUtil.generatorVCode();
		
		request.getSession().setAttribute("loginCpacha", generatorVCode);
		
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
