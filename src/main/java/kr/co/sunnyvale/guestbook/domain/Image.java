package kr.co.sunnyvale.guestbook.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class Image{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "IMAGE_ID_SEQ")
    @SequenceGenerator(name = "IMAGE_ID_SEQ", sequenceName = "image_id_seq", allocationSize=1)   
    private Long id;
	
	private String fileName; // 원본 파일명
	private String saveFileName; // save파일 이름
	private String realPath; //  실제 디스크 저장 경로
	private long fileLength;
	private String contentType;
	private java.sql.Date regdate;

	@ManyToOne
	private Guestbook guestbook;

	public Image(){

	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Image(Guestbook guestbook){
		setGuestbook(guestbook);		
	}

	public Guestbook getGuestbook() {
		return guestbook;
	}
	public void setGuestbook(Guestbook guestbook) {
		this.guestbook = guestbook;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public long getFileLength() {
		return fileLength;
	}
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public java.sql.Date getRegdate() {
		return regdate;
	}

	public void setRegdate(java.sql.Date regdate) {
		this.regdate = regdate;
	}
	
	
}

/*
create table images (
seq number primary key,
guestbook_seq number,
file_name varchar2(255) not null,
save_file_name varchar2(255) not null,
real_path varfchar2(4000) not null,
file_length number,
regdate date not null,
CONSTRAINT images_guestbook_seq_fk FOREIGN KEY (guestbook_seq)
REFERENCES guestbook(seq)
);


create sequence images_seq;

*/