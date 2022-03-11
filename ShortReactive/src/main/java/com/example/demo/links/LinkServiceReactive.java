package com.example.demo.links;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;

import com.example.demo.common.ShortLink;

import ch.qos.logback.core.util.TimeUtil;
import reactor.core.publisher.Mono;

@Service
public class LinkServiceReactive implements LinksService {

	private final static Long SAVE_TIMEOUT_MS = 5000L;
	
	private final ReactiveRedisOperations<String, ShortLink> redisOperaions;
	
	
	@Autowired
	public LinkServiceReactive(ReactiveRedisOperations<String, ShortLink> redisOperaions) {
		super();
		this.redisOperaions = redisOperaions;
	}

	@Override
	public void save(ShortLink link) {
		redisOperaions
			.opsForValue().set(
					link.getCode(), link, Duration.ofMillis(SAVE_TIMEOUT_MS)).subscribe();
	}

	@Override
	public void remove(String code) {
		redisOperaions.delete(code).subscribe();
	}

	@Override
	public Mono<ShortLink> get(Mono<String> code) {
		return code.flatMap(redisOperaions.opsForValue()::get);
	}

	@Override
	public Mono<ShortLink> randomPull() {
		return get(randomKey());
	}

	@Override
	public Mono<String> randomKey() {
		return redisOperaions.randomKey();
	}

}
