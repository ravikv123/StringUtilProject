package com.app.StringUtilProject.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.app.StringUtilProject.model.SearchReq;
import com.app.StringUtilProject.model.SearchRes;

@Service
public class StringServiceImpl {	
	//System.out.println("java8 (second case) = " + java8Case2);

	Map<String, Integer> wordCounters ;
	

	public void initFile(String fileName) throws URISyntaxException {
		fileToWordCounterInit(readFile(fileName));
	}

	public String readFile(String filePath) throws URISyntaxException
	{
		StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(ClassLoader.getSystemResource(filePath).toURI()), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
	}
	
	public void fileToWordCounterInit(String fileContent) {
		//Load the File to List
        List <String> list = Stream.of(fileContent).map(w -> w.split("\\s+")).flatMap(Arrays::stream).collect(Collectors.toList());
        //divide it into key/value pair for words vs count
        this.wordCounters = list.stream().collect(Collectors.toMap(w -> w.replace(".","").replace(",",""), w -> 1, Integer::sum));

        //Sort Descending order
        //wordCounter = wordCounter.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
     //           .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

	public SearchRes searchString(SearchReq searchReq) {
		SearchRes b = new SearchRes();
		HashMap<String, Integer> hm = new HashMap();
		if(searchReq != null)
			for (String searchText : searchReq.getSearchText()) {
				hm.put(searchText, wordCounters.get(searchText)!=null?wordCounters.get(searchText):0);
			}
		b.setCounts(new HashMap[]{hm});
		return b;
		
	}


	public String searchTop(Integer noOfRecords) {
		List<String> list = new ArrayList();
		wordCounters.entrySet().stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.limit(noOfRecords)
			.forEach(entry -> list.add(entry.getKey() + "|" + entry.getValue()));
		return String.join(" ",list );
	}
}
