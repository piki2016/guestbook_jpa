package kr.co.sunnyvale.guestbook.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
public class Guestbook {
	

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "GUESTBOOK_ID_SEQ")
    @SequenceGenerator(name = "GUESTBOOK_ID_SEQ", sequenceName = "guestbook_id_seq", allocationSize=1)   
    private Long id;
    
	private String name;
	
    @NotBlank
	private String content;
	private java.sql.Date regdate;
	
	@OneToMany(mappedBy="guestbook")
	List<Image> images = new ArrayList<Image>();

	public void addImage(Image image) {
		images.add(image);
	}	
	
	
	
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


	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
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