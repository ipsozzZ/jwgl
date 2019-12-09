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
 * ϵͳ��ҳ������
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
	 * ��¼ҳ��
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
	 * ��¼���ύ
	 * @param username	�û���
	 * @param password	����	
	 * @param vcode	��֤��
	 * @param type �û����ͣ�1-admin��2-teacher��3-student
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
			ret.put("msg", "�û�������Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(password)) {
			ret.put("type", "error");
			ret.put("msg", "���벻��Ϊ�գ�");
			return ret;
		}
		
		if(StringUtils.isEmpty(vcode)) {
			ret.put("type", "error");
			ret.put("msg", "��֤�벻��Ϊ�գ�");
			return ret;
		}
		String loginCpacha=(String) request.getSession().getAttribute("loginCpacha");
		if(StringUtils.isEmpty(loginCpacha)) {
			ret.put("type", "error");
			ret.put("msg", "��ʱ��δ�������Ự��ʧЧ����ˢ�º����ԣ�");
			return ret;
		}
		
		if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {
			ret.put("type", "error");
			ret.put("msg", "��֤�����!");
			return ret;
		}
		
		request.getSession().setAttribute("loginCpacha", null);
		
		//�����ݿ�����û�
		if(type==1) {
			Admin admin=adminService.findByName(username);
			if(admin==null) {
				ret.put("type", "error");
				ret.put("msg", "�����ڸ��û�!");
				return ret;
			}
			
			if(!password.equals(admin.getPass())) {
				ret.put("type", "error");
				ret.put("msg", "�������!");
				return ret;
			}	
			request.getSession().setAttribute("admin", admin);
		}
		else if(type==2) {}
		else if(type==3) {
			
		}
		ret.put("type", "success");
		ret.put("msg", "��¼�ɹ�");
		return ret;
	}
	
	/***
	 * ��ȡ��֤��
	 * @param request
	 * @param response
	 * @param vl ��֤��������
	 * @param w	ͼƬ��
	 * @param h ͼƬ��
	 */
	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
	public void getCpacha(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
			@RequestParam(value="w",defaultValue="98",required=false) Integer w,
			@RequestParam(value="h",defaultValue="33",required=false) Integer h) {
		CpachaUtil cpachaUtil=new CpachaUtil(vl,w,h);
		//1.������֤��ֵ��2.����֤���ֵ����session��3.������֤��ͼƬ
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
