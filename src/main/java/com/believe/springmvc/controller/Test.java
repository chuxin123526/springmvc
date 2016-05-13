package com.believe.springmvc.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test
{
	@RequestMapping(path = "/testVideo")
	public String testVideo() throws Exception
	{
		return "/test.jsp" ; 
	}
	
	@RequestMapping(path = "/test")
	public void test(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) throws Exception
	{
		OutputStream outputStream = httpServletResponse.getOutputStream() ;
		String rootPath = httpServletRequest.getServletContext().getRealPath("/") ; 
		System.out.println(rootPath);
		String path = rootPath + "/video/test.mp4" ; 
		InputStream inputStream = new FileInputStream(path) ; 
		int length = 0 ; 
		byte[] buffer = new byte[1024] ; 
		while((length = inputStream.read(buffer)) != -1)
		{
			outputStream.write(buffer, 0, length);
		}
		outputStream.flush();
		inputStream.close();
		outputStream.close() ; 
	}
	
	
}
