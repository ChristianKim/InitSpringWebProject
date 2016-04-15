package powergen.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class DateUtil {
	public static String getTime() {
		return getTime("yyyyMMddHHmmss");
	}

	public static String getDate() {
		return getTime("yyyyMMdd");
	}

	public static String getTime(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	public static java.util.Date stringToDate(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		return sdf.parse(dateString, pos);
	}

	public static Calendar stringToCalendar(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = sdf.parse(dateString, pos);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String formatDate(java.util.Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return sdf.format(cal.getTime());
	}

	public static String formatCalendar(Calendar cal, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	public static boolean isLeapYear(int year) {
		if (year % 4 != 0)
			return false;
		if (year % 400 == 0)
			return true;
		return year % 100 != 0;
	}

	public static int lastDate(int year, int month) {
		int[] kLastDates = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if ((month > 12) || (month < 0)) {
			month = 0;
		}
		if ((month == 2) && (isLeapYear(year))) {
			return kLastDates[month] + 1;
		}
		return kLastDates[month];
	}

	public static int daysDiff(String earlierDate, String laterDate, String format) {
		if ((earlierDate == null) || (laterDate == null)) {
			return 0;
		}
		java.util.Date d1 = stringToDate(earlierDate, format);
		java.util.Date d2 = stringToDate(laterDate, format);

		return (int) ((d2.getTime() - d1.getTime()) / 86400000L);
	}

	public static int compareDate(String date1, String date2, String format) {
		Calendar c1 = stringToCalendar(date1, format);
		Calendar c2 = stringToCalendar(date2, format);

		return compareDate(c1, c2);
	}

	public static int compareDate(java.util.Date date1, java.util.Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		return compareDate(c1, c2);
	}

	public static int compareDate(Calendar cal1, Calendar cal2) {
		int value = 9;

		if (cal1.before(cal2)) {
			value = -1;
		}
		if (cal1.after(cal2)) {
			value = 1;
		}
		if (cal1.equals(cal2)) {
			value = 0;
		}
		return value;
	}

	public static java.sql.Date rollYears(java.util.Date startDate, int years) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(startDate);
		gc.add(1, years);
		return new java.sql.Date(gc.getTime().getTime());
	}

	public static java.sql.Date rollMonths(java.util.Date startDate, int months) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(startDate);
		gc.add(2, months);
		return new java.sql.Date(gc.getTime().getTime());
	}

	public static java.sql.Date rollDays(java.util.Date startDate, int days) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(startDate);
		gc.add(5, days);
		return new java.sql.Date(gc.getTime().getTime());
	}

	public static String getTomorrow() {
		return getDate(1, "yyyyMMdd");
	}

	public static String getTomorrow(String format) {
		return getDate(1, format);
	}

	public static String getYesterday() {
		return getDate(-1, "yyyyMMdd");
	}

	public static String getYesterday(String format) {
		return getDate(-1, format);
	}

	public static String getDate(int days) {
		return getDate(days, "yyyyMMdd");
	}

	public static String getDate(int days, String format) {
		GregorianCalendar gc = new GregorianCalendar();
		SimpleTimeZone kstZone = new SimpleTimeZone(32400000, "Asia/Seoul");
		gc.setTimeZone(kstZone);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		gc.add(5, days);

		return sdf.format(gc.getTime());
	}

	public static String dateFilter(String src, String format) {
		if ((src == null) || (src.length() < 14) || (format.length() < 2)) {
			return "";
		}
		String str_ret = "";

		int iyyyy = Integer.parseInt(src.substring(0, 4));
		int iyy = Integer.parseInt(src.substring(2, 4));
		int imm = Integer.parseInt(src.substring(4, 6));
		int idd = Integer.parseInt(src.substring(6, 8));
		int i24hh = Integer.parseInt(src.substring(8, 10));
		int ihh;
		if (i24hh > 12)
			ihh = i24hh - 12;
		else
			ihh = i24hh;
		int imi = Integer.parseInt(src.substring(10, 12));
		int iss = Integer.parseInt(src.substring(12, 14));

		str_ret = replaceString(format, "YYYY", intToString(iyyyy));
		str_ret = replaceString(str_ret, "YY", intToString(iyy));
		str_ret = replaceString(str_ret, "MM", intToString(imm));
		str_ret = replaceString(str_ret, "DD", intToString(idd));
		str_ret = replaceString(str_ret, "HH24", intToString(i24hh));
		str_ret = replaceString(str_ret, "HH", intToString(ihh));
		str_ret = replaceString(str_ret, "MI", intToString(imi));
		str_ret = replaceString(str_ret, "SS", intToString(iss));

		return str_ret;
	}

	public static String dateFilter(String src, String format, boolean zeroFG) {
		if ((src == null) || (src.length() < 14) || (format.length() < 2)) {
			return "";
		}
		String str_ret = "";

		String strhh = "";

		if (zeroFG) {
			int temphh = Integer.parseInt(src.substring(8, 10));
			int ihh;
			if (temphh > 12)
				ihh = temphh - 12;
			else {
				ihh = temphh;
			}
			strhh = intToString(ihh);
			if (strhh.length() <= 1) {
				strhh = "0" + strhh;
			}
			str_ret = replaceString(format, "YYYY", src.substring(0, 4));
			str_ret = replaceString(str_ret, "YY", src.substring(2, 4));
			str_ret = replaceString(str_ret, "MM", src.substring(4, 6));
			str_ret = replaceString(str_ret, "DD", src.substring(6, 8));
			str_ret = replaceString(str_ret, "HH24", src.substring(8, 10));
			str_ret = replaceString(str_ret, "HH", strhh);
			str_ret = replaceString(str_ret, "MI", src.substring(10, 12));
			str_ret = replaceString(str_ret, "SS", src.substring(12, 14));
		} else {
			int iyyyy = Integer.parseInt(src.substring(0, 4));
			int iyy = Integer.parseInt(src.substring(2, 4));
			int imm = Integer.parseInt(src.substring(4, 6));
			int idd = Integer.parseInt(src.substring(6, 8));
			int i24hh = Integer.parseInt(src.substring(8, 10));
			int ihh;
			if (i24hh > 12)
				ihh = i24hh - 12;
			else {
				ihh = i24hh;
			}
			int imi = Integer.parseInt(src.substring(10, 12));
			int iss = Integer.parseInt(src.substring(12, 14));

			str_ret = replaceString(format, "YYYY", intToString(iyyyy));
			str_ret = replaceString(str_ret, "YY", intToString(iyy));
			str_ret = replaceString(str_ret, "MM", intToString(imm));
			str_ret = replaceString(str_ret, "DD", intToString(idd));
			str_ret = replaceString(str_ret, "HH24", intToString(i24hh));
			str_ret = replaceString(str_ret, "HH", intToString(ihh));
			str_ret = replaceString(str_ret, "MI", intToString(imi));
			str_ret = replaceString(str_ret, "SS", intToString(iss));
		}
		return str_ret;
	}

	private static String intToString(int i) {
		return new Integer(i).toString();
	}

	private static String replaceString(String src, String from, String to) {
		StringBuffer res_Buff = new StringBuffer();
		int pos = -1;

		if ((src == null) || (from == null) || (from.equals("")))
			return src;
		if (to == null)
			to = "";
		while (true) {
			if ((pos = src.indexOf(from)) == -1) {
				res_Buff.append(src);
				break;
			}
			res_Buff.append(src.substring(0, pos)).append(to);
			src = src.substring(pos + from.length());
		}
		return res_Buff.toString();
	}

	public static long getTimeInMillis() {
		GregorianCalendar gc = new GregorianCalendar();
		SimpleTimeZone kstZone = new SimpleTimeZone(32400000, "Asia/Seoul");
		gc.setTimeZone(kstZone);
		return gc.getTimeInMillis();
	}

	public static java.util.Date getDateObject() {
		GregorianCalendar gc = new GregorianCalendar();
		SimpleTimeZone kstZone = new SimpleTimeZone(32400000, "Asia/Seoul");
		gc.setTimeZone(kstZone);
		return gc.getTime();
	}

	public static boolean isBeetweenDate(java.util.Date startDate, java.util.Date endDate, java.util.Date testDate) {
		boolean result = false;
		if ((compareDate(startDate, testDate) == -1) && (compareDate(endDate, testDate) == 1)) {
			result = true;
		}

		return result;
	}
}
