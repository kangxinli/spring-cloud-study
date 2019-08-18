package spring.boot.feign.consumer;

import lombok.Data;

@Data
public class Person {
 
	private Integer id;			//主键ID
	private String name;		//姓名
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
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
}
