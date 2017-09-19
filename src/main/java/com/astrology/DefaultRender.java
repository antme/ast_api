// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DefaultRender.java

package com.astrology;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.astrology.util.DegreeUtil;

import astro.api.ConfigBean;

// Referenced classes of package com.astrology:
//			ChartRender, Config, ChartModel, HousesInfo, 
//			Constellation, PlanetInfo, ChartOptions

public class DefaultRender implements ChartRender {

	public static Stroke normalStroke = new BasicStroke(1.0F);
	public static Stroke halfStroke = new BasicStroke(0.5F);
	public static Stroke boldStroke = new BasicStroke(1.5F);
	public static Stroke dashStroke = new BasicStroke(1.0F, 0, 0, 10F, new float[] { 1.5F }, 0.0F);
	protected Graphics2D g;
	protected float width;
	protected float height;
	protected float cx;
	protected float cy;
	protected float ratio;
	private String config;
	private Config cfg;

	public DefaultRender() {
		this("/jastrology.properties");
	}

	public DefaultRender(String config) {
		this.config = config;
		cfg = Config.getConfig(config);
	}

	public void render(ChartModel model, Graphics g, float width, float height) {
		this.g = (Graphics2D) g;
		this.height = height;
		this.width = width;
		cy = height / 2.0F;
		cx = width / 2.0F;
		ratio = height / width;
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		//drawBackground();
		drawConstellations(model);
		drawPlanets(model);
		drawRelations(model);
	}

	public Config getConfig() {
		return cfg;
	}

	protected void drawBackground() {
		g.setColor(getConfig().getColor("background"));
		BufferedImage bgimg = getConfig().getImage("background.image");
		if (bgimg != null) {
			g.fillRect(0, 0, (int) width, (int) height);
			g.drawImage(bgimg, 0, 0, (int) width, (int) height, null);
		} else {
			BufferedImage texture = getConfig().getImage("background.texture");
			if (texture != null)
				g.setPaint(new TexturePaint(texture, new Rectangle(0, 0, texture.getWidth(), texture.getHeight())));
			g.fillRect(0, 0, (int) width, (int) height);
		}
		// BufferedImage wm = getConfig().getImage("watermark");
		// if (wm != null)
		// {
		// int w = wm.getWidth();
		// int h = wm.getHeight();
		// int x = getConfig().getInt("watermark.x", (int)cx - w / 2);
		// int y = getConfig().getInt("watermark.y", (int)cy - h / 2);
		// g.drawImage(wm, x, y, w, h, null);
		// }
	}

	protected void drawConstellations(ChartModel model) {
		HousesInfo hi = model.getHousesInfo();
		float r1 = cfg.getFloat("r1", 0.95F);
		if ((double) r1 < 1.0D)
			r1 = cx * r1;
		float r2 = cfg.getFloat("r2", 0.8F);
		if ((double) r2 < 1.0D)
			r2 = cx * r2;
		float r3 = cfg.getFloat("r3", 0.75F);
		if ((double) r3 < 1.0D)
			r3 = cx * r3;
		float r4 = cfg.getFloat("r4", 0.65F);
		if ((double) r4 < 1.0D)
			r4 = cx * r4;
		Color basecolor = cfg.getColor("base");
		Color weakcolor = cfg.getColor("weak");
		Font basefont = cfg.getFont("base");
		drawOval(r1, normalStroke, basecolor);
		drawOval(r2, normalStroke, basecolor);
		drawOval(r3, normalStroke, basecolor);
		drawOval(r4, normalStroke, basecolor);
		double asc = hi.getAscendant();
		Line2D line = new java.awt.geom.Line2D.Float(0.0F, cy, width, cy);
		draw(line, normalStroke, basecolor);
		double mc = DegreeUtil.transfer(hi.getMc(), asc);
		drawLine(cx, 0.0F - cx, mc, normalStroke, basecolor);
		for (int i = 0; i < Constellation.POLLUXS.length; i++) {
			String name = Constellation.POLLUXS[i];
			double angle = DegreeUtil.transfer(30 * i, asc);
			drawLine(r1, r3, angle, normalStroke, basecolor);
			drawString(name, (r1 + r2) / 2.0F, angle + 15D, cfg.getColor(name), basefont);
			for (int j = 1; j < 30; j++)
				if (j % 5 == 0)
					drawLine(r2, r3, angle + (double) j, normalStroke, basecolor);
				else
					drawLine(r2, r3, angle + (double) j, halfStroke, weakcolor);

		}

		HousesInfo thi = hi.transfer();
		for (int i = 1; i < 13; i++) {
			double angle = thi.get(i);
			double angle2;
			if (i < 12)
				angle2 = thi.get(i + 1);
			else
				angle2 = thi.get(1);
			double delta = angle2 - angle;
			delta = DegreeUtil.fixAngle(delta);
			drawLine(r3, r4, angle, normalStroke, basecolor);
			if (i % 3 != 1)
				drawLine(r4, 0.0F, angle, dashStroke, weakcolor);
			drawString(Integer.toString(i), (r3 + r4) / 2.0F, angle + delta / 2D,
					cfg.getColor(Constellation.POLLUXS[i - 1]), basefont);
		}

	}

	protected void drawPlanets(ChartModel model) {
		HousesInfo hi = model.getHousesInfo();
		PlanetInfo planets[] = model.getPlanets();
		float r5 = getConfig().getFloat("r5", 0.5F);
		if ((double) r5 < 1.0D)
			r5 = cx * r5;
		float r6 = getConfig().getFloat("r6", 0.45F);
		if ((double) r6 < 1.0D)
			r6 = cx * r6;
		Font font = getConfig().getFont("star");
		Color basecolor = getConfig().getColor("base");
		Arrays.sort(planets);
		int count = planets.length;
		double asc = hi.getAscendant();
		for (int i = 0; i < count; i++) {
			double angle = planets[i].getTransferedLongitude(asc);
			double a2 = angle;
			PlanetInfo p0;
			PlanetInfo p2;
			if (i == 0) {
				p0 = planets[count - 1];
				p2 = planets[i + 1];
			} else if (i == count - 1) {
				p0 = planets[i - 1];
				p2 = planets[0];
			} else {
				p0 = planets[i - 1];
				p2 = planets[i + 1];
			}
			if (Math.abs(planets[i].compareTo(p0)) < 10)
				a2 += 5D;
			if (Math.abs(planets[i].compareTo(p2)) < 10)
				a2 -= 5D;
			Color color = getConfig().getColor(planets[i].getPlanetName());
			drawCross(2.0F, r6, angle, normalStroke, color);
			drawLine(r5, a2, r6 + 5F, angle, normalStroke, basecolor);
			drawString(planets[i].getPlanetName(), r5, a2, color, font);
		}

	}

	protected void drawRelations(ChartModel model) {
		HousesInfo hi = model.getHousesInfo();
		PlanetInfo planets[] = model.getPlanets();
		int count = planets.length;
		float r6 = getConfig().getFloat("r6", 0.45F);
		if ((double) r6 < 1.0D)
			r6 = cx * r6;
		double asc = hi.getAscendant();
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++)
				if ((planets[i].getId() >= 0 || planets[i].getId() == -99 || planets[i].getId() == -90)
						&& (planets[j].getId() >= 0 || planets[j].getId() == -99 || planets[j].getId() == -90)
						&& (planets[i].getId() != -99 || planets[j].getId() != -90)
						&& (planets[j].getId() != -99 || planets[i].getId() != -90)) {
					ChartOptions.Aspect r = model.relation(planets[i], planets[j]);
					if (r != null) {
						Stroke stroke;
						if (r.orb > 1.0D)
							stroke = dashStroke;
						else
							stroke = boldStroke;
						Color color = getConfig().getColor(r.name);
						drawLine(r6, planets[i].getTransferedLongitude(asc), r6, planets[j].getTransferedLongitude(asc),stroke,  new Color(220,238,255));
					}
				}

		}

	}

	protected void drawOval(float r, Stroke stroke, Color color) {
		Shape s = new java.awt.geom.Ellipse2D.Float(cx - r, cy - r * ratio, r * 2.0F, r * ratio * 2.0F);
		draw(s, stroke, color);
	}

	protected void drawOval(float rw, float rh, Stroke stroke, Color color) {
		Shape s = new java.awt.geom.Ellipse2D.Float(cx - rw, cy - rh, rw * 2.0F, rh * 2.0F);
		draw(s, stroke, color);
	}

	protected void drawLine(float r1, float r2, double angle, Stroke stroke, Color color) {
		double da = DegreeUtil.d2R(angle);
		double hor = Math.cos(da);
		double vet = Math.sin(da);
		Shape shape = new java.awt.geom.Line2D.Double((double) cx + (double) r1 * hor,
				(double) cy - (double) (r1 * ratio) * vet, (double) cx + (double) r2 * hor,
				(double) cy - (double) (r2 * ratio) * vet);
		draw(shape, stroke, color);
	}

	protected void drawLine(float r1, double a1, float r2, double a2, Stroke stroke, Color color) {
		double da1 = DegreeUtil.d2R(a1);
		double da2 = DegreeUtil.d2R(a2);
		Shape shape = new java.awt.geom.Line2D.Double((double) cx + (double) r1 * Math.cos(da1),
				(double) cy - (double) (r1 * ratio) * Math.sin(da1), (double) cx + (double) r2 * Math.cos(da2),
				(double) cy - (double) (r2 * ratio) * Math.sin(da2));
		draw(shape, stroke, color);
	}

	protected void drawString(String str, float r, double angle, Color color, Font font) {
		g.setColor(color);
		if (font != null)
			g.setFont(font);
		FontMetrics metrics = g.getFontMetrics();
		int fh = metrics.getHeight();
		int fw = metrics.stringWidth(str);
		double da = DegreeUtil.d2R(angle);
		double hor = Math.cos(da);
		double vet = Math.sin(da);
		float x = (float) (((double) cx + (double) r * hor) - (double) fw / 2D);
		float y = (float) ((double) cy - (double) (r * ratio) * vet);
		if (vet < 0.0D)
			y += (float) fh / 2.0F;
		g.drawString(str, x, y);
	}

	protected void drawImage(Image img, float r, double angle) {
		int fh = img.getHeight(null);
		int fw = img.getWidth(null);
		drawImage(img, r, angle, fw, fh);
	}

	protected void drawImage(Image img, float r, double angle, int width, int height) {
		double da = DegreeUtil.d2R(angle);
		double hor = Math.cos(da);
		double vet = Math.sin(da);
		float x = (float) (((double) cx + (double) r * hor) - (double) width / 2D);
		float y = (float) ((double) cy - (double) (r * ratio) * vet);
		if (vet > 0.0D)
			y -= height;
		g.drawImage(img, (int) x, (int) y, width, height, null);
	}

	protected void drawCross(float size, float r, double angle, Stroke stroke, Color color) {
		g.setColor(color);
		g.setStroke(stroke);
		double da = DegreeUtil.d2R(angle);
		double hor = Math.cos(da);
		double vet = Math.sin(da);
		float x = (float) ((double) cx + (double) r * hor);
		float y = (float) ((double) cy - (double) (r * ratio) * vet);
		Shape line = new java.awt.geom.Line2D.Float(x - size, y, x + size, y);
		g.draw(line);
		line = new java.awt.geom.Line2D.Float(x, y - size, x, y + size);
		g.draw(line);
	}

	protected void draw(Shape shape, Stroke stroke, Color color) {
		g.setStroke(stroke);
		g.setColor(color);
		g.draw(shape);
	}

	public Object render(ChartModel model, String fileName) {
		Dimension size = model.getSize();

		BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, size.width, size.height);


		render(model, g, size.width, size.height);
		g.dispose();
		try {
			ImageIO.write(image, "png", new File(ConfigBean.getProperty("pic_path") + fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	public String getName() {
		return "DefaultRender";
	}

}
