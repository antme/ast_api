// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestMessager.java

package com.astrology;


import junit.framework.TestCase;
import net.sf.anole.Messager;
import net.sf.anole.MessagerFactory;

public class TestMessager extends TestCase
{

	public TestMessager()
	{
	}

	public void testMessager()
	{
		Messager messager = MessagerFactory.getMessager("com.astrology");
		String sun = messager.getMessage("Sun");
		assertNotNull(sun);
		System.out.println((new StringBuilder("Sun:\t")).append(sun).toString());
	}
}
