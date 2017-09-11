// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Location.java

package com.astrology.util;


// Referenced classes of package com.astrology.util:
//			DegreeUtil

public class Location
{

	private String province;
	private String name;
	private double longitude;
	private double latitude;

	public Location()
	{
	}

	public Location(double latitude, double longitude, String name, String province)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.province = province;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String toString()
	{
		return (new StringBuilder(String.valueOf(province))).append(" ").append(name).append(" ").append(DegreeUtil.format(longitude, "h:m:sw")).append(" ").append(DegreeUtil.format(latitude, "h:m:sn")).toString();
	}
}
