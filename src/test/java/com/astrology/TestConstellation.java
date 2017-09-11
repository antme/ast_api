// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestConstellation.java

package com.astrology;

import java.io.PrintStream;

import com.astrology.Calculator;
import com.astrology.Constellation;

import junit.framework.TestCase;

// Referenced classes of package com.astrology:
//			Constellation, Calculator

public class TestConstellation extends TestCase
{

	public TestConstellation()
	{
	}

	public void testPulluxs()
	{
		for (int i = 0; i < Constellation.POLLUXS.length; i++)
			System.out.println((new StringBuilder(String.valueOf(Constellation.POLLUXS[i]))).append(".color=").toString());

	}

	public void testPlanet()
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 21; i++)
		{
			String name = Calculator.getPlanetName(i);
			String sname;
			if (name.length() > 4)
				sname = name.substring(0, 4);
			else
				sname = name;
			System.out.println((new StringBuilder(String.valueOf(sname))).append("=").append(name).toString());
			sb.append((new StringBuilder("\"")).append(sname).append("\",").toString());
		}

		System.out.println(sb);
	}
}
