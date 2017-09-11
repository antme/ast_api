// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HousesInfo.java

package com.astrology;

import com.astrology.util.DegreeUtil;

public class HousesInfo
{

	private double houses[];
	private double otherPoint[];
	private double ascendant;
	private double mc;
	private double armc;
	private double vertex;
	private double equatorialAscendant;
	private double coasc1;
	private double coasc2;
	private double polarAscendant;

	public HousesInfo(double cusp[], double ascmc[])
	{
		houses = cusp;
		otherPoint = ascmc;
		ascendant = ascmc[0];
		mc = ascmc[1];
		armc = ascmc[2];
		vertex = ascmc[3];
		equatorialAscendant = ascmc[4];
		coasc1 = ascmc[5];
		coasc2 = ascmc[6];
		polarAscendant = ascmc[7];
	}

	public double get(int houseIndex)
	{
		return houses[houseIndex];
	}

	public String getAsString(int houseIndex)
	{
		double cusp = houses[houseIndex];
		return DegreeUtil.format(cusp);
	}

	public double getExtra(int index)
	{
		return otherPoint[index];
	}

	public double getArmc()
	{
		return armc;
	}

	public double getAscendant()
	{
		return ascendant;
	}

	public double getCoasc1()
	{
		return coasc1;
	}

	public double getCoasc2()
	{
		return coasc2;
	}

	public double getEquatorialAscendant()
	{
		return equatorialAscendant;
	}

	public double getMc()
	{
		return mc;
	}

	public double getPolarAscendant()
	{
		return polarAscendant;
	}

	public double getVertex()
	{
		return vertex;
	}

	public HousesInfo transfer()
	{
		double cusp[] = new double[13];
		for (int i = 0; i < cusp.length; i++)
			cusp[i] = DegreeUtil.transfer(houses[i], ascendant);

		double ascmc[] = new double[10];
		for (int i = 0; i < ascmc.length; i++)
			ascmc[i] = DegreeUtil.transfer(otherPoint[i], ascendant);

		return new HousesInfo(cusp, ascmc);
	}
}
