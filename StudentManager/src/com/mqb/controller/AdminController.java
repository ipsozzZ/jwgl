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
	 * ����Ա�б�ҳ
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
	 * ��ȡ��ҳ�Ĺ���Ա�б�
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
		
		//easyui �ٷ��ֲ�Ҫ�󷵻�rows��total��������
		
		return ret;
	}
	
	
	/**
	 * ��ӹ���Ա
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Admin admin){
		Map<String,String> ret=new HashMap<String,String>();
		if(admin==null) {
			ret.put("type", "error");
			ret.put("msg","���ݰ󶨳���,����ϵmqb");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getName())) {
			ret.put("type", "error");
			ret.put("msg", "�û�������Ϊ��");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getPass())) {
			ret.put("type", "error");
			ret.put("msg","���벻��Ϊ��");
			return ret;
		}
		if(!StringUtils.isEmpty(admin.getEmail())) {
			Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//����ƥ��
	        Matcher m = p.matcher(admin.getEmail());
	        if(!m.matches()) {
	        	ret.put("type", "error");
				ret.put("msg","���䲻�Ϸ�");
				return ret;
	        }
		}
		
		//��ѯ�Ƿ����ظ�
		Admin existAdmin =adminService.findByName(admin.getName());
		if(existAdmin!=null) {
			ret.put("type","error");
			ret.put("msg", "�û����ظ�,���ʧ��");
			return ret;
		}
		
		
		//��ӵ����ݿ�
		if(!(adminService.add(admin)>0)) {
			ret.put("type","error");
			ret.put("msg", "���ʧ��");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg", "��ӳɹ�");
		
		return ret;
	}
	
	/**
	 * �༭����Ա
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Admin admin){
		Map<String,String> ret=new HashMap<String,String>();
		if(admin==null) {
			ret.put("type", "error");
			ret.put("msg","�����޸ĳ���,����ϵmqb");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getName())) {
			ret.put("type", "error");
			ret.put("msg", "�û�������Ϊ��");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getPass())) {
			ret.put("type", "error");
			ret.put("msg","���벻��Ϊ��");
			return ret;
		}
		if(!StringUtils.isEmpty(admin.getEmail())) {
			Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//����ƥ��
	        Matcher m = p.matcher(admin.getEmail());
	        if(!m.matches()) {
	        	ret.put("type", "error");
				ret.put("msg","���䲻�Ϸ�");
				return ret;
	        }
		}
		
		//��ѯ�Ƿ����ظ�
		Admin existAdmin =adminService.findByName(admin.getName());
		if(existAdmin!=null) {
			if(existAdmin.getId()!=admin.getId()) {
				ret.put("type","error");
				ret.put("msg", "�û����ظ�,�޸�ʧ��");
				return ret;
			}
		}
		
		
		//��ӵ����ݿ�
		if(!(adminService.edit(admin)>0)) {
			ret.put("type","error");
			ret.put("msg", "�޸�ʧ��");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg", "�޸ĳɹ�");
		
		return ret;
	}
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(
			@RequestParam(value="ids[]",required=true) Integer[] ids){
		Map<String,String> ret=new HashMap<>();
		if(ids==null) {
			ret.put("type","error");
			ret.put("msg", "��ѡ��Ҫɾ��������");
			return ret;
		}
		System.out.println(ids);
		//���ݿ���ɾ��
		if(!(adminService.delete(ids)>0)) {
			ret.put("type","error");
			ret.put("msg", "ɾ��ʧ��");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg", "�ɹ�ɾ��"+ids.length+"������");
		return ret;
	}
	

}
