package dto;

import java.sql.Timestamp;

public class List {
	private String content;
	private String name;
	private int priority;
	private String time;
	
	public List() {
		
	}
	
	public List(String content, String name, int priority, String time) {
		super();
		this.content = content;
		this.name = name;
		this.priority = priority;
		this.time = time;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override //간편출력
	public String toString() {
		return "List [content=" + content + ", name=" + name + ", priority=" + priority + ", time=" + time + "]";
	}
}
