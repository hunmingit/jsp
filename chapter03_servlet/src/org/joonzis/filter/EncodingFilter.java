package org.joonzis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// init 자동 완성 (초기화 목적)
		encoding = filterConfig.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// do 자동 완성해서 override 
		System.out.println("EncodingFilter doFilter() 동작중,...");
		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
		
	}
}//set 인코딩 빼도됨 servlet 들어가기전에 filter를 먼저 태움
