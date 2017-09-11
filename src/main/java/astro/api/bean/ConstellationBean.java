package astro.api.bean;

import java.io.Serializable;

/**
 * 星座 见/assets/files/sign.json
 * @author  stackzhang
 *
 */
public class ConstellationBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String symbol;
	public String enName;
	public String enShortName;
	public String chName;
	public String color;
	public double angle;
	public ConstellationBean(String symbol, String enName, String enShortName, String chName, String color, double angle) {
		this.symbol = symbol;
		this.enName = enName;
		this.enShortName = enShortName;
		this.chName = chName;
		this.color = color;
		this.angle = angle;
	}
	
}
