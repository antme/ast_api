// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestDegreeUtil.java

package com.astrology.util;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.astrology.util.DegreeUtil;

import junit.framework.TestCase;

// Referenced classes of package com.astrology.util:
//			DegreeUtil

public class TestDegreeUtil extends TestCase
{

	public TestDegreeUtil()
	{
	}

	public void testFormat()
	{
		double angle = 10.205D;
		String s = DegreeUtil.format(angle);
		System.out.println((new StringBuilder("10.2:")).append(s).toString());
		assertEquals("10Ari12", s);
		s = DegreeUtil.format(angle, "hpm's");
		System.out.println(s);
		assertEquals("10Ari12'18", s);
		angle = 35.395000000000003D;
		s = DegreeUtil.format(angle);
		System.out.println((new StringBuilder("35.4:")).append(s).toString());
		assertEquals("5Tau24", s);
		s = DegreeUtil.format(angle, "hpm's");
		System.out.println(s);
		assertEquals("5Tau23'42", s);
		s = DegreeUtil.format(angle, "+ h:m's");
		System.out.println(s);
		assertEquals("+ 35:23'42", s);
		s = DegreeUtil.format(0.0D - angle, "+h:m's");
		assertEquals("-35:23'42", s);
		s = DegreeUtil.format(angle, "h:m:sw");
		System.out.println(s);
		assertEquals("35:23:42E", s);
		s = DegreeUtil.format(0.0D - angle, "h:m:sn");
		assertEquals("35:23:42S", s);
	}

	public void testParseAxis()
		throws ParseException
	{
		double d = DegreeUtil.parseAxis("35:30S");
		System.out.println(d);
		assertEquals(Double.valueOf(-35.5D), Double.valueOf(d));
		d = DegreeUtil.parseAxis("36:15E");
		assertEquals(Double.valueOf(36.25D), Double.valueOf(d));
		d = DegreeUtil.parseAxis("36:30W");
		assertEquals(Double.valueOf(-36.5D), Double.valueOf(d));
		d = DegreeUtil.parseAxis("34:12N");
		assertEquals(Double.valueOf(34.200000000000003D), Double.valueOf(d));
		d = DegreeUtil.parseAxis("34:20N");
		System.out.println(d);
	}

	public void testGroup()
	{
		String pattern = "(\\d+)([\\p{Punct}WENS])";
		Pattern p = Pattern.compile(pattern);
		for (Matcher m = p.matcher("-34:20:40-df-123W"); m.find();)
		{
			System.out.println((new StringBuilder("group count:")).append(m.groupCount()).toString());
			for (int i = 0; i <= m.groupCount(); i++)
				System.out.println((new StringBuilder("Group ")).append(i).append(" ").append(m.group(i)).toString());

		}

	}
}
