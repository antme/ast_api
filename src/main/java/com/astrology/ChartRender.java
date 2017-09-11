// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ChartRender.java

package com.astrology;


// Referenced classes of package com.astrology:
//			ChartModel

public interface ChartRender
{

	public abstract Object render(ChartModel chartmodel, String fileName);

	public abstract String getName();
}
