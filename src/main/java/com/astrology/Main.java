// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Main.java

package com.astrology;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.astrology.util.CommonUtil;
import com.astrology.util.DegreeUtil;

import de.thmac.swisseph.SweDate;

// Referenced classes of package com.astrology:
//			Calculator, PlanetInfo, HousesInfo

public class Main
{

	public Main()
	{
	}

	public static void main(String args[])
	{
		Properties argmap = processArgs(args);
		if (argmap.getProperty("h") != null || argmap.getProperty("help") != null)
		{
			usage();
			System.exit(0);
		}
		try
		{
			String time = argmap.getProperty("t");
			String tz = argmap.getProperty("z");
			String longi = argmap.getProperty("long");
			String lat = argmap.getProperty("lat");
			Date d;
			if (time != null)
				d = CommonUtil.parseDate(time, tz);
			else
				d = new Date();
			System.out.println(d);
			Calculator calc = new Calculator();
			SweDate sd = calc.calcSweDate(d);
			System.out.println(sd);
			if (longi != null && lat != null)
				System.out.println((new StringBuilder("longitude ")).append(DegreeUtil.format(Double.parseDouble(longi), "+h\260m")).append(" latitude ").append(DegreeUtil.format(Double.parseDouble(lat), "+h\260m")).toString());
			PlanetInfo sun = calc.calc(sd, 0);
			System.out.println(sun);
			PlanetInfo moon = calc.calc(sd, 1);
			System.out.println(moon);
			for (int i = 2; i < 21; i++)
			{
				PlanetInfo pi = calc.calc(sd, i);
				System.out.println(pi.toString());
			}

			if (longi != null && lat != null)
			{
				String hys = argmap.getProperty("hys");
				if (hys == null)
					hys = "P";
				HousesInfo hi = calc.houses(sd, Double.parseDouble(lat), Double.parseDouble(longi), hys.toUpperCase().charAt(0));
				double fort = calc.calcFort(hi, sun, moon);
				for (int i = 1; i < 13; i++)
					System.out.println((new StringBuilder(String.valueOf(i))).append(" house:\t").append(hi.getAsString(i)).toString());

				System.out.println((new StringBuilder("ASC:\t")).append(DegreeUtil.format(hi.getAscendant())).toString());
				System.out.println((new StringBuilder("MC:\t")).append(DegreeUtil.format(hi.getMc())).toString());
				System.out.println((new StringBuilder("Coasc1:\t")).append(DegreeUtil.format(hi.getCoasc1())).toString());
				System.out.println((new StringBuilder("Coasc2:\t")).append(DegreeUtil.format(hi.getCoasc2())).toString());
				System.out.println((new StringBuilder("Armc:\t")).append(DegreeUtil.format(hi.getArmc())).toString());
				System.out.println((new StringBuilder("EqAsc:\t")).append(DegreeUtil.format(hi.getEquatorialAscendant())).toString());
				System.out.println((new StringBuilder("PolarAsc:\t")).append(DegreeUtil.format(hi.getPolarAscendant())).toString());
				System.out.println((new StringBuilder("Vertex:\t")).append(DegreeUtil.format(hi.getVertex())).toString());
				System.out.println((new StringBuilder("Fort:\t")).append(DegreeUtil.format(fort)).toString());
			}
		}
		catch (IllegalArgumentException e)
		{
			System.err.println("Date formate error,required format yyyy-MM-dd h:m:s ");
		}
	}

	public static void usage()
	{
		System.out.println((new StringBuilder("java ")).append(Main.class.getName()).append(" [-t=time] [-z=timezone] [-f=format] [-long=longitude] [-lat=latitude] [-hys=house systems]").toString());
		System.out.println("time format yyyy-M-d h:m:s");
	}

	public static Properties processArgs(String args[])
	{
		Properties argmap = new Properties();
		int j = 0;
		for (int i = 0; i < args.length; i++)
			if (args[i].startsWith("-"))
			{
				int index = args[i].indexOf("=");
				if (index > 0)
				{
					if (index < args[i].length() - 1)
						argmap.setProperty(args[i].substring(1, index), args[i].substring(index + 1));
					else
						argmap.setProperty(args[i].substring(1, index), "true");
				} else
				if (i < args.length - 1 && !args[i + 1].startsWith("-"))
					argmap.setProperty(args[i].substring(1), args[++i]);
				else
					argmap.setProperty(args[i].substring(1), "true");
			} else
			{
				argmap.put((new StringBuilder("PLAIN_ARG_")).append(j).toString(), args[i]);
				j++;
			}

		String propfile = argmap.getProperty("properties");
		if (propfile != null)
			try
			{
				InputStream propin;
				try
				{
					propin = new FileInputStream(propfile);
				}
				catch (FileNotFoundException fnfe)
				{
					propin = Main.class.getResourceAsStream(propfile);
				}
				if (propin != null)
					argmap.load(propin);
			}
			catch (IOException e)
			{
				System.err.println((new StringBuilder("Exception while loading properties from file ")).append(propfile).toString());
			}
		return argmap;
	}
}
