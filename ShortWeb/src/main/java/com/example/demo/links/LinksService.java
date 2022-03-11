package com.example.demo.links;

import com.example.demo.common.ShortLink;

public interface LinksService {

	void save(ShortLink link);
	
	void remove(String link);
	 
	ShortLink get(String code);
	
	ShortLink randomPull();
	
	String randomKey();
}
