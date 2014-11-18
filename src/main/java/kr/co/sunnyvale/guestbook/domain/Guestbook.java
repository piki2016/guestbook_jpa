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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.sunnyvale.support.jpa.CreatedAndUpdatedDateEntityListener;
import kr.co.sunnyvale.support.jpa.HasCreatedAndUpdatedDate;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
@Table(name = "GUESTBOOK")
//@JsonAutoDetect //http://stackoverflow.com/questions/4362104/strange-jackson-exception-being-thrown-when-serializing-hibernate-object
//@JsonIgnoreProperties({"user"})
public class Guestbook implements HasCreatedAndUpdatedDate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GUESTBOOK_ID_SEQ")
	@SequenceGenerator(name = "GUESTBOOK_ID_SEQ", sequenceName = "guestbook_id_seq", allocationSize = 1)
	private Long id;

	@NotBlank
	@Lob
	@Column(name = "content", nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;

	// JoinColumn을 지정하지 않으면 USER_ID 속성과 칼람이 생성되고 ORA-01747이 발생한다.
	// User의 foregin key 에 해당하는 칼럼이 joinColumn이다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Guestbook() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public void setUpdatedDate(Date updateDate) {
		this.updatedDate = updateDate;
	}

	@Override
	public String toString() {
		return "Guestbook [id=" + id + ", content=" + content
				+ ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

}

/*
 * 
 * create table guestbook ( seq number primary key, name varchar2(20) not null,
 * content clob not null, regdate date not null );
 * 
 * create sequence guestbook_seq;
 */