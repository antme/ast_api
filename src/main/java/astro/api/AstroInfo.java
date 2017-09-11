package astro.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import astro.api.bean.Aspect;
import astro.api.bean.Aspect.AspectType;
import astro.api.bean.AstrologyResult;
import astro.api.bean.ConstellationBean;
import astro.api.bean.HouseBean;
import astro.api.bean.PlanetBean;
import astro.api.util.AstrologyImageUtil;
import de.thmac.swisseph.SweConst;
import de.thmac.swisseph.SweDate;
import de.thmac.swisseph.SwissEph;

public class AstroInfo {

	public static void main(String args[]) throws ParseException {
		double longitude;
		double latitude;

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		longitude = 116.28;// 116 ;
		latitude = 39.55;

		String json = new Gson()
				.toJson(new AstroInfo().getastroinfo(f.parse("1998-01-01 00:00:00"), longitude, latitude));
		System.out.println(json);

	}

	public AstrologyResult getastroinfo(Date date, double longitude, double latitude) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		SweDate sd = new SweDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY) - 8);
		SwissEph sw = new SwissEph(ConfigBean.getProperty("ephemeris_path"));

		double time = sd.getJulDay();

		int flags = 0;
		double[] cusps = new double[13];
		double[] acsc = new double[10];

		final StringBuffer sb1 = new StringBuffer();
		sb1.append("lng:" + longitude + ", lat:" + latitude + "\n");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));

		sb1.append(formatter.format(SweDate.getDate(time)) + "," + time);

		System.out.println(sb1.toString());

		int result = sw.swe_houses(sd.getJulDay(), 0, latitude, longitude, 'P', cusps, acsc);

		List<HouseBean> houseBeans = new ArrayList<HouseBean>();

		// 宫位
		// 宫位
		for (int i = 1; i < 13; i++) {
			HouseBean bean = new HouseBean(i, "", "", cusps[i]);
			houseBeans.add(bean);
			System.out.println(i + "->>" + cusps[i]);
		}

		int[] planets = { SweConst.SE_SUN, SweConst.SE_MOON, SweConst.SE_MERCURY, SweConst.SE_VENUS, SweConst.SE_MARS,
				SweConst.SE_JUPITER, SweConst.SE_SATURN, SweConst.SE_URANUS, SweConst.SE_NEPTUNE, SweConst.SE_PLUTO,
				SweConst.SE_JUNO, SweConst.SE_CHIRON, SweConst.SE_MEAN_NODE };

		Type type = new TypeToken<List<PlanetBean>>() {
		}.getType();
		String planetJson = Util.loadStringFromAssets("planet.json");
		final List<PlanetBean> planetList = JsonTool.parseJson(planetJson, type);

		// 过滤
		Util.getAscPlanet(planetList).angle = acsc[0];
		Util.getDesPlanet(planetList).angle = ((acsc[0] + 180 > 360 ? acsc[0] - 180 : acsc[0] + 180));
		Util.getMcPlanet(planetList).angle = acsc[1];
		Util.getIcPlanet(planetList).angle = ((acsc[1] + 180 > 360 ? acsc[1] - 180 : acsc[1] + 180));

		flags = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED;// |
		double[] xp = new double[6];
		for (int p = 0; p < planets.length; p++) {
			int planet = planets[p];
			StringBuffer serr = new StringBuffer();
			int ret = sw.swe_calc_ut(time, planet, flags, xp, serr);
			if (ret != flags) {
			}

			PlanetBean planetBean = Util.getPlanetById(planetList, planet);
			if (planetBean != null) {
				planetBean.angle = xp[0];

				System.out.println(planetBean.enName + "(" + planetBean.chName + ") angle = " + planetBean.angle
						+ ", retrograde=" + xp[3]);
			} else {
				System.err.println("planet = " + planet);
			}

		}

		String signJson = Util.loadStringFromAssets("sign.json");
		List<ConstellationBean> signList = JsonTool.parseJson(signJson, new TypeToken<List<ConstellationBean>>() {
		}.getType());
		// 计算

		List<String> xingxinXinzuoResultList = new ArrayList<String>();
		List<String> xingxinGonweioResultList = new ArrayList<String>();
		List<String> gonweiXingzuoResultList = new ArrayList<String>();

		final StringBuffer sb = new StringBuffer();
		sb.append("===行星-星座=========\n");
		for (PlanetBean planet : planetList) {
			String[] strs = szZodiac(planet.angle);
			int xingzuo_index = Integer.parseInt(strs[0]);
			ConstellationBean signBean = signList.get(xingzuo_index);

			sb.append(planet.chName + "-->" + signBean.chName + strs[1] + "°" + strs[2] + "′\n");

			String replace = signBean.chName.replace("座", "");
			xingxinXinzuoResultList.add(planet.chName + replace);

			int index = loadHource(planet, houseBeans) + 1;
			sb.append(planet.chName + "-->第" + index + "宫\n");
			String g = "一";
			if (index == 1) {
				g = "一";
			} else if (index == 2) {
				g = "二";
			} else if (index == 3) {
				g = "三";
			} else if (index == 4) {
				g = "四";
			} else if (index == 5) {
				g = "五";
			} else if (index == 6) {
				g = "六";
			} else if (index == 7) {
				g = "七";
			} else if (index == 8) {
				g = "八";
			} else if (index == 9) {
				g = "九";
			} else if (index == 10) {
				g = "十";
			} else if (index == 11) {
				g = "十一";
			} else if (index == 12) {
				g = "十二";
			}
			xingxinGonweioResultList.add(planet.chName + g + "宫");
			gonweiXingzuoResultList.add(g + "宫" + replace);

		}

		// sb.append("\n===行星-宫位=========\n");
		// for (PlanetBean planet : planetList) {
		// String[] strs = szZodiac(planet.angle);
		// sb.append(planet.chName + "-->第" + (Integer.parseInt(strs[0]) + 1) + "宫\n");
		// }
		//

		// List<String> xiangWeiResultList = new ArrayList<String>();
		sb.append("\n===行星-行星相位=========\n");
		for (int i = 0; i < planetList.size() - 1; i++) {
			for (int j = i + 1; j < planetList.size(); j++) {
				double angle = planetList.get(i).angle - planetList.get(j).angle;
				if (angle >= 180) {
					angle = 360 - angle;
				} else if (angle <= -180) {
					angle += 360;
				} else {
					angle = Math.abs(angle);
				}
				AspectType aspectType = AspectType.getType(angle);
				if (aspectType != null) {
					if (planetList.get(i).id == -1 && planetList.get(j).id == -1) {
						continue;
					}
					double d = angle - aspectType.getValue();
					int[] x = calc(d);
					if (planetList.get(i).aspects == null) {
						planetList.get(i).aspects = new ArrayList<Aspect>();
					}
					Aspect aspect = new Aspect(aspectType, planetList.get(j), true);
					aspect.deltaDegree = x[0];
					aspect.deltaMinute = x[1];
					planetList.get(i).aspects.add(aspect);// 更新相位信息
					if (planetList.get(j).aspects == null) {
						planetList.get(j).aspects = new ArrayList<Aspect>();
					}
					aspect = new Aspect(aspectType, planetList.get(i), false);
					aspect.deltaDegree = x[0];
					aspect.deltaMinute = x[1];
					planetList.get(j).aspects.add(aspect);
					sb.append(planetList.get(i).chName + " " + aspectType.getName() + " " + planetList.get(j).chName + "\n");
					// xiangWeiResultList.add(e);
				}
			}
		}

		System.out.println(sb.toString());

		AstrologyResult astrologyResult = new AstrologyResult();

		try {
			String imageFileName = new AstrologyImageUtil().drawImage(houseBeans, planetList);
			astrologyResult.setFileName(imageFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		astrologyResult.setXingxinGonwei(xingxinGonweioResultList);
		astrologyResult.setXingxinXingzuo(xingxinXinzuoResultList);
		astrologyResult.setGonweiXingzuo(gonweiXingzuoResultList);

		System.out.println(astrologyResult);
		return astrologyResult;
	}

	public int loadHource(PlanetBean plannet, List<HouseBean> hourses) {

		int i = 0;
		double d = 0;
		int index = 0;
		boolean first = true;
		for (HouseBean house : hourses) {

			double d1 = plannet.getAngle() - house.getAngle();

			if (d1 > 0) {
				if (first) {
					first = false;
					d = d1;
				}
				if (d1 <= d) {
					d = d1;
					i = index;
				}

			}

			index++;
		}

		return i;

	}

	public String[] szZodiac(double d) {
		String as[] = new String[3];
		int i = (int) d / 30;
		int j = (int) d - i * 30;
		int k = Math.round(Math.round(60D * (d - Math.floor(d))));
		if (k == 60) {
			j++;
			k = 0;
		}
		as[0] = i + "";
		as[1] = String.valueOf(j);
		as[2] = String.valueOf(k);
		return as;
	}

	public int[] calc(double d) {
		int i = (int) d / 30;
		int j = (int) d - i * 30;
		int k = Math.round(Math.round(60D * (d - Math.floor(d))));
		if (k == 60) {
			j++;
			k = 0;
		}
		return new int[] { j, k };
	}

}
