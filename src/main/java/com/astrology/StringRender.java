// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   StringRender.java

package com.astrology;

import java.io.StringWriter;
import java.util.Calendar;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.astrology.util.ChineseCalendarGB;
import com.astrology.util.DegreeUtil;

import net.sf.anole.Messager;
import net.sf.anole.MessagerFactory;

// Referenced classes of package com.astrology:
//			ChartRender, ChartModel, HousesInfo, PlanetInfo

public class StringRender implements ChartRender {

	private static Messager messager = MessagerFactory.getMessager("com.astrology");
	private String info;

	public StringRender() {
	}

	public Object render(ChartModel model, String fileName) {
		VelocityContext context;
		PlanetInfo pi[];
		String planets[];
		context = new VelocityContext();
		context.put("model", model);
		Calendar calendar = Calendar.getInstance(model.getTimezone());
		calendar.setTime(model.getDate());
		// context.put("chineseDate", ChineseCalendarGB.computeChinese(calendar.get(1),
		// calendar.get(2) + 1, calendar.get(5)).getChineseDateString());
		String pos[] = new String[12];
		HousesInfo h = model.getHousesInfo();
		for (int i1 = 0; i1 < 12; i1++) {
			pos[i1] = DegreeUtil.format(h.get(i1 + 1), "P h\260m��");
		}

		context.put("houses", pos);
		context.put("longitude", DegreeUtil.format(model.getLongitude(), "W h\260m��"));
		context.put("latitude", DegreeUtil.format(model.getLatitude(), "N h\260m��"));
		pi = model.getPlanets();
		planets = new String[pi.length];
		for (int i = 0; i < pi.length; i++) {
			PlanetInfo info = pi[i];
			planets[i] = (info.getPlanetName() + "\t" + DegreeUtil.format(info.getLongitude(), "P h°m′"));
			if (info.getLongitudeSpeed() < 0.0D) {
				int tmp243_241 = i;
				String[] tmp243_239 = planets;
				tmp243_239[tmp243_241] = (tmp243_239[tmp243_241] + messager.getMessage("regressive"));
			}
		}
		context.put("planets", planets);
		context.put("ascsign", DegreeUtil.format(model.getHousesInfo().getAscendant(), "P"));
		try {
			Template template = Velocity.getTemplate(messager.getMessage("info.vm"));
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			writer.flush();
			this.info = writer.toString();
		} catch (Exception e) {
			this.info = e.getMessage();
		}
		return this.info;
	}

	public String toString() {
		return info;
	}

	public String getName() {
		return "StringRender";
	}

}
