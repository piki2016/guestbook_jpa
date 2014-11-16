package kr.co.sunnyvale.guestbook.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class BaseDateEntity<PK extends Serializable> extends AbstractPersistable<PK>{

	private static final long serialVersionUID = -8669707500586538457L;
	
	private String createdBy;
	private String lastModifiedBy;

	@Column(length = 14)
	private String createdDttm;

	@Column(length = 14)
	private String lastModifiedDttm;
	
    @Version
    @Access(AccessType.FIELD)
    private long version;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public PK getId() {
        return super.getId();
    }
    
	@Override
	public void setId(PK id) {
		super.setId(id);
	}

	@PreUpdate
	public void preUpdate() {
		lastModifiedDttm = getNowTime();
	}

	@PrePersist
	public void prePersist() {
		String nowTime = getNowTime();
		createdDttm = nowTime;
		lastModifiedDttm = nowTime;
	}

	public static String getNowTime() {
		return getTime("yyyyMMddHHmmss");
	}

	public static String getTime(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getCreatedDttm() {
		return createdDttm;
	}

	public void setCreatedDttm(String createdDttm) {
		this.createdDttm = createdDttm;
	}

	public String getLastModifiedDttm() {
		return lastModifiedDttm;
	}

	public void setLastModifiedDttm(String lastModifiedDttm) {
		this.lastModifiedDttm = lastModifiedDttm;
	}
	
	

}