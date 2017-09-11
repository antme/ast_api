// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestPlanetInfo.java

package com.astrology;

import com.astrology.PlanetInfo;

import junit.framework.Assert;
import junit.framework.TestCase;

// Referenced classes of package com.astrology:
//			PlanetInfo

public class TestPlanetInfo extends TestCase
{

	public TestPlanetInfo()
	{
	}

	public void testGetPlanetNameInt()
	{
		String pn = PlanetInfo.getPlanetName(0);
		Assert.assertEquals(pn, "Sun");
		pn = PlanetInfo.getPlanetName(1);
		Assert.assertEquals(pn, "Moon");
		pn = PlanetInfo.getPlanetName(-99);
		Assert.assertEquals(pn, "Asc");
		pn = PlanetInfo.getPlanetName(-86);
		Assert.assertEquals(pn, "Fort");
		pn = PlanetInfo.getPlanetName(-89);
		Assert.assertEquals(pn, "11th");
		pn = PlanetInfo.getPlanetName(-98);
		Assert.assertEquals(pn, "2nd");
	}
}
