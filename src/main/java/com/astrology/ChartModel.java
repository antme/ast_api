// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ChartModel.java

package com.astrology;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.astrology.util.CommonUtil;
import com.astrology.util.DegreeUtil;
import com.astrology.util.Location;

import de.thmac.swisseph.SweDate;

// Referenced classes of package com.astrology:
//			Calculator, ChartOptions, HousesInfo, PlanetInfo

public class ChartModel
{

	private Date date;
	private double julianDay;
	private boolean cflag;
	private HousesInfo housesInfo;
	private PlanetInfo planets[];
	private PlanetInfo sun;
	private PlanetInfo moon;
	private Location location;
	private PlanetInfo nono;
	private PlanetInfo sono;
	private String name;
	private TimeZone timezone;
	private ChartOptions options;
	Calculator calc;

	public ChartModel()
	{
		cflag = false;
		calc = new Calculator();
		options = ChartOptions.defaultOptions;
		timezone = TimeZone.getDefault();
		name ="com.astrology";
	}

	public ChartModel(Date date, double latitude, double longitude)
	{
		this();
		this.date = date;
		location = new Location(latitude, longitude, "Earth", "");
	}

	public ChartModel(Date date, String timezone, double latitude, double longitude)
	{
		this(date, latitude, longitude);
		this.timezone = TimeZone.getTimeZone(timezone);
	}

	public ChartModel(String dateStr, String timezone, double latitude, double longitude)
	{
		this(CommonUtil.parseDate(dateStr, timezone), latitude, longitude);
		this.timezone = TimeZone.getTimeZone(timezone);
	}

	public Location getLocation()
	{
		return location;
	}

	public java.awt.Dimension getSize()
	{
		return options.size;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public void setOptions(ChartOptions options)
	{
		this.options = options;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
		cflag = false;
	}

	public String getFormatDate()
	{
		return SimpleDateFormat.getInstance().format(date);
	}

	public String getHouseSystem()
	{
		return options.getHouseSystemName();
	}

	public TimeZone getTimezone()
	{
		return timezone;
	}

	public void setTimezone(TimeZone timezone)
	{
		this.timezone = timezone;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getLatitude()
	{
		return location.getLatitude();
	}

	public double getLongitude()
	{
		return location.getLongitude();
	}

	public HousesInfo getHousesInfo()
	{
		if (!cflag)
			calc();
		return housesInfo;
	}

	public String[] getHousesPosition()
	{
		String pos[] = new String[12];
		getHousesInfo();
		for (int i = 0; i < 12; i++)
			pos[i] = DegreeUtil.format(housesInfo.get(i + 1));

		return pos;
	}

	public PlanetInfo getSouthNode()
	{
		if (!cflag)
			calc();
		return sono;
	}

	public double getJulianDay()
	{
		if (!cflag)
			calc();
		return julianDay;
	}

	public PlanetInfo[] getPlanets()
	{
		if (!cflag)
			calc();
		return planets;
	}

	protected boolean isCflag()
	{
		return cflag;
	}

	public void calc()
	{
		SweDate sd = calc.calcSweDate(date);
		julianDay = sd.getJulDay();
		housesInfo = calc.houses(sd, getLatitude(), getLongitude(), options.getHouseSystem());
		sun = calc.calc(julianDay, 0);
		moon = calc.calc(julianDay, 1);
		nono = calc.calc(julianDay, options.nono_true_mode ? 11 : 10);
		sono = new PlanetInfo(-83, nono.getLongitude() + 180D);
		sono.setDistance(nono.getDistance());
		sono.setLatitude(nono.getLatitude());
		sono.setLatitudeSpeed(nono.getLatitudeSpeed());
		sono.setLongitudeSpeed(nono.getLongitudeSpeed());
		sono.setDistanceSpeed(nono.getDistanceSpeed());
		int pl = options.planets.size();
		int cl = options.cusps.size();
		planets = new PlanetInfo[pl + cl];
		for (int i = 0; i < pl; i++)
			try
			{
				planets[i] = calc(((Integer)options.planets.get(i)).intValue());
			}
			catch (Exception e)
			{
				System.out.println(e);
			}

		for (int i = 0; i < cl; i++)
			try
			{
				planets[i + pl] = calc(((Integer)options.cusps.get(i)).intValue());
			}
			catch (Exception e)
			{
				System.out.println(e);
			}

		cflag = true;
	}

	public ChartOptions.Aspect relation(PlanetInfo p1, PlanetInfo p2)
	{
		double r = Math.abs(DegreeUtil.fixAngle(p1.getLongitude()) - DegreeUtil.fixAngle(p2.getLongitude()));
		if (r > 180D)
			r = 360D - r;
		for (int i = 0; i < options.aspects.size(); i++)
		{
			ChartOptions.Aspect aspect = (ChartOptions.Aspect)options.aspects.get(i);
			double k = Math.abs(aspect.angle - r);
			if (k < aspect.orb)
				return new ChartOptions.Aspect(aspect.angle, aspect.influence, aspect.name, k);
		}

		return null;
	}

	private PlanetInfo calc(int id)
	{
		if (id == 0)
			return sun;
		if (id == 1)
			return moon;
		if (id < 0)
			switch (id)
			{
			case -99: 
				return new PlanetInfo(-99, housesInfo.getAscendant());

			case -93: 
				return new PlanetInfo(-93, housesInfo.getAscendant() + 180D);

			case -90: 
				return new PlanetInfo(-90, housesInfo.getMc());

			case -96: 
				return new PlanetInfo(-96, housesInfo.getMc() + 180D);

			case -86: 
				return new PlanetInfo(-86, calc.calcFort(housesInfo, sun, moon));

			case -85: 
				return new PlanetInfo(-85, housesInfo.getVertex());

			case -84: 
				return new PlanetInfo(-84, housesInfo.getEquatorialAscendant());

			case -83: 
				return sono;

			case -82: 
				return nono;

			case -98: 
			case -97: 
			case -95: 
			case -94: 
			case -92: 
			case -91: 
			case -89: 
			case -88: 
			case -87: 
			default:
				return new PlanetInfo(id, housesInfo.get(100 + id));
			}
		else
			return calc.calc(julianDay, id);
	}
}
