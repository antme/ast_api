package astro.api.bean;

import java.io.Serializable;

public class AstrologyCombination implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanetBean planet;

	private ConstellationBean constellation;

	private HouseBean house;

	public PlanetBean getPlanet() {
		return planet;
	}

	public void setPlanet(PlanetBean planet) {
		this.planet = planet;
	}

	public ConstellationBean getConstellation() {
		return constellation;
	}

	public void setConstellation(ConstellationBean constellation) {
		this.constellation = constellation;
	}

	public HouseBean getHouse() {
		return house;
	}

	public void setHouse(HouseBean house) {
		this.house = house;
	}

}
