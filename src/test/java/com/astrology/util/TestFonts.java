// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestFonts.java

package com.astrology.util;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.astrology.util.AstrologyFonts;

// Referenced classes of package com.astrology.util:
//			AstrologyFonts

public class TestFonts
{

	public TestFonts()
	{
	}

	public static void main(String args[])
	{
		new AstrologyFonts();
		(new TestFonts()).testZodiacFont();
	}

	public void testZodiacFont()
	{
		JFrame frame = new JFrame("Font test");
		JTextArea ta = new JTextArea(5, 8);
		ta.setFont(AstrologyFonts.getFont("gezodiac.ttf").deriveFont(36F));
		ta.setText("abcdefg\nhijklnm\nopqrst\nuvwxyz");
		frame.getContentPane().add(ta, "North");
		JTextArea tb = new JTextArea(5, 8);
		tb.setFont(AstrologyFonts.getFont("syastro.ttf").deriveFont(36F));
		tb.setText("abcdefg\nhijklnm\nopqrst\nuvwxyz".toUpperCase());
		frame.getContentPane().add(tb, "South");
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(2);
	}
}
