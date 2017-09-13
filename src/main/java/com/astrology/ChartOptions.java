// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ChartOptions.java

package com.astrology;

import java.util.ArrayList;
import java.util.List;

public class ChartOptions
{
	public static class Aspect
	{

		public String name;
		public double angle;
		public double orb;
		public float influence;

		public Aspect()
		{
		}

		public Aspect(double angle, float influence, String name, double orb)
		{
			this.angle = angle;
			this.influence = influence;
			this.name = name;
			this.orb = orb;
		}
	}


	public List planets;
	public List aspects;
	public List cusps;
	public java.awt.Dimension size;
	private int houseSystem;
	public boolean nono_true_mode;
	public static final ChartOptions defaultOptions;
	public static final int default_planet_ids[] = {
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
		-82, 15, 17, 18, 19, 20, 12, -86, -84, -85
	};

	public int getHouseSystem()
	{
		return houseSystem;
	}

	public ChartOptions()
	{
		houseSystem = 80;
		nono_true_mode = false;
		size = new java.awt.Dimension(580, 580);
	}

	public void setHouseSystem(int houseSystem)
	{
		this.houseSystem = houseSystem;
	}

	public String getHouseSystemName()
	{
		return getHouseSystemName(houseSystem);
	}

	public static String getHouseSystemName(int system)
	{
		switch (system)
		{
		case 80: // 'P'
			return "Placidus";

		case 75: // 'K'
			return "Koch";

		case 79: // 'O'
			return "Porphyrius";

		case 82: // 'R'
			return "Regiomontanus";

		case 67: // 'C'
			return "Campanus";

		case 65: // 'A'
			return "equal (cusp 1 is ascendent)";

		case 69: // 'E'
			return "equal (cusp 1 is ascendent)";

		case 86: // 'V'
			return "Vehlow equal (asc. in middle of house 1)";

		case 88: // 'X'
			return "axial rotation system/ Meridian houses";

		case 72: // 'H'
			return "azimuthal or horizontal system";

		case 84: // 'T'
			return "Polich/Page ('topocentric' system)";

		case 66: // 'B'
			return "Alcabitius";

		case 68: // 'D'
		case 70: // 'F'
		case 71: // 'G'
		case 73: // 'I'
		case 74: // 'J'
		case 76: // 'L'
		case 77: // 'M'
		case 78: // 'N'
		case 81: // 'Q'
		case 83: // 'S'
		case 85: // 'U'
		case 87: // 'W'
		default:
			return "unknown";
		}
	}

	public static ChartOptions buildOptions(int planet_ids[])
	{
		ChartOptions options = new ChartOptions();
		options.planets = new ArrayList(17);
		for (int i = 0; i < planet_ids.length; i++)
		{
			int id = planet_ids[i];
			options.planets.add(new Integer(id));
		}

		options.aspects = new ArrayList(5);
		options.aspects.add(new Aspect(0.0D, 1.0F, "Con", 6D));
		options.aspects.add(new Aspect(60D, 1.0F, "Sex", 3D));
		options.aspects.add(new Aspect(90D, 1.0F, "Squ", 5D));
		options.aspects.add(new Aspect(120D, 1.0F, "Tri", 5D));
		options.aspects.add(new Aspect(180D, 1.0F, "Opp", 5D));
		options.cusps = new ArrayList();
		options.cusps.add(new Integer(-99));
		options.cusps.add(new Integer(-93));
		options.cusps.add(new Integer(-90));
		options.cusps.add(new Integer(-96));
		return options;
	}

	static 
	{
		defaultOptions = new ChartOptions();
		defaultOptions.planets = new ArrayList(17);
		for (int i = 0; i < default_planet_ids.length; i++)
		{
			int id = default_planet_ids[i];
			defaultOptions.planets.add(new Integer(id));
		}

		defaultOptions.aspects = new ArrayList(5);
		defaultOptions.aspects.add(new Aspect(0.0D, 1.0F, "Con", 6D));
		defaultOptions.aspects.add(new Aspect(60D, 1.0F, "Sex", 3D));
		defaultOptions.aspects.add(new Aspect(90D, 1.0F, "Squ", 5D));
		defaultOptions.aspects.add(new Aspect(120D, 1.0F, "Tri", 5D));
		defaultOptions.aspects.add(new Aspect(180D, 1.0F, "Opp", 5D));
		defaultOptions.cusps = new ArrayList();
		defaultOptions.cusps.add(new Integer(-99));
		defaultOptions.cusps.add(new Integer(-93));
		defaultOptions.cusps.add(new Integer(-90));
		defaultOptions.cusps.add(new Integer(-96));
	}
}
