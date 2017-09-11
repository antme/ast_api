// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AstrologyFonts.java

package com.astrology.util;

import java.awt.Font;
import java.io.PrintStream;
import java.util.Hashtable;

public class AstrologyFonts
{

	private String names[] = {
		"gezodiac.ttf", "syastro.ttf"
	};
	private static Hashtable cache;

	public AstrologyFonts()
	{
		cache = new Hashtable(names.length);
		for (int i = 0; i < names.length; i++)
			cache.put(names[i], getFont(names[i]));

	}

	public static Font getFont(String name)
	{
		Font font = null;
		if (cache != null && (font = (Font)cache.get(name)) != null)
			return font;
		String fName = (new StringBuilder("/fonts/")).append(name).toString();
		try
		{
			java.io.InputStream is = AstrologyFonts.class.getResourceAsStream(fName);
			font = Font.createFont(0, is);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.err.println((new StringBuilder(String.valueOf(fName))).append(" not loaded.  Using serif font.").toString());
			font = new Font("serif", 0, 24);
		}
		return font;
	}
}
