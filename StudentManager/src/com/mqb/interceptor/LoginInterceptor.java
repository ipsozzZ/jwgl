package com.mqb.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mqb.entity.Admin;

import net.sf.json.JSONObject;

/**
 * ��¼����������
 * @author MQB
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * �������ǰ����
	 * return false�ͱ�������
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		
		String url=request.getRequestURI();
		
		//System.out.println("������������url = "+url);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin==null) {
			//��ʾδ��¼�����¼״̬ʧЧ
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				//ajax����
				Map<String,String> ret=new HashMap<String,String>();
				ret.put("type", "error");
				ret.put("msg", "��¼״̬��ʧЧ�������µ�¼");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//System.out.println("δ��¼�����¼ʧЧ");
			response.sendRedirect(request.getContextPath()+"/system/login");
			return false;
		}
		
		return true;
	}

}
