package kr.co.sunnyvale.guestbook.dto;

import org.springframework.web.multipart.MultipartFile;


public class GuestbookDTO {
    private Long id;
	private String name;
	private String content;
	private java.sql.Date regdate;
	private MultipartFile images[];
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public java.sql.Date getRegdate() {
		return regdate;
	}
	public void setRegdate(java.sql.Date regdate) {
		this.regdate = regdate;
	}
	public MultipartFile[] getImages() {
		return images;
	}
	public void setImages(MultipartFile[] images) {
		this.images = images;
	}
	
	
}
