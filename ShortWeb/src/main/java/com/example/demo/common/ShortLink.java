package com.example.demo.common;

public class ShortLink {

	private String code;
	private String path;
	
	public ShortLink() {
		super();
	}
	public ShortLink(String code, String path) {
		super();
		this.code = code;
		this.path = path;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "ShortLink [code=" + code + ", path=" + path + "]";
	}
	
	
	
	
	
}
