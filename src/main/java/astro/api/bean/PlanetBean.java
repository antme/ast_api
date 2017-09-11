package astro.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 行星 /assets/files/planet.json
 * @author stackzhang
 *
 */
public class PlanetBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public String symbol;
	public String enName;
	public String enSimpleName;
	public String chName;
	public String color;
	public double angle;
	public List<Aspect> aspects;// 相位
	
	public PlanetBean(int id, String symbol, String enName, String enSimpleName, String chName, String color) {
		this.id = id;
		this.symbol = symbol;
		this.enName = enName;
		this.enSimpleName = enSimpleName;
		this.chName = chName;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEnSimpleName() {
		return enSimpleName;
	}

	public void setEnSimpleName(String enSimpleName) {
		this.enSimpleName = enSimpleName;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
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

	public List<Aspect> getAspects() {
		return aspects;
	}

	public void setAspects(List<Aspect> aspects) {
		this.aspects = aspects;
	}
	
	

}
