package com.believe.springmvc.pojo;


public class User
{
	private int id ; 
	private String name ;
	private boolean receiveNews ; 
	private String[] interests ; 
	private String sex ;
	private String skill ; 
	
	public String getSkill()
	{
		return skill;
	}
	public void setSkill(String skill)
	{
		this.skill = skill;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public String[] getInterests()
	{
		return interests;
	}
	public void setInterests(String[] interests)
	{
		this.interests = interests;
	}
	public boolean isReceiveNews()
	{
		return receiveNews;
	}
	public void setReceiveNews(boolean receiveNews)
	{
		this.receiveNews = receiveNews;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	} 
	
	
	
}
