package astro.api.bean;

import java.awt.Color;

/**
 * 相位 - 5种
 * @author stackzhang
 *
 */
public class Aspect {
	public AspectType type;
	public boolean isDraw;
	public int deltaDegree;
	public int deltaMinute;
	
	public PlanetBean planet;


	public Aspect(AspectType type, PlanetBean planet, boolean isDraw){
		this.type = type;
		this.isDraw = isDraw;
		this.planet = planet;
	}
	public enum AspectType {
		Conjunction(0, 5, "合", new Color(255, 255, 255)), 
		Sextile(60, 5, "六合", new Color(192, 192, 192)),
		Square(90, 5, "刑",  new Color(255, 0, 0)), 
		Trine(120, 5, "拱", new Color(255, 200, 0)), 
		Opposition(180, 5, "冲", new Color(0, 255, 0)), ;
		private int value;
		private float delta;
		private String name;
		private Color color;

		private AspectType(int value, float delta, String name, Color color) {
			this.value = value;
			this.delta = delta;
			this.name = name;
			this.color = color;
		}

		public static AspectType getType(double angle) {
			for (AspectType type : AspectType.values()) {
				if (type.value - type.delta <= angle && angle <= type.value + type.delta) {
					return type;
				}
			}
			return null;
		}

		public int getValue(){
			return value;
		}
		public Color getColor() {
			return color;
		}

		public String getName() {
			return name;
		}

	}
	public AspectType getType() {
		return type;
	}
	public void setType(AspectType type) {
		this.type = type;
	}

	public boolean isDraw() {
		return isDraw;
	}
	public void setDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}
	public int getDeltaDegree() {
		return deltaDegree;
	}
	public void setDeltaDegree(int deltaDegree) {
		this.deltaDegree = deltaDegree;
	}
	public int getDeltaMinute() {
		return deltaMinute;
	}
	public void setDeltaMinute(int deltaMinute) {
		this.deltaMinute = deltaMinute;
	}
	
	
}
