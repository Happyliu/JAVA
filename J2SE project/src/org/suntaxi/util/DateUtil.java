package org.suntaxi.util;

/*
 *  Copyright  sunflower
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Date tool
 * 
 * 
 */
public class DateUtil {
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm:ss");
	
	private static final SimpleDateFormat datetimeFormat2 = new SimpleDateFormat(
	"yyyyMMddHHmmss");

	/**
	 * Get the current time
	 * <p>
	 * date formet is yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentDatetime() {
		return datetimeFormat.format(now());
	}
	

	public static String currentDatetime2() {
		return datetimeFormat2.format(now());
	}


	public static String formatDatetime(Date date) {
		return datetimeFormat.format(date);
	}


	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat
				.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(date);
	}


	public static String currentDate() {
		return dateFormat.format(now());
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}


	public static String currentTime() {
		return timeFormat.format(now());
	}


	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}


	public static Date now() {
		return new Date();
	}

	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.ENGLISH);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}


	public static long millis() {
		return System.currentTimeMillis();
	}


	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}


	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}


	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}


	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}


	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}


	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}


	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}


	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}


	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 0); // set month is zero
		cal.set(Calendar.HOUR_OF_DAY, 0);// set day is zero
		cal.set(Calendar.MINUTE, 0);// set minute is zero
		cal.set(Calendar.SECOND, 0);// set second is zero
		cal.set(Calendar.MILLISECOND, 0);// set millisecond is zero
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// month plus 1
		cal.set(Calendar.MILLISECOND, -1);
		return cal.getTime();
	}


	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // 
		cal.set(Calendar.HOUR_OF_DAY, 0);// 
		cal.set(Calendar.MINUTE, 0);// 
		cal.set(Calendar.SECOND, 0);// 
		cal.set(Calendar.MILLISECOND, 0);//
		return cal.getTime();
	}

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}


	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}


	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}


	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}

	public static Date parseDatetime(String datetime) throws ParseException {
		return datetimeFormat.parse(datetime);
	}


	public static Date parseDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}


	public static Date parseTime(String time) throws ParseException {
		return timeFormat.parse(time);
	}


	public static Date parseDatetime(String datetime, String pattern)
			throws ParseException {
		SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
		format.applyPattern(pattern);
		return format.parse(datetime);
	}
	
	public static double xj(Date date1,Date date2){
		double hour=(date1.getTime()-date2.getTime())/(60*1000);
		System.out.println( "Gap of time: " + hour);
		return hour;
	}
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(xj(parseDatetime("2013-05-15 16:31:00"),parseDatetime("2013-05-15 16:11:00"))/60);
		System.out.println(currentDatetime());
	}
}

