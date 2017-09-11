package astro.api.bean;

import java.io.Serializable;

/**
 * 宫位[1-12]， 每个相位角度为30°
 * @author stackzhang
 *
 */
public class HouseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int index;//1-12
	public String symbol;
	public String color;
	public double angle;
	public HouseBean(int index, String symbol, String color, double angle) {
		this.index = index;
		this.symbol = symbol;
		this.color = color;
		this.angle = angle;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
