package com.app.StringUtilProject.model;

import java.util.HashMap;

public class SearchRes {
	private HashMap<String,Integer>[] counts=new HashMap[1];

	public HashMap[] getCounts() {
		return this.counts;
	}

	public void setCounts(HashMap[] counts) {
		this.counts = counts;
	}
	
	
}
