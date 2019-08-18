package spring.boot.eureka.client.controller;

import lombok.Data;

@Data
public class Person {
 
	private Integer id;		//主键ID
	private String name;		//姓名
	private String message;		//信息
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", message=" + message + "]";
	}
	
}
