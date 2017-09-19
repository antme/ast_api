// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ImageRender.java

package com.astrology;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.astrology.util.DegreeUtil;

import astro.api.ConfigBean;

// Referenced classes of package com.astrology:
//			DefaultRender, ChartModel, Config, HousesInfo, 
//			PlanetInfo, Constellation

public class ImageRender extends DefaultRender
{

	public ImageRender()
	{
		this("/magic.properties");
	}

	public ImageRender(String config)
	{
		super(config);
	}

	protected void drawPlanets(ChartModel model)
	{
		HousesInfo hi = model.getHousesInfo();
		PlanetInfo planets[] = model.getPlanets();
		float r5 = getConfig().getFloat("r5", 0.5F);
		if ((double)r5 < 1.0D)
			r5 = cx * r5;
		float r6 = getConfig().getFloat("r6", 0.45F);
		if ((double)r6 < 1.0D)
			r6 = cx * r6;
		Font font = getConfig().getFont("star");
		Color basecolor = getConfig().getColor("base");
		Arrays.sort(planets);
		int count = planets.length;
		double asc = hi.getAscendant();
		for (int i = 0; i < count; i++)
		{
			double angle = planets[i].getTransferedLongitude(asc);
			double a2 = angle;
			PlanetInfo p0;
			PlanetInfo p2;
			if (i == 0)
			{
				p0 = planets[count - 1];
				p2 = planets[i + 1];
			} else
			if (i == count - 1)
			{
				p0 = planets[i - 1];
				p2 = planets[0];
			} else
			{
				p0 = planets[i - 1];
				p2 = planets[i + 1];
			}
			if (Math.abs(planets[i].compareTo(p0)) < 10)
				a2 += 5D;
			if (Math.abs(planets[i].compareTo(p2)) < 10)
				a2 -= 5D;
			String planetName = planets[i].getPlanetName();
			
			
			if(!isPlanetIgnored(ConfigBean.getCnName(planetName))) {
			Color color = getConfig().getColor(planetName);
			drawCross(2.0F, r6, angle, normalStroke, color);
			drawLine(r5, a2, r6 + 5F, angle, normalStroke, basecolor);
			//BufferedImage image = getConfig().getImage((new StringBuilder("p")).append(planets[i].getId()).toString());
			//if (image == null)
				drawString(ConfigBean.getCnName(planetName).substring(0, 1), r5, a2, color, font);
			//else
				//drawImage(image, r5, a2);
				
			}
		}

	}

	public void render(ChartModel model, Graphics g, float width, float height)
	{
		this.g = (Graphics2D)g;
		this.height = getConfig().getFloat("height", width);
		this.width = getConfig().getFloat("width", height);
		cy = this.height / 2.0F;
		cx = this.width / 2.0F;
		ratio = this.height / this.width;
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		this.height = height;
		this.width = width;
		drawBackground();
		this.height = getConfig().getFloat("height", width);
		this.width = getConfig().getFloat("width", height);
		drawConstellations(model);
		drawPlanets(model);
		drawRelations(model);
	}

	protected void drawConstellations(ChartModel model)
	{
		HousesInfo hi = model.getHousesInfo();
		Config cfg = getConfig();
		float r1 = cfg.getFloat("r1", 0.95F);
		if ((double)r1 < 1.0D)
			r1 = cx * r1;
		float r2 = cfg.getFloat("r2", 0.8F);
		if ((double)r2 < 1.0D)
			r2 = cx * r2;
		float r3 = cfg.getFloat("r3", 0.75F);
		if ((double)r3 < 1.0D)
			r3 = cx * r3;
		float r4 = cfg.getFloat("r4", 0.65F);
		if ((double)r4 < 1.0D)
			r4 = cx * r4;
		
		r3 = 242;
		r4 = 222;
		Color basecolor = cfg.getColor("base");
		Color weakcolor = cfg.getColor("weak");
		double asc = hi.getAscendant();
		Font basefont = cfg.getFont("base");
		Image img = getConfig().getImage("houseimage");
		double a0 = DegreeUtil.d2R(DegreeUtil.transfer(0.0D, asc));
		AffineTransform at = AffineTransform.getRotateInstance(0.0D - a0, cx, cy);
		g.transform(at);
		g.setComposite(AlphaComposite.Src);

		g.drawImage(img, 0, 0, (int)width, (int)height, null);
		at = AffineTransform.getRotateInstance(a0, cx, cy);
		g.transform(at);
//		drawOval(r3, normalStroke, basecolor);
//		Line2D line = new java.awt.geom.Line2D.Float(0.0F, cy, width, cy);
//		draw(line, normalStroke, basecolor);
//		double mc = DegreeUtil.transfer(hi.getMc(), asc);
//		drawLine(cx, 0.0F - cx, mc, normalStroke, basecolor);
//		for (int i = 0; i < Constellation.POLLUXS.length; i++)
//		{
//			String name = Constellation.POLLUXS[i];
//			double angle = DegreeUtil.transfer(30 * i, asc);
//			drawLine(r1, r3, angle, normalStroke, basecolor);
//			for (int j = 1; j < 30; j++)
//				if (j % 5 == 0)
//					drawLine(r2, r3, angle + (double)j, normalStroke, basecolor);
//				else
//					drawLine(r2, r3, angle + (double)j, halfStroke, weakcolor);
//
//		}

		HousesInfo thi = hi.transfer();
		for (int i = 1; i < 13; i++)
		{
			double angle = thi.get(i);
			double angle2;
			if (i < 12)
				angle2 = thi.get(i + 1);
			else
				angle2 = thi.get(1);
			double delta = angle2 - angle;
			delta = DegreeUtil.fixAngle(delta);
			drawLine(r3, r4, angle, normalStroke, new Color(220,238,255));
//			if (i % 3 != 1)
//				drawLine(r4, 0.0F, angle, dashStroke, weakcolor);
			drawString(Integer.toString(i), (r3 + r4) / 2.0F, angle + delta / 2D, Color.WHITE, basefont);
			//drawString(Integer.toString(i), (r3 + r4) / 2.0F, angle + delta / 2D, cfg.getColor(Constellation.POLLUXS[i - 1]), basefont);
		}

	}

	public String getName()
	{
		return "ImageRender";
	}
}
