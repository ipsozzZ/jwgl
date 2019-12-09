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
	 * רҵ�б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("profession/profession_list");
		return model;
	}
	
	/**
	 * ��ȡרҵ�б�����
	 * @param name ��ѯʱ�Ĺؼ���
	 * @param page ��ҳ��Ϣ
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
		
		//�����ݿ��ȡרҵ�����б�
		ret.put("rows", professionService.findList(queryMap));
		ret.put("total", professionService.getTotal(queryMap));
		return ret;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Profession profession){
		Map<String,String> ret=new HashMap<>();
		
		//���רҵ��ź�רҵ�����ظ�
		Profession existPro=professionService.findByName(profession.getName());
		if(existPro!=null) {
			ret.put("type", "error");
			ret.put("msg", "רҵ�������ظ�!");
			return ret;
		}
		
		existPro=professionService.findByPno(profession.getPno());
		if(existPro!=null) {
			ret.put("type", "error");
			ret.put("msg", "רҵ������ظ�!");
			return ret;
		}
		
		//��������ӵ����ݿ�
		if(!(professionService.add(profession)>0)) {
			ret.put("type", "error");
			ret.put("msg", "�������ʧ��!");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Profession profession){
		Map<String,String> ret=new HashMap<>();
		
		//���רҵ��ź�רҵ�����ظ�
		Profession existPro=professionService.findByName(profession.getName());
		if(existPro!=null && existPro.getId()!=profession.getId()) {
			ret.put("type", "error");
			ret.put("msg", "רҵ�������ظ�!");
			return ret;
		}
		
		existPro=professionService.findByPno(profession.getPno());
		if(existPro!=null && existPro.getId()!=profession.getId()) {
			ret.put("type", "error");
			ret.put("msg", "רҵ������ظ�!");
			return ret;
		}
		
		//�������޸ĵ����ݿ�
		if(!(professionService.edit(profession)>0)) {
			ret.put("type", "error");
			ret.put("msg", "�����޸�ʧ��!");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(
			@RequestParam(value="ids[]",required=true)Integer[] ids){
		Map<String,String> ret=new HashMap<>();
		
		if(ids==null) {
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ��������");
			return ret;
		}
		
		//�����ݴ����ݿ�ɾ��
		if(!(professionService.delete(ids)>0)) {
			ret.put("type", "error");
			ret.put("msg", "ɾ������ʧ��");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
}
