package kr.co.sunnyvale.support.util;

import java.util.Calendar;

public class CalendarUtil {
	
	public static String getTodayYyyyMmDd(){
		Calendar c = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append("/");
		sb.append(c.get(Calendar.YEAR));
		sb.append("/");
		if((c.get(Calendar.MONTH) + 1) < 10){
			sb.append("0");
			sb.append(c.get(Calendar.MONTH) + 1 );
		}else{
			sb.append(c.get(Calendar.MONTH) + 1 );
		}
		sb.append("/");
		if((c.get(Calendar.DATE) + 1) < 10){
			sb.append("0");
			sb.append(c.get(Calendar.DATE) + 1 );
		}else{
			sb.append(c.get(Calendar.DATE) + 1 );
		}

		return sb.toString();
	}
	

	public static void main(String args[]){
		System.out.println(CalendarUtil.getTodayYyyyMmDd());
	}
}
