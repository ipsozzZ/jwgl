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
 * 登录过滤拦截器
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
	 * 请求完成前拦截
	 * return false就被拦截了
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		
		String url=request.getRequestURI();
		
		//System.out.println("进入拦截器，url = "+url);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin==null) {
			//表示未登录，或登录状态失效
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				//ajax请求
				Map<String,String> ret=new HashMap<String,String>();
				ret.put("type", "error");
				ret.put("msg", "登录状态已失效，请重新登录");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//System.out.println("未登录，或登录失效");
			response.sendRedirect(request.getContextPath()+"/system/login");
			return false;
		}
		
		return true;
	}

}
