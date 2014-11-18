package kr.co.sunnyvale.guestbook.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import kr.co.sunnyvale.support.jpa.CreatedAndUpdatedDateEntityListener;
import kr.co.sunnyvale.support.jpa.HasCreatedAndUpdatedDate;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Slf4j	
@Entity
@Table(name="GUESTBOOK_USER", uniqueConstraints=@UniqueConstraint(columnNames="user_id"))
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class User implements HasCreatedAndUpdatedDate{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "user_id_seq", allocationSize=1)    
    private Long id;

    @NotBlank
    @Length(max = 20) 
    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @NotBlank
    @Length(max = 100)   
	private String name;

    @NotBlank
    @JsonIgnore
	private String passwd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
	private Date updatedDate;
	
	private int admin;
	
	@OneToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
	@JsonIgnore
	List<Guestbook> guestbooks;

	@OneToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
	@JsonIgnore
	List<Image> images;
	
	public User(){
	}
	
	public User(String userId){
		this();
		setUserId(userId);
	}
	
	public void addImage(Image image) {
		if(image == null)
			return;
		if(images == null){
			images = new ArrayList<Image>();
		}
		images.add(image);
		image.setUser(this);
	}		
	
	public void addGuestbook(Guestbook guestbook){
		if(guestbook == null)
			return;
		
		if(guestbooks == null){
			guestbooks = new ArrayList<Guestbook>();
		}
		guestbook.setUser(this);
		guestbooks.add(guestbook);
	}
	
	//, message="org.hibernate.validator.constraints.Pattern.message"
    @NotBlank
    @Length(max = 255)  
    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$")
    private String email;
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public List<Guestbook> getGuestbooks() {
		return guestbooks;
	}

	public void setGuestbooks(List<Guestbook> guestbooks) {
		this.guestbooks = guestbooks;
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
		return "User [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", passwd=" + passwd + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", admin=" + admin
				+ ", email=" + email + "]";
	}

	
}

/*

spring 은 기본적으로 14가지 검증을 처리할 수 있는 어노테이션을 제공한다.
@AssertFalse : false 값만 통과 가능
@AssertTrue : true 값만 통과 가능
@DecimalMax(value=) : 지정된 값 이하의 실수만 통과 가능
@DecimalMin(value=) : 지정된 값 이상의 실수만 통과 가능
@Digits(integer=,fraction=) : 대상 수가 지정된 정수와 소수 자리수보다 적을 경우 통과 가능
@Future : 대상 날짜가 현재보다 미래일 경우만 통과 가능
@Past : 대상 날짜가 현재보다 과거일 경우만 통과 가능
@Max(value) : 지정된 값보다 아래일 경우만 통과 가능
@Min(value) : 지정된 값보다 이상일 경우만 통과 가능
@NotNull : null 값이 아닐 경우만 통과 가능
@Null : null일 겨우만 통과 가능
@Pattern(regex=, flag=) : 해당 정규식을 만족할 경우만 통과 가능
@Size(min=, max=) : 문자열 또는 배열이 지정된 값 사이일 경우 통과 가능
@Valid : 대상 객체의 확인 조건을 만족할 경우 통과 가능
*/
