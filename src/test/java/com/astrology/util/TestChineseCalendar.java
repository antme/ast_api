package com.astrology.util;
//// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
//// Jad home page: http://kpdus.tripod.com/jad.html
//// Decompiler options: packimports(3) fieldsfirst ansi space 
//// Source File Name:   TestChineseCalendar.java
//
//package com.astrology.util;
//
//import java.io.PrintStream;
//import junit.framework.TestCase;
//
//// Referenced classes of package com.astrology.util:
////			ChineseCalendarGB
//
//public class TestChineseCalendar extends TestCase
//{
//
//	public TestChineseCalendar()
//	{
//	}
//
//	public void testGetDateString()
//	{
//		System.out.println("");
//		ChineseCalendarGB c = new ChineseCalendarGB();
//		for (int i = 1; i < 31; i++)
//		{
//			c.setGregorian(2006, 5, i);
//			c.computeChineseFields();
//			c.computeSolarTerms();
//			System.out.println((new StringBuilder("2006-5-")).append(i).append("\t").append(c.getChineseDateString()).toString());
//		}
//
//	}
//
//	public void testChineseYear()
//	{
//		System.out.println("");
//		ChineseCalendarGB c = new ChineseCalendarGB();
//		for (int i = 1; i < 31; i++)
//		{
//			c.setGregorian(2006, 1, i);
//			c.computeChineseFields();
//			c.computeSolarTerms();
//		}
//
//	}
//
//	public void testConvertChineseToGe()
//	{
//		System.out.println("");
//		ChineseCalendarGB c0 = new ChineseCalendarGB();
//		for (int i = 1; i < 31; i++)
//		{
//			c0.setGregorian(2006, 1, i);
//			c0.computeChineseFields();
//			c0.computeSolarTerms();
//			ChineseCalendarGB chinesecalendargb = ChineseCalendarGB.computeChineseToGregorian(c0.getChineseYear(), c0.getChineseMonth(), c0.getChineseDate());
//		}
//
//	}
//}
