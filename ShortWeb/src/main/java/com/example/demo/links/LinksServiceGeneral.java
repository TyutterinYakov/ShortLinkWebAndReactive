package com.example.demo.links;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.common.ShortLink;

@Service
public class LinksServiceGeneral implements LinksService {
	
	private final Long SAVE_TIMEOUT_MS = 5000L;
	
	private final RedisTemplate<String, ShortLink> template;
	
	@Autowired
	public LinksServiceGeneral(RedisTemplate<String, ShortLink> template) {
		super();
		this.template = template;
	}
	

	@Override
	public void save(ShortLink link) {
		template.opsForValue().set(link.getCode(), link, SAVE_TIMEOUT_MS, TimeUnit.MILLISECONDS); 
	}

	@Override
	public void remove(String link) {
		template.delete(link);
	}

	@Override
	public ShortLink get(String code) {
		return template.opsForValue().get(code);
	}

	@Override
	public ShortLink randomPull() {
		String key = randomKey();
		if(key!=null) {
			return get(key);
		}
		return null;
	}

	@Override
	public String randomKey() {
		return template.randomKey();
	}

	
	
}
