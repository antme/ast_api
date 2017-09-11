// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ChineseCalendarGB.java

package com.astrology.util;

import java.io.PrintStream;

public class ChineseCalendarGB
{

	private int gregorianYear;
	private int gregorianMonth;
	private int gregorianDate;
	private boolean isGregorianLeap;
	private int dayOfYear;
	private int dayOfWeek;
	private int chineseYear;
	private int chineseMonth;
	private int chineseDate;
	private int sectionalTerm;
	private int principleTerm;
	private static char daysInGregorianMonth[] = {
		'\037', '\034', '\037', '\036', '\037', '\036', '\037', '\037', '\036', '\037', 
		'\036', '\037'
	};
	private static String stemNames[] = {
		"��", "��", "��", "��", "��", "��", "��", "��", "��", "��"
	};
	private static String branchNames[] = {
		"��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", 
		"��", "��"
	};
	private static String animalNames[] = {
		"��", "ţ", "��", "��", "��", "��", "��", "��", "��", "��", 
		"��", "��"
	};
	private static char chineseMonths[] = {
		'\0', '\004', '\255', '\b', 'Z', '\001', '\325', 'T', '\264', '\t', 
		'd', '\005', 'Y', 'E', '\225', '\n', '\246', '\004', 'U', '$', 
		'\255', '\b', 'Z', 'b', '\332', '\004', '\264', '\005', '\264', 'U', 
		'R', '\r', '\224', '\n', 'J', '*', 'V', '\002', 'm', 'q', 
		'm', '\001', '\332', '\002', '\322', 'R', '\251', '\005', 'I', '\r', 
		'*', 'E', '+', '\t', 'V', '\001', '\265', ' ', 'm', '\001', 
		'Y', 'i', '\324', '\n', '\250', '\005', '\251', 'V', '\245', '\004', 
		'+', '\t', '\236', '8', '\266', '\b', '\354', 't', 'l', '\005', 
		'\324', '\n', '\344', 'j', 'R', '\005', '\225', '\n', 'Z', 'B', 
		'[', '\004', '\266', '\004', '\264', '"', 'j', '\005', 'R', 'u', 
		'\311', '\n', 'R', '\005', '5', 'U', 'M', '\n', 'Z', '\002', 
		']', '1', '\265', '\002', 'j', '\212', 'h', '\005', '\251', '\n', 
		'\212', 'j', '*', '\005', '-', '\t', '\252', 'H', 'Z', '\001', 
		'\265', '\t', '\260', '9', 'd', '\005', '%', 'u', '\225', '\n', 
		'\226', '\004', 'M', 'T', '\255', '\004', '\332', '\004', '\324', 'D', 
		'\264', '\005', 'T', '\205', 'R', '\r', '\222', '\n', 'V', 'j', 
		'V', '\002', 'm', '\002', 'j', 'A', '\332', '\002', '\262', '\241', 
		'\251', '\005', 'I', '\r', '\n', 'm', '*', '\t', 'V', '\001', 
		'\255', 'P', 'm', '\001', '\331', '\002', '\321', ':', '\250', '\005', 
		')', '\205', '\245', '\f', '*', '\t', '\226', 'T', '\266', '\b', 
		'l', '\t', 'd', 'E', '\324', '\n', '\244', '\005', 'Q', '%', 
		'\225', '\n', '*', 'r', '[', '\004', '\266', '\004', '\254', 'R', 
		'j', '\005', '\322', '\n', '\242', 'J', 'J', '\005', 'U', '\224', 
		'-', '\n', 'Z', '\002', 'u', 'a', '\265', '\002', 'j', '\003', 
		'a', 'E', '\251', '\n', 'J', '\005', '%', '%', '-', '\t', 
		'\232', 'h', '\332', '\b', '\264', '\t', '\250', 'Y', 'T', '\003', 
		'\245', '\n', '\221', ':', '\226', '\004', '\255', '\260', '\255', '\004', 
		'\332', '\004', '\364', 'b', '\264', '\005', 'T', '\013', 'D', ']', 
		'R', '\n', '\225', '\004', 'U', '"', 'm', '\002', 'Z', 'q', 
		'\332', '\002', '\252', '\005', '\262', 'U', 'I', '\013', 'J', '\n', 
		'-', '9', '6', '\001', 'm', '\200', 'm', '\001', '\331', '\002', 
		'\351', 'j', '\250', '\005', ')', '\013', '\232', 'L', '\252', '\b', 
		'\266', '\b', '\264', '8', 'l', '\t', 'T', 'u', '\324', '\n', 
		'\244', '\005', 'E', 'U', '\225', '\n', '\232', '\004', 'U', 'D', 
		'\265', '\004', 'j', '\202', 'j', '\005', '\322', '\n', '\222', 'j', 
		'J', '\005', 'U', '\n', '*', 'J', 'Z', '\002', '\265', '\002', 
		'\262', '1', 'i', '\003', '1', 's', '\251', '\n', 'J', '\005', 
		'-', 'U', '-', '\t', 'Z', '\001', '\325', 'H', '\264', '\t', 
		'h', '\211', 'T', '\013', '\244', '\n', '\245', 'j', '\225', '\004', 
		'\255', '\b', 'j', 'D', '\332', '\004', 't', '\005', '\260', '%', 
		'T', '\003'
	};
	private static int baseYear = 1901;
	private static int baseMonth = 1;
	private static int baseDate = 1;
	private static int baseIndex = 0;
	private static int baseChineseYear = 4597;
	private static int baseChineseMonth = 11;
	private static int baseChineseDate = 11;
	private static int bigLeapMonthYears[] = {
		6, 14, 19, 25, 33, 36, 38, 41, 44, 52, 
		55, 79, 117, 136, 147, 150, 155, 158, 185, 193
	};
	private static char sectionalTermMap[][] = {
		{
			'\007', '\006', '\006', '\006', '\006', '\006', '\006', '\006', '\006', '\005', 
			'\006', '\006', '\006', '\005', '\005', '\006', '\006', '\005', '\005', '\005', 
			'\005', '\005', '\005', '\005', '\005', '\004', '\005', '\005'
		}, {
			'\005', '\004', '\005', '\005', '\005', '\004', '\004', '\005', '\005', '\004', 
			'\004', '\004', '\004', '\004', '\004', '\004', '\004', '\003', '\004', '\004', 
			'\004', '\003', '\003', '\004', '\004', '\003', '\003', '\003'
		}, {
			'\006', '\006', '\006', '\007', '\006', '\006', '\006', '\006', '\005', '\006', 
			'\006', '\006', '\005', '\005', '\006', '\006', '\005', '\005', '\005', '\006', 
			'\005', '\005', '\005', '\005', '\004', '\005', '\005', '\005', '\005'
		}, {
			'\005', '\005', '\006', '\006', '\005', '\005', '\005', '\006', '\005', '\005', 
			'\005', '\005', '\004', '\005', '\005', '\005', '\004', '\004', '\005', '\005', 
			'\004', '\004', '\004', '\005', '\004', '\004', '\004', '\004', '\005'
		}, {
			'\006', '\006', '\006', '\007', '\006', '\006', '\006', '\006', '\005', '\006', 
			'\006', '\006', '\005', '\005', '\006', '\006', '\005', '\005', '\005', '\006', 
			'\005', '\005', '\005', '\005', '\004', '\005', '\005', '\005', '\005'
		}, {
			'\006', '\006', '\007', '\007', '\006', '\006', '\006', '\007', '\006', '\006', 
			'\006', '\006', '\005', '\006', '\006', '\006', '\005', '\005', '\006', '\006', 
			'\005', '\005', '\005', '\006', '\005', '\005', '\005', '\005', '\004', '\005', 
			'\005', '\005', '\005'
		}, {
			'\007', '\b', '\b', '\b', '\007', '\007', '\b', '\b', '\007', '\007', 
			'\007', '\b', '\007', '\007', '\007', '\007', '\006', '\007', '\007', '\007', 
			'\006', '\006', '\007', '\007', '\006', '\006', '\006', '\007', '\007'
		}, {
			'\b', '\b', '\b', '\t', '\b', '\b', '\b', '\b', '\007', '\b', 
			'\b', '\b', '\007', '\007', '\b', '\b', '\007', '\007', '\007', '\b', 
			'\007', '\007', '\007', '\007', '\006', '\007', '\007', '\007', '\006', '\006', 
			'\007', '\007', '\007'
		}, {
			'\b', '\b', '\b', '\t', '\b', '\b', '\b', '\b', '\007', '\b', 
			'\b', '\b', '\007', '\007', '\b', '\b', '\007', '\007', '\007', '\b', 
			'\007', '\007', '\007', '\007', '\006', '\007', '\007', '\007', '\007'
		}, {
			'\t', '\t', '\t', '\t', '\b', '\t', '\t', '\t', '\b', '\b', 
			'\t', '\t', '\b', '\b', '\b', '\t', '\b', '\b', '\b', '\b', 
			'\007', '\b', '\b', '\b', '\007', '\007', '\b', '\b', '\b'
		}, {
			'\b', '\b', '\b', '\b', '\007', '\b', '\b', '\b', '\007', '\007', 
			'\b', '\b', '\007', '\007', '\007', '\b', '\007', '\007', '\007', '\007', 
			'\006', '\007', '\007', '\007', '\006', '\006', '\007', '\007', '\007'
		}, {
			'\007', '\b', '\b', '\b', '\007', '\007', '\b', '\b', '\007', '\007', 
			'\007', '\b', '\007', '\007', '\007', '\007', '\006', '\007', '\007', '\007', 
			'\006', '\006', '\007', '\007', '\006', '\006', '\006', '\007', '\007'
		}
	};
	private static char sectionalTermYear[][] = {
		{
			'\r', '1', 'U', 'u', '\225', '\271', '\311', '\372', '\372'
		}, {
			'\r', '-', 'Q', 'u', '\225', '\271', '\311', '\372', '\372'
		}, {
			'\r', '0', 'T', 'p', '\224', '\270', '\310', '\311', '\372'
		}, {
			'\r', '-', 'L', 'l', '\214', '\254', '\310', '\311', '\372'
		}, {
			'\r', ',', 'H', 'h', '\204', '\250', '\310', '\311', '\372'
		}, {
			'\005', '!', 'D', '`', '|', '\230', '\274', '\310', '\311'
		}, {
			'\035', '9', 'U', 'x', '\224', '\260', '\310', '\311', '\372'
		}, {
			'\r', '0', 'L', 'h', '\204', '\250', '\304', '\310', '\311'
		}, {
			'\031', '<', 'X', 'x', '\224', '\270', '\310', '\311', '\372'
		}, {
			'\020', ',', 'L', 'l', '\220', '\254', '\310', '\311', '\372'
		}, {
			'\034', '<', '\\', '|', '\240', '\300', '\310', '\311', '\372'
		}, {
			'\021', '5', 'U', '|', '\234', '\274', '\310', '\311', '\372'
		}
	};
	private static char principleTermMap[][] = {
		{
			'\025', '\025', '\025', '\025', '\025', '\024', '\025', '\025', '\025', '\024', 
			'\024', '\025', '\025', '\024', '\024', '\024', '\024', '\024', '\024', '\024', 
			'\024', '\023', '\024', '\024', '\024', '\023', '\023', '\024'
		}, {
			'\024', '\023', '\023', '\024', '\024', '\023', '\023', '\023', '\023', '\023', 
			'\023', '\023', '\023', '\022', '\023', '\023', '\023', '\022', '\022', '\023', 
			'\023', '\022', '\022', '\022', '\022', '\022', '\022', '\022'
		}, {
			'\025', '\025', '\025', '\026', '\025', '\025', '\025', '\025', '\024', '\025', 
			'\025', '\025', '\024', '\024', '\025', '\025', '\024', '\024', '\024', '\025', 
			'\024', '\024', '\024', '\024', '\023', '\024', '\024', '\024', '\024'
		}, {
			'\024', '\025', '\025', '\025', '\024', '\024', '\025', '\025', '\024', '\024', 
			'\024', '\025', '\024', '\024', '\024', '\024', '\023', '\024', '\024', '\024', 
			'\023', '\023', '\024', '\024', '\023', '\023', '\023', '\024', '\024'
		}, {
			'\025', '\026', '\026', '\026', '\025', '\025', '\026', '\026', '\025', '\025', 
			'\025', '\026', '\025', '\025', '\025', '\025', '\024', '\025', '\025', '\025', 
			'\024', '\024', '\025', '\025', '\024', '\024', '\024', '\025', '\025'
		}, {
			'\026', '\026', '\026', '\026', '\025', '\026', '\026', '\026', '\025', '\025', 
			'\026', '\026', '\025', '\025', '\025', '\026', '\025', '\025', '\025', '\025', 
			'\024', '\025', '\025', '\025', '\024', '\024', '\025', '\025', '\025'
		}, {
			'\027', '\027', '\030', '\030', '\027', '\027', '\027', '\030', '\027', '\027', 
			'\027', '\027', '\026', '\027', '\027', '\027', '\026', '\026', '\027', '\027', 
			'\026', '\026', '\026', '\027', '\026', '\026', '\026', '\026', '\027'
		}, {
			'\027', '\030', '\030', '\030', '\027', '\027', '\030', '\030', '\027', '\027', 
			'\027', '\030', '\027', '\027', '\027', '\027', '\026', '\027', '\027', '\027', 
			'\026', '\026', '\027', '\027', '\026', '\026', '\026', '\027', '\027'
		}, {
			'\027', '\030', '\030', '\030', '\027', '\027', '\030', '\030', '\027', '\027', 
			'\027', '\030', '\027', '\027', '\027', '\027', '\026', '\027', '\027', '\027', 
			'\026', '\026', '\027', '\027', '\026', '\026', '\026', '\027', '\027'
		}, {
			'\030', '\030', '\030', '\030', '\027', '\030', '\030', '\030', '\027', '\027', 
			'\030', '\030', '\027', '\027', '\027', '\030', '\027', '\027', '\027', '\027', 
			'\026', '\027', '\027', '\027', '\026', '\026', '\027', '\027', '\027'
		}, {
			'\027', '\027', '\027', '\027', '\026', '\027', '\027', '\027', '\026', '\026', 
			'\027', '\027', '\026', '\026', '\026', '\027', '\026', '\026', '\026', '\026', 
			'\025', '\026', '\026', '\026', '\025', '\025', '\026', '\026', '\026'
		}, {
			'\026', '\026', '\027', '\027', '\026', '\026', '\026', '\027', '\026', '\026', 
			'\026', '\026', '\025', '\026', '\026', '\026', '\025', '\025', '\026', '\026', 
			'\025', '\025', '\025', '\026', '\025', '\025', '\025', '\025', '\026'
		}
	};
	private static char principleTermYear[][] = {
		{
			'\r', '-', 'Q', 'q', '\225', '\271', '\311'
		}, {
			'\025', '9', ']', '}', '\241', '\301', '\311'
		}, {
			'\025', '8', 'X', 'x', '\230', '\274', '\310', '\311'
		}, {
			'\025', '1', 'Q', 't', '\220', '\260', '\310', '\311'
		}, {
			'\021', '1', 'M', 'p', '\214', '\250', '\310', '\311'
		}, {
			'\034', '<', 'X', 't', '\224', '\264', '\310', '\311'
		}, {
			'\031', '5', 'T', 'p', '\220', '\254', '\310', '\311'
		}, {
			'\035', '9', 'Y', 'x', '\224', '\264', '\310', '\311'
		}, {
			'\021', '-', 'I', 'l', '\214', '\250', '\310', '\311'
		}, {
			'\034', '<', '\\', '|', '\240', '\300', '\310', '\311'
		}, {
			'\020', ',', 'P', 'p', '\224', '\264', '\310', '\311'
		}, {
			'\021', '5', 'X', 'x', '\234', '\274', '\310', '\311'
		}
	};
	private static String monthNames[] = {
		"һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", 
		"ʮһ", "ʮ��"
	};
	private static String chineseDateNames[] = {
		"��һ", "����", "����", "����", "����", "����", "����", "����", "����", "��ʮ", 
		"ʮһ", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "��ʮ", 
		"إһ", "إ��", "إ��", "إ��", "إ��", "إ��", "إ��", "إ��", "إ��", "��ʮ"
	};
	private static String chineseMonthNames[] = {
		"��", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", 
		"��", "��"
	};
	private static String principleTermNames[] = {
		"��", "��ˮ", "����", "����", "����", "����", "����", "����", "���", "˪��", 
		"Сѩ", "����"
	};
	private static String sectionalTermNames[] = {
		"С��", "����", "����", "����", "����", "â��", "С��", "����", "��¶", "��¶", 
		"����", "��ѩ"
	};

	public static void main(String arg[])
	{
		ChineseCalendarGB c = new ChineseCalendarGB();
		String cmd = "day";
		int y = 1901;
		int m = 1;
		int d = 1;
		if (arg.length > 0)
			cmd = arg[0];
		if (arg.length > 1)
			y = Integer.parseInt(arg[1]);
		if (arg.length > 2)
			m = Integer.parseInt(arg[2]);
		if (arg.length > 3)
			d = Integer.parseInt(arg[3]);
		c.setGregorian(y, m, d);
		c.computeChineseFields();
		c.computeSolarTerms();
		if (cmd.equalsIgnoreCase("year"))
		{
			String t[] = c.getYearTable();
			for (int i = 0; i < t.length; i++)
				System.out.println(t[i]);

		} else
		if (cmd.equalsIgnoreCase("month"))
		{
			String t[] = c.getMonthTable();
			for (int i = 0; i < t.length; i++)
				System.out.println(t[i]);

		} else
		{
			System.out.println(c.toString());
		}
	}

	public ChineseCalendarGB()
	{
		setGregorian(1901, 1, 1);
	}

	public void setGregorian(int y, int m, int d)
	{
		gregorianYear = y;
		gregorianMonth = m;
		gregorianDate = d;
		isGregorianLeap = isGregorianLeapYear(y);
		dayOfYear = dayOfYear(y, m, d);
		dayOfWeek = dayOfWeek(y, m, d);
		chineseYear = 0;
		chineseMonth = 0;
		chineseDate = 0;
		sectionalTerm = 0;
		principleTerm = 0;
	}

	public static boolean isGregorianLeapYear(int year)
	{
		boolean isLeap = false;
		if (year % 4 == 0)
			isLeap = true;
		if (year % 100 == 0)
			isLeap = false;
		if (year % 400 == 0)
			isLeap = true;
		return isLeap;
	}

	public static int daysInGregorianMonth(int y, int m)
	{
		int d = daysInGregorianMonth[m - 1];
		if (m == 2 && isGregorianLeapYear(y))
			d++;
		return d;
	}

	public static int dayOfYear(int y, int m, int d)
	{
		int c = 0;
		for (int i = 1; i < m; i++)
			c += daysInGregorianMonth(y, i);

		c += d;
		return c;
	}

	public static int dayOfWeek(int y, int m, int d)
	{
		int w = 1;
		y = (y - 1) % 400 + 1;
		int ly = (y - 1) / 4;
		ly -= (y - 1) / 100;
		ly += (y - 1) / 400;
		int ry = y - 1 - ly;
		w += ry;
		w += 2 * ly;
		w += dayOfYear(y, m, d);
		w = (w - 1) % 7 + 1;
		return w;
	}

	public int computeChineseFields()
	{
		if (gregorianYear < 1901 || gregorianYear > 2100)
			return 1;
		int startYear = baseYear;
		int startMonth = baseMonth;
		int startDate = baseDate;
		chineseYear = baseChineseYear;
		chineseMonth = baseChineseMonth;
		chineseDate = baseChineseDate;
		if (gregorianYear >= 2000)
		{
			startYear = baseYear + 99;
			startMonth = 1;
			startDate = 1;
			chineseYear = baseChineseYear + 99;
			chineseMonth = 11;
			chineseDate = 25;
		}
		int daysDiff = 0;
		for (int i = startYear; i < gregorianYear; i++)
		{
			daysDiff += 365;
			if (isGregorianLeapYear(i))
				daysDiff++;
		}

		for (int i = startMonth; i < gregorianMonth; i++)
			daysDiff += daysInGregorianMonth(gregorianYear, i);

		daysDiff += gregorianDate - startDate;
		chineseDate += daysDiff;
		int lastDate = daysInChineseMonth(chineseYear, chineseMonth);
		for (int nextMonth = nextChineseMonth(chineseYear, chineseMonth); chineseDate > lastDate; nextMonth = nextChineseMonth(chineseYear, chineseMonth))
		{
			if (Math.abs(nextMonth) < Math.abs(chineseMonth))
				chineseYear++;
			chineseMonth = nextMonth;
			chineseDate -= lastDate;
			lastDate = daysInChineseMonth(chineseYear, chineseMonth);
		}

		return 0;
	}

	public static int daysInChineseMonth(int y, int m)
	{
		int index = (y - baseChineseYear) + baseIndex;
		int v = 0;
		int l = 0;
		int d = 30;
		if (1 <= m && m <= 8)
		{
			v = chineseMonths[2 * index];
			l = m - 1;
			if ((v >> l & 1) == 1)
				d = 29;
		} else
		if (9 <= m && m <= 12)
		{
			v = chineseMonths[2 * index + 1];
			l = m - 9;
			if ((v >> l & 1) == 1)
				d = 29;
		} else
		{
			v = chineseMonths[2 * index + 1];
			v = v >> 4 & 0xf;
			if (v != Math.abs(m))
			{
				d = 0;
			} else
			{
				d = 29;
				for (int i = 0; i < bigLeapMonthYears.length; i++)
				{
					if (bigLeapMonthYears[i] != index)
						continue;
					d = 30;
					break;
				}

			}
		}
		return d;
	}

	public static int nextChineseMonth(int y, int m)
	{
		int n = Math.abs(m) + 1;
		if (m > 0)
		{
			int index = (y - baseChineseYear) + baseIndex;
			int v = chineseMonths[2 * index + 1];
			v = v >> 4 & 0xf;
			if (v == m)
				n = -m;
		}
		if (n == 13)
			n = 1;
		return n;
	}

	public int computeSolarTerms()
	{
		if (gregorianYear < 1901 || gregorianYear > 2100)
		{
			return 1;
		} else
		{
			sectionalTerm = sectionalTerm(gregorianYear, gregorianMonth);
			principleTerm = principleTerm(gregorianYear, gregorianMonth);
			return 0;
		}
	}

	public static int sectionalTerm(int y, int m)
	{
		if (y < 1901 || y > 2100)
			return 0;
		int index = 0;
		int ry;
		for (ry = (y - baseYear) + 1; ry >= sectionalTermYear[m - 1][index]; index++);
		int term = sectionalTermMap[m - 1][4 * index + ry % 4];
		if (ry == 121 && m == 4)
			term = 5;
		if (ry == 132 && m == 4)
			term = 5;
		if (ry == 194 && m == 6)
			term = 6;
		return term;
	}

	public static int principleTerm(int y, int m)
	{
		if (y < 1901 || y > 2100)
			return 0;
		int index = 0;
		int ry;
		for (ry = (y - baseYear) + 1; ry >= principleTermYear[m - 1][index]; index++);
		int term = principleTermMap[m - 1][4 * index + ry % 4];
		if (ry == 171 && m == 3)
			term = 21;
		if (ry == 181 && m == 5)
			term = 21;
		return term;
	}

	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append((new StringBuilder("Gregorian Year: ")).append(gregorianYear).append("\n").toString());
		buf.append((new StringBuilder("Gregorian Month: ")).append(gregorianMonth).append("\n").toString());
		buf.append((new StringBuilder("Gregorian Date: ")).append(gregorianDate).append("\n").toString());
		buf.append((new StringBuilder("Is Leap Year: ")).append(isGregorianLeap).append("\n").toString());
		buf.append((new StringBuilder("Day of Year: ")).append(dayOfYear).append("\n").toString());
		buf.append((new StringBuilder("Day of Week: ")).append(dayOfWeek).append("\n").toString());
		buf.append((new StringBuilder("Chinese Year: ")).append(chineseYear).append("\n").toString());
		buf.append((new StringBuilder("Heavenly Stem: ")).append((chineseYear - 1) % 10).append("\n").toString());
		buf.append((new StringBuilder("Earthly Branch: ")).append((chineseYear - 1) % 12).append("\n").toString());
		buf.append((new StringBuilder("Chinese Month: ")).append(chineseMonth).append("\n").toString());
		buf.append((new StringBuilder("Chinese Date: ")).append(chineseDate).append("\n").toString());
		buf.append((new StringBuilder("Sectional Term: ")).append(sectionalTerm).append("\n").toString());
		buf.append((new StringBuilder("Principle Term: ")).append(principleTerm).append("\n").toString());
		return buf.toString();
	}

	public String[] getYearTable()
	{
		setGregorian(gregorianYear, 1, 1);
		computeChineseFields();
		computeSolarTerms();
		String table[] = new String[58];
		table[0] = getTextLine(27, (new StringBuilder("����������")).append(gregorianYear).toString());
		table[1] = getTextLine(27, (new StringBuilder("ũ��������")).append(chineseYear + 1).append(" (").append(stemNames[((chineseYear + 1) - 1) % 10]).append(branchNames[((chineseYear + 1) - 1) % 12]).append(" - ").append(animalNames[((chineseYear + 1) - 1) % 12]).append("��)").toString());
		int ln = 2;
		String blank = "                                                                                    ";
		String mLeft[] = (String[])null;
		String mRight[] = (String[])null;
		for (int i = 1; i <= 6; i++)
		{
			table[ln] = blank;
			ln++;
			mLeft = getMonthTable();
			mRight = getMonthTable();
			for (int j = 0; j < mLeft.length; j++)
			{
				String line = (new StringBuilder(String.valueOf(mLeft[j]))).append("  ").append(mRight[j]).toString();
				table[ln] = line;
				ln++;
			}

		}

		table[ln] = blank;
		ln++;
		table[ln] = getTextLine(0, "##/## - ��������/ũ�����ڣ�(*)#�� - (��)ũ���µ�һ��");
		ln++;
		return table;
	}

	public static String getTextLine(int s, String t)
	{
		String str = "                                                                                    ";
		if (t != null && s < str.length() && s + t.length() < str.length())
			str = (new StringBuilder(String.valueOf(str.substring(0, s)))).append(t).append(str.substring(s + t.length())).toString();
		return str;
	}

	public String[] getMonthTable()
	{
		setGregorian(gregorianYear, gregorianMonth, 1);
		computeChineseFields();
		computeSolarTerms();
		String table[] = new String[8];
		String title = null;
		if (gregorianMonth < 11)
			title = "                   ";
		else
			title = "                 ";
		title = (new StringBuilder(String.valueOf(title))).append(monthNames[gregorianMonth - 1]).append("��").append("                   ").toString();
		String header = "   ��    һ    ��    ��    ��    ��    �� ";
		String blank = "                                          ";
		table[0] = title;
		table[1] = header;
		int wk = 2;
		String line = "";
		for (int i = 1; i < dayOfWeek; i++)
			line = (new StringBuilder(String.valueOf(line))).append("      ").toString();

		int days = daysInGregorianMonth(gregorianYear, gregorianMonth);
		for (int i = gregorianDate; i <= days; i++)
		{
			line = (new StringBuilder(String.valueOf(line))).append(getDateString()).append(' ').toString();
			rollUpOneDay();
			if (dayOfWeek == 1)
			{
				table[wk] = line;
				line = "";
				wk++;
			}
		}

		for (int i = dayOfWeek; i <= 7; i++)
			line = (new StringBuilder(String.valueOf(line))).append("      ").toString();

		table[wk] = line;
		for (int i = wk + 1; i < table.length; i++)
			table[i] = blank;

		for (int i = 0; i < table.length; i++)
			table[i] = table[i].substring(0, table[i].length() - 1);

		return table;
	}

	public String getDateString()
	{
		String str = "*  /  ";
		String gm = String.valueOf(gregorianMonth);
		if (gm.length() == 1)
			gm = (new StringBuilder(String.valueOf(' '))).append(gm).toString();
		String cm = String.valueOf(Math.abs(chineseMonth));
		if (cm.length() == 1)
			cm = (new StringBuilder(String.valueOf(' '))).append(cm).toString();
		String gd = String.valueOf(gregorianDate);
		if (gd.length() == 1)
			gd = (new StringBuilder(String.valueOf(' '))).append(gd).toString();
		String cd = String.valueOf(chineseDate);
		if (cd.length() == 1)
			cd = (new StringBuilder(String.valueOf(' '))).append(cd).toString();
		if (gregorianDate == sectionalTerm)
			str = (new StringBuilder(" ")).append(sectionalTermNames[gregorianMonth - 1]).toString();
		else
		if (gregorianDate == principleTerm)
			str = (new StringBuilder(" ")).append(principleTermNames[gregorianMonth - 1]).toString();
		else
		if (chineseDate == 1 && chineseMonth > 0)
			str = (new StringBuilder(" ")).append(chineseMonthNames[chineseMonth - 1]).append("��").toString();
		else
		if (chineseDate == 1 && chineseMonth < 0)
			str = (new StringBuilder("*")).append(chineseMonthNames[-chineseMonth - 1]).append("��").toString();
		else
			str = (new StringBuilder(String.valueOf(gd))).append('/').append(cd).toString();
		return str;
	}

	public String getChineseDateString()
	{
		StringBuffer str = new StringBuffer((new StringBuilder(String.valueOf(stemNames[(chineseYear - 1) % 10]))).append(branchNames[(chineseYear - 1) % 12]).append("��").toString());
		if (chineseMonth > 0)
			str.append((new StringBuilder(String.valueOf(chineseMonthNames[chineseMonth - 1]))).append("��").toString());
		else
		if (chineseMonth < 0)
			str.append((new StringBuilder("��")).append(chineseMonthNames[-chineseMonth - 1]).append("��").toString());
		str.append(chineseDateNames[chineseDate - 1]);
		if (gregorianDate == sectionalTerm)
			str.append((new StringBuilder("(")).append(sectionalTermNames[gregorianMonth - 1]).append(")").toString());
		else
		if (gregorianDate == principleTerm)
			str.append((new StringBuilder("(")).append(principleTermNames[gregorianMonth - 1]).append(")").toString());
		return str.toString();
	}

	public int rollUpOneDay()
	{
		dayOfWeek = dayOfWeek % 7 + 1;
		dayOfYear++;
		gregorianDate++;
		int days = daysInGregorianMonth(gregorianYear, gregorianMonth);
		if (gregorianDate > days)
		{
			gregorianDate = 1;
			gregorianMonth++;
			if (gregorianMonth > 12)
			{
				gregorianMonth = 1;
				gregorianYear++;
				dayOfYear = 1;
				isGregorianLeap = isGregorianLeapYear(gregorianYear);
			}
			sectionalTerm = sectionalTerm(gregorianYear, gregorianMonth);
			principleTerm = principleTerm(gregorianYear, gregorianMonth);
		}
		chineseDate++;
		days = daysInChineseMonth(chineseYear, chineseMonth);
		if (chineseDate > days)
		{
			chineseDate = 1;
			chineseMonth = nextChineseMonth(chineseYear, chineseMonth);
			if (chineseMonth == 1)
				chineseYear++;
		}
		return 0;
	}

	public int getChineseDate()
	{
		return chineseDate;
	}

	public int getChineseMonth()
	{
		return chineseMonth;
	}

	public int getChineseYear()
	{
		return (chineseYear - baseChineseYear - 1) + baseYear;
	}

	public int getGregorianDate()
	{
		return gregorianDate;
	}

	public int getGregorianMonth()
	{
		return gregorianMonth;
	}

	public int getGregorianYear()
	{
		return gregorianYear;
	}

	public static ChineseCalendarGB computeChineseToGregorian(int year, int month, int day)
	{
		ChineseCalendarGB c = new ChineseCalendarGB();
		int cm = month;
		int cd = day;
		c.setGregorian(year, Math.abs(month), day);
		c.computeChineseFields();
		c.computeSolarTerms();
		for (int count = 0; count < 100 && (cm != c.chineseMonth || cd != c.chineseDate); count++)
			c.rollUpOneDay();

		return c;
	}

	public static ChineseCalendarGB computeChinese(int year, int month, int day)
	{
		ChineseCalendarGB c = new ChineseCalendarGB();
		c.setGregorian(year, month, day);
		c.computeChineseFields();
		c.computeSolarTerms();
		return c;
	}

}
