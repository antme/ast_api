// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestLocationProvider.java

package com.astrology.util;

import java.io.*;

import com.astrology.util.Location;
import com.astrology.util.LocationProvider;

import junit.framework.Assert;
import junit.framework.TestCase;

// Referenced classes of package com.astrology.util:
//			LocationProvider

public class TestLocationProvider extends TestCase
{

	private LocationProvider lp;

	public TestLocationProvider()
	{
	}

	public void setUp()
	{
		try
		{
			lp = new LocationProvider(new FileInputStream("resource/CN.xls"));
		}
		catch (IOException e)
		{
			Assert.fail(e.toString());
			e.printStackTrace();
		}
	}

	public void testListProvince()
	{
		String provinces[] = lp.listProvinces();
		System.out.println((new StringBuilder(String.valueOf(provinces.length))).append(" provinces").toString());
		for (int i = 0; i < provinces.length; i++)
			System.out.println((new StringBuilder("<option value=\"")).append(provinces[i]).append("\">").append(provinces[i]).append("</option>").toString());

	}

	public void testListLocation()
	{
		Location locs[] = lp.listLocations("����ʡ");
		for (int i = 0; i < locs.length; i++)
		{
			Location loc = locs[i];
			System.out.println(loc);
		}

	}
}
