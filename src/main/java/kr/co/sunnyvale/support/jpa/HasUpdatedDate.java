package kr.co.sunnyvale.support.jpa;
import java.util.Date;

public interface HasUpdatedDate {
	void setUpdatedDate(Date updatedDate);

	Date getUpdatedDate();
}