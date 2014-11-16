package kr.co.sunnyvale.commons.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class BaseEntity<PK extends Serializable>{

	private static final long serialVersionUID = -8669707500586538457L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private PK id;
	
	@Version
	@Access(AccessType.FIELD)
	private long version;

	public PK getId() {

		return id;
	}

	public void setId(PK id) {

		this.id = id;
	}
	
	public boolean isNew() {

		return null == getId();
	}	
}