// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Test.java

package com.astrology;

import java.io.PrintStream;
import junit.textui.TestRunner;

public class Test
{

	public Test()
	{
	}

	public static void main(String args[])
	{
		if (args.length == 0)
		{
			for (int i = 0; i < 256; i++)
				System.out.println((new StringBuilder(String.valueOf(i))).append("\t").append((char)i).toString());

		} else
		{
			TestRunner.main(args);
		}
	}

	public static void usage()
	{
		System.out.println((new StringBuilder("java ")).append(Test.class.getName()).toString());
	}
}
