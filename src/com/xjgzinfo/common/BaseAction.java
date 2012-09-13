package com.xjgzinfo.common;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

public class BaseAction implements SessionAware,ServletRequestAware,ServletResponseAware,ServletContextAware{

	protected Map<String,Object> session;
	protected ServletContext Context;
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}
	public void setServletContext(ServletContext arg0) {
		Context = arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		response =  arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

}
