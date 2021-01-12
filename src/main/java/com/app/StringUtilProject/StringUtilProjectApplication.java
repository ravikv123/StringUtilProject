package com.app.StringUtilProject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.StringUtilProject.service.StringServiceImpl;

@SpringBootApplication
public class StringUtilProjectApplication {
	
	@Autowired
	public StringServiceImpl service;
	
	Map<String, Integer> wordCounters ;
	public static void main(String[] args) {
		SpringApplication.run(StringUtilProjectApplication.class, args);
	}
	
	@PostConstruct
	public void init() throws URISyntaxException
	{
		service.initFile("sampleFile.txt");
	}
	
	
	

}
