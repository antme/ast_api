// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestCalculator.java

package com.astrology;

import java.util.Calendar;

import com.astrology.Calculator;
import com.astrology.HousesInfo;
import com.astrology.PlanetInfo;
import com.astrology.util.DegreeUtil;

import de.thmac.swisseph.SweDate;
import junit.framework.TestCase;

// Referenced classes of package com.astrology:
//			Calculator, PlanetInfo, HousesInfo

public class TestCalculator extends TestCase
{

	public TestCalculator()
	{
	}

	public void testCalcSweDate()
	{
		Calendar now = Calendar.getInstance();
		Calculator calc = new Calculator();
		SweDate swd = calc.calcSweDate(now.getTime());
		System.out.println(now.getTime());
		System.out.println(swd);
		swd = calc.calcSweDate(2006, 2, 16, 22, 55, 0, "GMT+8");
		System.out.println("2006-2-16 22:55 GMT+8 ");
		System.out.println(swd);
	}

	public void testCalc()
	{
		Calculator calc = new Calculator();
		Calendar now = Calendar.getInstance();
		PlanetInfo pi = calc.calc(calc.calcSweDate(2006, 2, 16, 22, 55, 0, "GMT+8"), 9, 256);
		pi = calc.calc(calc.calcSweDate(now.getTime()), 0, 256);
		System.out.println((new StringBuilder(String.valueOf(pi.getPlanetName()))).append(":").append("\n\tLongitude:          ").append(pi.getLongitude()).append("\n\tLatitude:           ").append(pi.getLatitude()).append("\n\tDistance:           ").append(pi.getDistance()).append(" AU").append("\n\tLongitudinal speed: ").append(pi.getLongitudeSpeed()).append(" degs/day").append("\n\tLatitudinal speed: ").append(pi.getLatitudeSpeed()).append(" degs/day").toString());
	}

	public void testHouses()
	{
		Calculator calc = new Calculator();
		SweDate date = new SweDate();
		HousesInfo hi = calc.houses(date, 30.52D, 114.31D, 80);
		System.out.println(date.toString());
		for (int i = 1; i < 12; i++)
			System.out.println((new StringBuilder(String.valueOf(i))).append(" house:\t").append(hi.getAsString(i)).toString());

		System.out.println((new StringBuilder("ASC:\t")).append(DegreeUtil.format(hi.getAscendant())).toString());
		System.out.println((new StringBuilder("MC:\t")).append(DegreeUtil.format(hi.getMc())).toString());
		System.out.println((new StringBuilder("Coasc1:\t")).append(DegreeUtil.format(hi.getCoasc1())).toString());
		System.out.println((new StringBuilder("Coasc2:\t")).append(DegreeUtil.format(hi.getCoasc2())).toString());
		System.out.println((new StringBuilder("Armc:\t")).append(DegreeUtil.format(hi.getArmc())).toString());
		System.out.println((new StringBuilder("EqAsc:\t")).append(DegreeUtil.format(hi.getEquatorialAscendant())).toString());
		System.out.println((new StringBuilder("PolarAsc:\t")).append(DegreeUtil.format(hi.getPolarAscendant())).toString());
		System.out.println((new StringBuilder("Vertex:\t")).append(DegreeUtil.format(hi.getVertex())).toString());
		System.out.println((new StringBuilder("Extra 8:\t")).append(DegreeUtil.format(hi.getExtra(8))).toString());
		System.out.println((new StringBuilder("Extra 9:\t")).append(DegreeUtil.format(hi.getExtra(9))).toString());
		date = calc.calcSweDate(2006, 2, 19, 0, 0, 0, "GMT+8");
		hi = calc.houses(date, 30.52D, 114.31D, 80);
		System.out.println(date.toString());
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
		System.out.println((new StringBuilder("Extra 8:\t")).append(DegreeUtil.format(hi.getExtra(8))).toString());
		System.out.println((new StringBuilder("Extra 9:\t")).append(DegreeUtil.format(hi.getExtra(9))).toString());
	}

	public void testPlanets()
	{
		Calculator calc = new Calculator();
		SweDate date = calc.calcSweDate(2006, 2, 19, 0, 0, 0, "GMT+8");
		System.out.println(date.toString());
		for (int i = 1; i < 21; i++)
			try
			{
				PlanetInfo pi = calc.calc(date, i);
				System.out.println(pi.toString());
			}
			catch (Exception e)
			{
				System.out.println(e);
			}

		for (int i = 0; i < 21; i++)
			try
			{
				PlanetInfo pi = calc.calc(date, i + 10000);
				System.out.println(pi.toString());
			}
			catch (Exception e)
			{
				System.out.println(e);
			}

	}

	public void testCalcFort()
	{
		Calculator calc = new Calculator();
		String forts[] = {
			"15Pis43", "27Pis54", "10Ari11", "22Ari49", "6Tau13", "20Tau52", "7Gem19", "25Gem59", "23Sco14", "15Sag38", 
			"7Cap07", "26Cap32", "13Aqu46", "29Aqu13", "13Pis28", "27Pis00", "10Ari12", "23Ari20", "6Tau33", "2Cap59", 
			"15Cap31", "28Cap05", "10Aqu37", "23Aqu00"
		};
		for (int i = 0; i < 24; i++)
		{
			SweDate date = calc.calcSweDate(2006, 2, 19, i, 0, 0, "GMT+8");
			HousesInfo hi = calc.houses(date, 30.52D, 114.31D, 80);
			PlanetInfo sun = calc.calc(date, 0);
			PlanetInfo moon = calc.calc(date, 1);
			double fort = calc.calcFort(hi, sun, moon);
			assertEquals(forts[i], DegreeUtil.format(fort));
		}

	}

	public void testFort()
	{
		Calculator calc = new Calculator();
		System.out.println("No.\tASC-SUN\tASC-MON\tnightd\tnightc\tdayd\tdayc");
		for (int i = 0; i < 24; i++)
		{
			SweDate date = calc.calcSweDate(2006, 2, 19, i, 0, 0, "GMT+8");
			HousesInfo hi = calc.houses(date, 30.52D, 114.31D, 80);
			PlanetInfo sun = calc.calc(date, 0);
			PlanetInfo moon = calc.calc(date, 1);
			PlanetInfo fortuna = calc.calc(date, 10019);
			double k = hi.getAscendant() - sun.getLongitude();
			System.out.print((new StringBuilder(String.valueOf(i))).append("\t").toString());
			System.out.print(DegreeUtil.format(k, "+h.m"));
			k = hi.getAscendant() - moon.getLongitude();
			System.out.print((new StringBuilder("\t")).append(DegreeUtil.format(k, "+h.m")).toString());
			k = (hi.getAscendant() + sun.getLongitude()) - moon.getLongitude();
			System.out.print((new StringBuilder("\t")).append(DegreeUtil.format(k, "+h.m")).toString());
			String s = DegreeUtil.format(k);
			System.out.print((new StringBuilder("\t")).append(s).toString());
			k = (hi.getAscendant() - sun.getLongitude()) + moon.getLongitude();
			System.out.print((new StringBuilder("\t")).append(DegreeUtil.format(k, "+h.m")).toString());
			s = DegreeUtil.format(k);
			System.out.println((new StringBuilder("\t")).append(s).toString());
		}

	}
}
