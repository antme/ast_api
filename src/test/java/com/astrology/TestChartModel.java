// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestChartModel.java

package com.astrology;

import java.text.SimpleDateFormat;

import com.astrology.ChartModel;
import com.astrology.Config;
import com.astrology.StringRender;

import junit.framework.TestCase;

// Referenced classes of package com.astrology:
//			ChartModel, Config, StringRender

public class TestChartModel extends TestCase
{

	public TestChartModel()
	{
	}

	protected void setUp()
		throws Exception
	{
		super.setUp();
	}

//	public void testChartModel()
//	{
//		ChartModel model = new ChartModel(new Date(), 39.920000000000002D, 116.45999999999999D);
//		Assert.assertFalse(model.isCflag());
//		double jd = model.getJulianDay();
//		Assert.assertTrue(model.isCflag());
//		model.setDate(new Date());
//		Assert.assertFalse(model.isCflag());
//		HousesInfo hi = model.getHousesInfo();
//		Assert.assertTrue(model.isCflag());
//	}

//	public void testVelocity()
//		throws Exception
//	{
//		Config.initVelocity();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		ChartModel model = new ChartModel(formatter.parse("2003-09-11 11:00:00"), 39.920000000000002D, 116.45999999999999D);
//		model.setName("����");
//		String positions[] = model.getHousesPosition();
//		VelocityContext context = new VelocityContext();
//		context.put("julianDay", Double.valueOf(model.getJulianDay()));
//		context.put("latitude", Double.valueOf(model.getLatitude()));
//		context.put("longitude", Double.valueOf(model.getLongitude()));
//		context.put("houses", model.getHousesPosition());
//		context.put("planets", model.getPlanets());
//		try
//		{
//			Template template = Velocity.getTemplate("astrology.xml.vm");
//			Writer writer = new PrintWriter(System.out);
//			template.merge(context, writer);
//			writer.flush();
//		}
//		catch (Exception e)
//		{
//			Assert.fail(e.toString());
//		}
//	}

	public void testInfo()
		throws Exception
	{
		Config.initVelocity();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ChartModel model = new ChartModel(formatter.parse("2003-09-11 11:00:00"), 39.55, 116.28);
		model.setName("dylan");
		StringRender render = new StringRender();
		System.out.println(render.render(model, null));
	}
}
