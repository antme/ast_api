// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommandParser.java

package com.astrology.util;

import java.text.ParseException;

import com.astrology.ChartModel;

// Referenced classes of package com.astrology.util:
//			DegreeUtil

public class CommandParser
{

	public CommandParser()
	{
	}

	public static ChartModel parse(String command)
		throws ParseException
	{
		String args[] = command.split("\\s");
		return parse(args);
	}

	public static ChartModel parse(String args[])
		throws ParseException
	{
		String op = args[0].substring(1);
		String time;
		String timezone;
		String longi;
		String lati;
		if ("qa".equals(op))
		{
			time = (new StringBuilder(String.valueOf(args[3]))).append("-").append(args[1]).append("-").append(args[2]).append(" ").append(args[4]).toString();
			timezone = args[5];
			longi = args[6];
			lati = args[7];
		} else
		if ("qb".equals(op))
		{
			time = (new StringBuilder(String.valueOf(args[3]))).append("-").append(args[1]).append("-").append(args[2]).append(" ").append(args[4]).toString();
			timezone = args[6];
			longi = args[7];
			lati = args[8];
		} else
		{
			throw new IllegalArgumentException(args[0]);
		}
		if (timezone.charAt(0) == '+')
			timezone = (new StringBuilder("GMT")).append(timezone.replace('+', '-')).toString();
		else
		if (timezone.charAt(0) == '-')
			timezone = (new StringBuilder("GMT")).append(timezone.replace('-', '+')).toString();
		ChartModel model = new ChartModel(time, timezone, DegreeUtil.parseAxis(lati), DegreeUtil.parseAxis(longi));
		return model;
	}
}
