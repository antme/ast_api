// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestCommandParser.java

package com.astrology.util;

import java.text.ParseException;
import java.util.TimeZone;

import com.astrology.ChartModel;
import com.astrology.util.CommandParser;

import junit.framework.TestCase;

// Referenced classes of package com.astrology.util:
//			CommandParser

public class TestCommandParser extends TestCase
{

	public TestCommandParser()
	{
	}

	public void testTimeZoneId()
	{
		System.out.println(TimeZone.getTimeZone("-8:00"));
		System.out.println(TimeZone.getTimeZone("GMT-8:00"));
	}

	public void testParse()
		throws ParseException
	{
		ChartModel model = CommandParser.parse("-qb 10 28 1955 22:00 ST +8:00 122:20W 47:36N");
		System.out.println((new StringBuilder("TimeZone:\t")).append(model.getTimezone()).toString());
		assertEquals(TimeZone.getTimeZone("GMT-8:00"), model.getTimezone());
		System.out.println((new StringBuilder("Date:\t")).append(model.getFormatDate()).toString());
		System.out.println((new StringBuilder("Location:\t")).append(model.getLocation()).toString());
		assertEquals(Double.valueOf(47.600000000000001D), Double.valueOf(model.getLatitude()));
		assertTrue(Math.abs(-122.33D - model.getLongitude()) < 0.01D);
	}
}
