package com.app.StringUtilProject.controller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.StringUtilProject.model.SearchReq;
import com.app.StringUtilProject.model.SearchRes;
import com.app.StringUtilProject.service.StringServiceImpl;

@RestController
@RequestMapping(path = "/counter-api")
public class StringUtilController {
	
	@Autowired
	private StringServiceImpl serv;
	
	@PostMapping("/search")
	public SearchRes search(@RequestBody SearchReq searchReq) {
		//{"searchText":["ravi","theja"]}
		return serv.searchString(searchReq);
	 }
	
	
	@GetMapping("/top/{noOfRecords}")
	public String search(@PathVariable Integer noOfRecords) {
		//{"searchText":["ravi","theja"]}
		return serv.searchTop(noOfRecords);
	 }
	
	

}
