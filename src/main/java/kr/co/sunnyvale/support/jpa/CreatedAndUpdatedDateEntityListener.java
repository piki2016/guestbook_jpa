package kr.co.sunnyvale.support.jpa;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CreatedAndUpdatedDateEntityListener {

	@PrePersist
	public void prePersist(HasCreatedAndUpdatedDate hcud) {
		Date currentDate = getCurrentDate();
		hcud.setCreatedDate(currentDate);
		hcud.setUpdatedDate(currentDate);
	}

	private Date getCurrentDate() {
		return new Date();
	}

	@PreUpdate
	public void preUpdate(HasCreatedAndUpdatedDate hcud) {
		Date currentDate = getCurrentDate();
		hcud.setUpdatedDate(currentDate);
	}
}

/**
http://www.hanbit.co.kr/network/view.html?bi_id=1553

@PrePersist	엔티티가 데이터베이스에 기록되기 전에 호출되는 메서드
@PostPersist	엔티티가 데이터베이스에 기록된 후에 직접 호출되는 메서드
@PreUpdate	엔티티가 데이터베이스에 업데이트 되기 전에 호출되는 메서드
@PostUpdate	엔티티가 데이터베이스에 업데이트 된 후에 호출되는 메서드
@PreRemove	엔티티가 제거되기 전에 호출되는 메서드
@PostRemove	엔티티가 제거된 후에 호출되는 메서드

**/