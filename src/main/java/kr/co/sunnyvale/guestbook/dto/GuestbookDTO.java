package kr.co.sunnyvale.guestbook.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;


public class GuestbookDTO {
	private int seq;
	private String name;
	
    @NotBlank
	private String content;
	private java.sql.Date regdate;
	private List<ImageDTO> images;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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


	public List<ImageDTO> getImages() {
		return images;
	}
	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "GuestbookDTO [seq=" + seq + ", name=" + name + ", content="
				+ content + ", regdate=" + regdate + "]";
	}

}

/*

create table guestbook (
seq number primary key,
name varchar2(20) not null,
content clob not null,
regdate date not null );

create sequence guestbook_seq;
*/