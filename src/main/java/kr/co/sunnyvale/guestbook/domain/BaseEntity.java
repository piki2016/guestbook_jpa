package kr.co.sunnyvale.guestbook.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class BaseEntity<PK extends Serializable> extends AbstractPersistable<PK>{

	private static final long serialVersionUID = -8669707500586538457L;
	
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

}