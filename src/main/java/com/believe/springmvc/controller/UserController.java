package com.believe.springmvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.eclipse.jdt.internal.compiler.ast.SynchronizedStatement;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import com.believe.springmvc.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Controller
@RequestMapping("/user")
public class UserController
{
	
	
	@RequestMapping(path = "/list" , method = RequestMethod.GET)
	public String list(@PathVariable String message)
	{
		System.out.println("testUserList"); 
		return "list" ; 
	}
	
	@RequestMapping(path = "/testJson" )
	@ResponseBody
	public String testJson(@RequestBody String requestBody) throws Exception
	{
		System.out.println(requestBody);
		JSONObject jsonObject = new JSONObject(requestBody) ; 
		System.out.println(jsonObject.get("id"));
		System.out.println(jsonObject.get("name"));
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		User user = new User() ; 
		user.setId(1);
		user.setName("心之所向便是光:");
		String stringJson = mapper.writeValueAsString(user) ; 
		
		return stringJson ; 
	}
	
	@RequestMapping(path = "/testParams" , params = "message=believe")
	public String testParams()
	{
		return "test" ; 
	}
	
	@RequestMapping("/testHttpServletRequest")
	public String testHttpServletRequest(HttpServletRequest httpServletRequest)
	{
		System.out.println(httpServletRequest.getLocalPort());
		return "test" ; 
	}
	
	@RequestMapping("/testRequestParam")
	public String testRequestParam(Model model , @RequestParam("message") String message)
	{
		System.out.println(message);
		return "test" ;
	}
	
	@RequestMapping(path = "/testFileUpload" , method = RequestMethod.POST)
	public String testFileUpload(@RequestParam("name") String name , 
			@RequestParam("file") MultipartFile multipartFile ,
			HttpServletRequest httpServletRequest
			)
	{
		OutputStream outputStream = null ; 
		String fileName = multipartFile.getOriginalFilename() ; 
		try
		{
			String rootPath = httpServletRequest.getServletContext().getRealPath("/") ; 
			byte[] fileBytes = multipartFile.getBytes() ; 
			System.out.println(name);
			System.out.println(fileName);
			String path = rootPath+ "/"+fileName ; //TODO
			File file = new File(path) ; 
			if(!file.exists())
			{
				file.createNewFile() ; 
			}
			System.out.println(file.getAbsolutePath());
			outputStream = new FileOutputStream(file) ; 
			outputStream.write(fileBytes);
		} catch (Exception e)
		{
			e.printStackTrace();
			return "error" ; 
		}
		finally
		{
			try
			{
				outputStream.close();
			} catch (IOException e)
			{
				
				e.printStackTrace();
			}
		}
		
		
		
		return "test" ;
	}
	
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader("User-Agent") String userAgent)
	{
		System.out.println(userAgent);
		return "test" ; 
	}
	
	@RequestMapping("/testRequestBody")
	public String testRequestBody(@RequestBody String body , @RequestHeader("Content-Type") String contentType)
	{
		System.out.println("enter");
		System.out.println(body);
		System.out.println(contentType);
		return "test" ; 
	}
	
	@RequestMapping("/testHttpEntity")
	public ResponseEntity<String> testHttpEntity(HttpEntity<byte[]> httpEntity)
	{
		System.out.println("enterHttpEntity");
		String contentType = httpEntity.getHeaders().getFirst("Content-Type") ; 
		String body = new String(httpEntity.getBody()); 
		System.out.println(contentType);
		System.out.println(body);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("test" , HttpStatus.CREATED) ; 
		return responseEntity ; 
	}
	
	@RequestMapping("/testResponseBody")
	@ResponseBody
	public String testResponseBody() 
	{
		return "testResponseBody" ;
	}
	
	@RequestMapping("testModelAttribute")
	public String testModelAttriBute(@ModelAttribute User user)
	{
		System.out.println(user.getName());
		System.out.println(user.getId());
		return "test" ;
	}
	
	@RequestMapping("/test")
	public String test()
	{
		return "redirect:/example/user/testRedirect" ; 
	}
	
	@RequestMapping("/testRedirect")
	public String testRedirect()
	{
		return "test/testRedirect" ; 
	}
	
	@RequestMapping("testFormTag")
	public String testFormTag(Model model , Map<String, List<String>> map)
	{
		User user = new User() ; 
		user.setName("凉了时光乱了心:");
		user.setSkill("running");
		List<String> skillList = new ArrayList<String>() ; 
		skillList.add("test1") ; 
		skillList.add("running") ; 
		skillList.add("test3") ; 
		skillList.add("test4") ; 
		skillList.add("test5") ; 
		model.addAttribute("skillList" ,skillList) ; 
		model.addAttribute("user" ,user) ; 
		return "test" ; 
	}
	

}
