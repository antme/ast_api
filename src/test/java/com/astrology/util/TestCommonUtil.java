// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestCommonUtil.java

package com.astrology.util;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.astrology.util.CommonUtil;

import junit.framework.TestCase;

// Referenced classes of package com.astrology.util:
//			CommonUtil

public class TestCommonUtil extends TestCase
{

	int count;

	public TestCommonUtil()
	{
		count = 10000;
	}

	public void testParseDate()
	{
		Date d = CommonUtil.parseDate("2002-01-02", null);
		System.out.println(d);
		Date d2 = CommonUtil.parseDate("2002-1-2 0:00:00", TimeZone.getDefault().getID());
		assertEquals(d, d2);
		d2 = CommonUtil.parseDate("2002/01/02 0:0:0", null);
		assertEquals(d, d2);
	}

	public void testFormatDate()
	{
		String pattern = "yyyyMMdd";
		for (int i = 0; i < count; i++)
			CommonUtil.formatDate(new Date(), pattern);

	}

	public void testFormatDate2()
	{
		String pattern = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		for (int i = 0; i < count; i++)
			sdf.format(new Date());

	}

	public void testFormatString()
	{
		MessageFormat mf = new MessageFormat("{0}-{1}-{2}-{3}");
	}
}
