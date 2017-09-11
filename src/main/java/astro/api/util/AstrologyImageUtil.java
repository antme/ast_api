package astro.api.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.gson.reflect.TypeToken;

import astro.api.ConfigBean;
import astro.api.JsonTool;
import astro.api.Util;
import astro.api.bean.Aspect;
import astro.api.bean.ConstellationBean;
import astro.api.bean.HouseBean;
import astro.api.bean.PlanetBean;

public class AstrologyImageUtil {
	private double deltaAngle = 0;
	private double planetRadius;
	private double planetMinRadius;
	private double signXRadius;
	private double signRadius;
	private double houseRadius;
	private Point centerPoint = new Point();
	private static final int DEFAULT_W = 600;
	private static final int DEFAULT_H = 600;

	public static void main(String args[]) {

	}

	public String drawImage(List<HouseBean> houseBeans, List<PlanetBean> planetBeans) throws IOException {
		

		    
		double centerX = DEFAULT_W / 2;
		double centerY = DEFAULT_H / 2;

		deltaAngle = 180 - Util.getAscPlanet(planetBeans).angle;
		signRadius = centerX * 0.95;
		signXRadius = centerX * 0.80;
		houseRadius = centerX * 0.72;
		planetRadius = centerX * 0.55;
		planetMinRadius = centerX * 0.35;
		centerPoint.x = centerPoint.y = (int) (0.5 * DEFAULT_W);

		BufferedImage bImage = new BufferedImage(DEFAULT_W, DEFAULT_H, BufferedImage.TYPE_INT_BGR);
		Graphics2D g2d = bImage.createGraphics();

		bImage = g2d.getDeviceConfiguration().createCompatibleImage(DEFAULT_W, DEFAULT_H, Transparency.TRANSLUCENT);

		g2d = bImage.createGraphics();
		// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);

		String signJson = Util.loadStringFromAssets("sign.json");
		List<ConstellationBean> signList = JsonTool.parseJson(signJson, new TypeToken<List<ConstellationBean>>() {
		}.getType());

		Ellipse2D circle = new Ellipse2D.Double();

		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(new Color(241, 142, 147));
		circle.setFrameFromCenter(centerX, centerY, centerX + planetRadius, centerY + planetRadius);
		g2d.draw(circle);

		circle.setFrameFromCenter(centerX, centerY, centerX + houseRadius, centerY + houseRadius);
		g2d.draw(circle);

		circle.setFrameFromCenter(centerX, centerY, centerX + signXRadius, centerY + signXRadius);
		g2d.draw(circle);

		circle.setFrameFromCenter(centerX, centerY, centerX + signRadius, centerY + signRadius);
		g2d.draw(circle);

		for (ConstellationBean sign : signList) {

			Point pt1 = getPointByAngle(centerPoint, signXRadius, sign.angle);
			Point pt2 = getPointByAngle(centerPoint, signRadius, sign.angle);
			Point centerPt = getPointByAngle(centerPoint, (signXRadius + signRadius) / 2, sign.angle + 15);

			g2d.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);

			drawString(g2d, sign.chName, centerPt.x, centerPt.y);
//			g2d.drawString(sign.symbol, centerPt.x, centerPt.y);

			for (int i = 0; i < 6; i++) {
				double angle = sign.angle + i * 5;
				Point p1 = getPointByAngle(centerPoint, houseRadius, angle);
				Point p2 = getPointByAngle(centerPoint, signXRadius, angle);

				g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
			}

		}

		int i = 0;
		for (HouseBean house : houseBeans) {

			Point pt1 = getPointByAngle(centerPoint, planetRadius, house.angle);
			Point pt2 = getPointByAngle(centerPoint, houseRadius, house.angle);

			g2d.setColor(new Color(241, 142, 147));
			g2d.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);

			// 4条长线
			// if (i % 3 == 0) {
			// g2d.setColor(Color.WHITE);
			// g2d.drawLine(pt1.x, pt1.y, centerPoint.x, centerPoint.y);
			// }

			int g = i + 1;

			Point pt3 = getPointByAngle(centerPoint, planetRadius, house.angle + 15);
			Point pt4 = getPointByAngle(centerPoint, houseRadius, house.angle + 15);
			g2d.setColor(Color.CYAN);
			
			drawString(g2d, g + "", (pt3.x + pt4.x) / 2, (pt3.y + pt4.y) / 2);
			//g2d.drawString(g + "", (pt3.x + pt4.x) / 2, (pt3.y + pt4.y) / 2);

			i++;

		}

		for (PlanetBean planet : planetBeans) {
			Point pt1 = getPointByAngle(centerPoint, planetMinRadius, planet.angle);
			Point pt2 = getPointByAngle(centerPoint, planetMinRadius * 1.1f, planet.angle);
			// g2d.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);
			Color c = null;
			try {
				int r = Integer.parseInt(planet.color.substring(0, 2), 16);
				int g = Integer.parseInt(planet.color.substring(2, 4), 16);
				int b = Integer.parseInt(planet.color.substring(4, 6), 16);
				c = new Color(r, g, b);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (c != null) {
				g2d.setColor(c);
				// g2d.drawLine(pt1.x, pt1.y,pt1.x, pt1.y);

			}
			Point pt3 = getPointByAngle(centerPoint, planetMinRadius * 1.2f, planet.angle);

//			g2d.drawString(planet.symbol, pt3.x, pt3.y);
			drawString(g2d, planet.symbol, pt3.x, pt3.y);

			if (planet.aspects != null) {
				for (Aspect aspect : planet.aspects) {
					if (aspect.isDraw) {
						Point pt4 = getPointByAngle(centerPoint, planetMinRadius, aspect.planet.angle);
						if (aspect.deltaDegree >= 1) {
							float delta = 2 * (aspect.deltaDegree);
						}
						g2d.setColor(aspect.type.getColor());
						g2d.drawLine(pt1.x, pt1.y, pt4.x, pt4.y);
					}
				}
			}
		}

		g2d.dispose();

		String imageFileName = UUID.randomUUID().toString() + ".png";
		new File(ConfigBean.getProperty("pic_path")).mkdirs();
		String imageFilePath = ConfigBean.getProperty("pic_path") + imageFileName;
		System.out.println("create file to " + imageFilePath);
		ImageIO.write(bImage, "PNG", new File(imageFilePath));
		return imageFileName;

	}

	private void drawString(Graphics2D g2d, String symbol, int x, int y) {
		Font font2=new Font("SansSerif",Font.ITALIC,20);
//		Font font2=new Font("DejaVu Serif",Font.ITALIC,20);
		
		g2d.setFont(font2);

		g2d.setStroke(new BasicStroke(5));
		g2d.drawString(symbol, x, y);
		g2d.setStroke(new BasicStroke(1));
	}

	private Point getPointByAngle(Point pt, double r, double angle) {
		angle += deltaAngle;

		double x = pt.x + r * Math.cos(angle * Math.PI / 180);
		double y = pt.y - r * Math.sin(angle * Math.PI / 180);
		return new Point((int) x, (int) y);
	}
}
