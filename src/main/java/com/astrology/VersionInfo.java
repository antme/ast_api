// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   VersionInfo.java

package com.astrology;


public class VersionInfo
{

	private String codename;
	private String version;
	private String release;
	private String build;

	public VersionInfo(String codename, String version, String release, String build)
	{
		this.codename = codename;
		this.version = version;
		this.release = release;
		this.build = build;
	}

	public String getCodename()
	{
		return codename;
	}

	public String getVersion()
	{
		return version;
	}

	public String getRelease()
	{
		return release;
	}

	public String getBuild()
	{
		return build;
	}

	public String toString()
	{
		return (new StringBuilder(String.valueOf(version))).append(" ").append(release).append(" (Build ").append(build).append(")").toString();
	}
}
