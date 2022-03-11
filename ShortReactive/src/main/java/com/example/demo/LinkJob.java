package com.example.demo;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.common.ShortLink;
import com.example.demo.links.LinkServiceReactive;
import com.example.demo.links.LinksService;

@Component
@EnableScheduling
public class LinkJob {

	private final LinksService linksService;
	private final Queue<String> linksQueue;
	
	@Autowired
	public LinkJob(LinkServiceReactive linksService, Queue<String> linksQueue) {
		super();
		this.linksService = linksService;
		this.linksQueue = linksQueue;
	}
	
	
	@Scheduled(fixedRate = 500L)
	public void bookLink() {
		String lnk = linksQueue.poll();
		if(lnk!=null) {
			 linksService.save(new ShortLink(lnk, "https://github.com/TyutterinYakov"));
		}
	}
	
	@Scheduled(fixedRate = 500L)
	public void useLink() {
		linksService.randomPull().subscribe(System.out::println);
	}
	
	
	@Scheduled(fixedRate = 1000L)
	public void deleteLink() throws InterruptedException {
		linksService.randomPull().doOnSuccess(shortLink->{
			if(shortLink!=null) {
				linksService.remove(shortLink.getCode());
				linksQueue.add(shortLink.getCode());
			}
		}).subscribe(System.out::println);
	}
}
