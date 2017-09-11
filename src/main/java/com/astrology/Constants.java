// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Constants.java

package com.astrology;


public class Constants
{

	public static final String BASE_PACKAGE = "com.astrology";
	public static final String VERSION_INFO = "0.1 alpha";
	public static final int VP_OFFSET = -100;
	public static final int VP_ASC = -99;
	public static final int VP_2_CUSP = -98;
	public static final int VP_3_CUSP = -97;
	public static final int VP_IC = -96;
	public static final int VP_5_CUSP = -95;
	public static final int VP_6_CUSP = -94;
	public static final int VP_DES = -93;
	public static final int VP_8_CUSP = -92;
	public static final int VP_9_CUSP = -91;
	public static final int VP_MC = -90;
	public static final int VP_11_CUSP = -89;
	public static final int VP_12_CUSP = -88;
	public static final int VP_FORT = -86;
	public static final int VP_VERT = -85;
	public static final int VP_EAST = -84;
	public static final int VP_SONO = -83;
	public static final int VP_NONO = -82;
	public static final int MINOR_OBJECTS[] = {
		15, 16, 17, 18, 19, 20, 12, -86, -85, -84, 
		-83, -82
	};
	public static int PLANETS[] = {
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	};
	static final String vp_names[] = {
		"Offset", "Asc", "2nd", "3rd", "IC", "5th", "6th", "Desc", "8th", "9th", 
		"MC", "11th", "12th", "", "Fort", "Vert", "East", "SoNo", "NoNo"
	};
	static final String planet_names[] = {
		"Sun", "Moon", "Merc", "Venu", "Mars", "Jupi", "Satu", "Uran", "Nept", "Plut", 
		"mNde", "tNde", "Lili", "oApo", "Eart", "Chir", "Phol", "Cere", "Pall", "Juno", 
		"Vest"
	};

	private Constants()
	{
	}

}
