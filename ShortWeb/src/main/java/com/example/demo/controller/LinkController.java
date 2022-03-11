package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.links.LinksService;

@RestController
@CrossOrigin("*")
public class LinkController {

	private final LinksService linksService;

	@Autowired
	public LinkController(LinksService linksService) {
		super();
		this.linksService = linksService;
	}
	
	
	@GetMapping
	public int status() {
		linksService.randomPull();
		return Thread.activeCount();
	}
	
	
	
	
	
}
