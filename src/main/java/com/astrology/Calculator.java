// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Calculator.java

package com.astrology;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import astro.api.ConfigBean;
import de.thmac.swisseph.SweDate;
import de.thmac.swisseph.SwissEph;
import de.thmac.swisseph.SwissephException;

// Referenced classes of package com.astrology:
//			PlanetInfo, HousesInfo

public class Calculator
{

	static SwissEph sw = null;
	public static final TimeZone GMT0 = TimeZone.getTimeZone("GMT+0");


	public Calculator()
	{
		if (sw == null)
			initSwissEph();
	}

	private static SwissEph initSwissEph()
	{
		sw = new SwissEph(ConfigBean.getProperty("ephemeris_path"));
		return sw;
	}

	public SweDate calcSweDate(Date date)
	{
		Calendar calendar = Calendar.getInstance(GMT0);
		calendar.setTime(date);
		SweDate sd = new SweDate(calendar.get(1), calendar.get(2) + 1, calendar.get(5), (double)calendar.get(11) + (double)calendar.get(12) / 60D + (double)calendar.get(13) / 3600D + (double)calendar.get(14) / 3600000D);
		return sd;
	}

	public SweDate calcSweDate(int year, int month, int day, int hour, int minutes, int second, String timezone)
	{
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
		calendar.set(1, year);
		calendar.set(2, month - 1);
		calendar.set(5, day);
		calendar.set(11, hour);
		calendar.set(12, minutes);
		calendar.set(13, second);
		calendar.set(14, 0);
		return calcSweDate(calendar.getTime());
	}

	public PlanetInfo calc(SweDate sd, int planet, int iflag)
	{
		return calc(sd.getJulDay(), planet, iflag);
	}

	public PlanetInfo calc(double julianDay, int planet, int iflag)
	{
		double res[] = new double[6];
		StringBuffer sbErr = new StringBuffer();
		int rc = sw.swe_calc_ut(julianDay, planet, iflag, res, sbErr);
		if (sbErr.length() > 0)
			System.err.println(sbErr.toString());
		if (rc == -1)
			throw new SwissephException(julianDay, sbErr.toString());
		else
			return new PlanetInfo(planet, res);
	}

	public PlanetInfo calc(SweDate sd, int planet)
	{
		return calc(sd, planet, 256);
	}

	public PlanetInfo calc(double julianDay, int planet)
	{
		return calc(julianDay, planet, 256);
	}

	public HousesInfo houses(SweDate sd, double latitude, double longitude, int hsy)
	{
		double cusp[] = new double[13];
		double ascmc[] = new double[10];
		int result = sw.swe_houses(sd.getJulDay(), 0, latitude, longitude, hsy, cusp, ascmc);
		if (result == -1)
			throw new SwissephException(sd.getJulDay(), "Calculation was not possible due to nearness to the polar circle in Koch or Placidus house system or when requesting Gauquelin sectors. Calculation automatically switched to Porphyry house calculation method in this case");
		else
			return new HousesInfo(cusp, ascmc);
	}

	public double calcFort(HousesInfo hi, PlanetInfo sun, PlanetInfo moon)
	{
		double k = sun.getLongitude() - hi.getAscendant();
		if (k < 0.0D)
			k += 360D;
		double f;
		if (k > 180D)
			f = (hi.getAscendant() - sun.getLongitude()) + moon.getLongitude();
		else
			f = (hi.getAscendant() + sun.getLongitude()) - moon.getLongitude();
		return f;
	}

	public static String getPlanetName(int planet)
	{
		return sw.swe_get_planet_name(planet);
	}

}
