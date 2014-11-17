package kr.co.sunnyvale.guestbook.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.sunnyvale.commons.support.jpa.CreatedAndUpdatedDateEntityListener;
import kr.co.sunnyvale.commons.support.jpa.HasCreatedAndUpdatedDate;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")
@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Image implements HasCreatedAndUpdatedDate{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "IMAGE_ID_SEQ")
    @SequenceGenerator(name = "IMAGE_ID_SEQ", sequenceName = "image_id_seq", allocationSize=1)   
    private Long id;
	
    @Column(name = "file_name", nullable = false)
	private String fileName; // 원본 파일명
    
    @Column(name = "save_file_name", nullable = false)
	private String saveFileName; // save파일 이름
	
	@Length(max=500)
    @Column(name = "real_path", nullable = false)
	private String realPath; //  실제 디스크 저장 경로
	
	@Column(name = "file_length", nullable = false)
	private long fileLength;
	 
	@Column(name = "content_type", nullable = false)
	private String contentType;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
	private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="guestbook_id", nullable=false)
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", fileName=" + fileName + ", saveFileName="
				+ saveFileName + ", realPath=" + realPath + ", fileLength="
				+ fileLength + ", contentType=" + contentType
				+ ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
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