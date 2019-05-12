package com.lifebank.banksProducts.util;

import java.util.Calendar;
import java.util.Date;

public class DefferenceBetweenDate {
	
	public static int defferenceInMonths(Date startDate, Date endDate){
		try {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endDate);
            
            int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            
            int diffMonth = endMes - startMes;
            
            if(endCalendar.get(Calendar.DAY_OF_MONTH)>startCalendar.get(Calendar.DAY_OF_MONTH)){
            	diffMonth++;
            } else {
            	if(endCalendar.get(Calendar.DAY_OF_MONTH)<startCalendar.get(Calendar.DAY_OF_MONTH) && diffMonth == 0){
            		diffMonth = -1;
            	}
            }
            return diffMonth;
        } catch (Exception e) {
            return -1;
        }
	}
}
