// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonUtil.java

package com.astrology.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil
{

	public CommonUtil()
	{
	}

	public static Date parseDate(String dateString, String timezone)
		throws IllegalArgumentException
	{
		if (isEmpty(dateString))
			throw new IllegalArgumentException("Date string can not be empty");
		TimeZone tz;
		if (isEmpty(timezone))
			tz = TimeZone.getDefault();
		else
			tz = TimeZone.getTimeZone(timezone);
		StringTokenizer st = new StringTokenizer(dateString, " -:./");
		Calendar cal = Calendar.getInstance(tz);
		int flds[] = new int[7];
		flds[0] = 1;
		flds[1] = 2;
		flds[2] = 5;
		flds[3] = 11;
		flds[4] = 12;
		flds[5] = 13;
		flds[6] = 14;
		int index;
		for (index = 0; st.hasMoreTokens() && index < 7;)
			try
			{
				int value = Integer.parseInt(st.nextToken());
				if (index == 1)
					value--;
				cal.set(flds[index], value);
				index++;
			}
			catch (Exception e)
			{
				throw new IllegalArgumentException(dateString);
			}

		for (; index < 7; index++)
			cal.set(flds[index], 0);

		return cal.getTime();
	}

	public static boolean isEmpty(String s)
	{
		return s == null || s.trim().length() == 0;
	}

	public static String formatDate(Date date, String pattern)
	{
		return (new SimpleDateFormat(pattern)).format(date);
	}
}
