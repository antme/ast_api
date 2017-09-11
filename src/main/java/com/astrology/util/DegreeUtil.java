// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DegreeUtil.java

package com.astrology.util;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.astrology.Constellation;

import net.sf.anole.Messager;
import net.sf.anole.MessagerFactory;

public class DegreeUtil
{

	private static Messager messager = MessagerFactory.getMessager("com.astrology");

	public DegreeUtil()
	{
	}

	public static String format(double degree)
	{
		return format(degree, "hpm");
	}

	public static String format(double degree, String pattern)
	{
		StringBuffer sb = new StringBuffer();
		String direct = "";
		String upattern = pattern.toLowerCase();
		if (pattern.indexOf("+") == 0)
		{
			if (degree < 0.0D)
			{
				degree = 0.0D - degree;
				sb.append("-");
			} else
			{
				sb.append("+");
			}
			pattern = pattern.substring(1);
		} else
		if (upattern.indexOf("w") >= 0)
		{
			if (degree < 0.0D)
			{
				degree = 0.0D - degree;
				direct = "W";
			} else
			{
				direct = "E";
			}
		} else
		if (upattern.indexOf("n") >= 0)
		{
			if (degree < 0.0D)
			{
				degree = 0.0D - degree;
				direct = "S";
			} else
			{
				direct = "N";
			}
		} else
		if (upattern.indexOf("p") >= 0)
			degree = fixAngle(degree);
		int d = (int)(degree / 30D);
		double r;
		if (upattern.indexOf("p") == -1)
			r = degree;
		else
			r = degree % 30D;
		int h = (int)r;
		double dm = (r - (double)h) * 60D;
		int m = (int)dm;
		double ds = (dm - (double)m) * 60D;
		int s = (int)ds;
		if (s > 30 && pattern.indexOf("s") == -1 && ++m == 60)
		{
			m = 0;
			h++;
		}
		char pchars[] = pattern.toCharArray();
		for (int i = 0; i < pchars.length; i++)
		{
			char c = pchars[i];
			switch (c)
			{
			case 104: // 'h'
				sb.append(h);
				break;

			case 109: // 'm'
				if (m < 10)
					sb.append('0');
				sb.append(m);
				break;

			case 115: // 's'
				if (s < 10)
					sb.append('0');
				sb.append(s);
				break;

			case 112: // 'p'
				sb.append(Constellation.POLLUXS[d]);
				break;

			case 80: // 'P'
				sb.append(messager.getMessage(Constellation.POLLUXS[d]));
				break;

			case 110: // 'n'
			case 119: // 'w'
				sb.append(direct);
				break;

			case 78: // 'N'
			case 87: // 'W'
				sb.append(messager.getMessage((new StringBuilder("axis.")).append(direct).toString()));
				break;

			default:
				sb.append(c);
				break;
			}
		}

		return sb.toString();
	}

	public static double parseAxis(String degree)
		throws ParseException
	{
		String pattern = "(\\d+)([\\p{Punct}WENS])";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(degree);
		double result = 0.0D;
		String d = "";
		int count;
		for (count = 0; m.find(); count++)
		{
			result += Double.parseDouble(m.group(1)) / Math.pow(60D, count);
			d = m.group(2);
		}

		if (count == 0)
			throw new ParseException((new StringBuilder(String.valueOf(degree))).append(" does not matches the pattern ").append(pattern).toString(), 0);
		if (d.equals("W") || d.equals("S") || degree.startsWith("-"))
			result = 0.0D - result;
		return result;
	}

	public static double transfer(double orgi, double asc)
	{
		return (orgi - asc) + 180D;
	}

	public static double d2R(double degree)
	{
		return (degree * 3.1415926535897931D) / 180D;
	}

	public static double fixAngle(double angle)
	{
		for (; angle < 0.0D; angle += 360D);
		for (; angle > 360D; angle -= 360D);
		return angle;
	}

}
