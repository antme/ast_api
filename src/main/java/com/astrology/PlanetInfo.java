// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PlanetInfo.java

package com.astrology;

import com.astrology.util.DegreeUtil;

// Referenced classes of package com.astrology:
//			Constants, Calculator

public class PlanetInfo
	implements Comparable
{

	private int id;
	private double longitude;
	private double latitude;
	private double distance;
	private double longitudeSpeed;
	private double latitudeSpeed;
	private double distanceSpeed;

	public PlanetInfo()
	{
	}

	public PlanetInfo(int id)
	{
		this.id = id;
	}

	public PlanetInfo(int id, double longitude)
	{
		this.id = id;
		this.longitude = longitude;
	}

	public PlanetInfo(int planetId, double xx[])
	{
		id = planetId;
		longitude = xx[0];
		latitude = xx[1];
		distance = xx[2];
		longitudeSpeed = xx[3];
		latitudeSpeed = xx[4];
		distanceSpeed = xx[5];
	}

	public double getDistance()
	{
		return distance;
	}

	public void setDistance(double distance)
	{
		this.distance = distance;
	}

	public double getDistanceSpeed()
	{
		return distanceSpeed;
	}

	public void setDistanceSpeed(double distanceSpeed)
	{
		this.distanceSpeed = distanceSpeed;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLatitudeSpeed()
	{
		return latitudeSpeed;
	}

	public void setLatitudeSpeed(double latitudeSpeed)
	{
		this.latitudeSpeed = latitudeSpeed;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public double getLongitudeSpeed()
	{
		return longitudeSpeed;
	}

	public void setLongitudeSpeed(double longitudeSpeed)
	{
		this.longitudeSpeed = longitudeSpeed;
	}

	public int getId()
	{
		return id;
	}

	public String getPlanetName()
	{
		return getPlanetName(id);
	}

	public String getPosition()
	{
		return DegreeUtil.format(getLongitude());
	}

	public String toString()
	{
		String s = (new StringBuilder(String.valueOf(getPlanetName()))).append("\t").append(DegreeUtil.format(getLongitude())).toString();
		if (getLongitudeSpeed() < 0.0D)
			s = (new StringBuilder(String.valueOf(s))).append("R").toString();
		s = (new StringBuilder(String.valueOf(s))).append("\t").append(DegreeUtil.format(getLatitude(), "+h\260m")).toString();
		return s;
	}

	public double getTransferedLongitude(double asc)
	{
		return DegreeUtil.transfer(longitude, asc);
	}

	public static String getPlanetName(int id)
	{
		if (id >= 0 && id < 21)
			return Constants.planet_names[id];
		if (id < 0)
			return Constants.vp_names[id - -100];
		else
			return Calculator.getPlanetName(id);
	}

	public int compareTo(Object o)
	{
		double longi = ((PlanetInfo)o).getLongitude();
		double d = DegreeUtil.fixAngle(getLongitude()) - DegreeUtil.fixAngle(longi);
		if (d > 0.0D && d < 1.0D)
			return 1;
		if (d < 0.0D && d > -1D)
			return -1;
		else
			return (int)d;
	}
}
