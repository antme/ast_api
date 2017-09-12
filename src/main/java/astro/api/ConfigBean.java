package astro.api;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigBean {

	private static Properties p = null;

	private static Map<String, String> gpsMap = new HashMap<String, String>();

	private static Font font = null;

	public static String getProperty(String key) {

		if (p == null) {
			p = new Properties();
			try {
				p.load(ConfigBean.class.getResourceAsStream("/config.properties"));
				p.load(ConfigBean.class.getResourceAsStream("/com/astrology/i18n/Resources_zh_CN.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Properties props = System.getProperties();

			if (!props.getProperty("os.name").toLowerCase().contains("windows")) {
				p.put("ephemeris_path", "/data/ephemeris/");
				p.put("pic_path", "/opt/lampp/htdocs/sites/default/files/xingpan/");
			}

			intialGpsMap();

		}
		return p.getProperty(key);
	}

	private static void intialGpsMap() {

		if (gpsMap.isEmpty()) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(ConfigBean.class.getResourceAsStream("/gps.txt")));
			try {
				String line = reader.readLine();
				while (line != null) {

					String[] lines = line.split(" ");
					if (lines.length > 2) {
						gpsMap.put(lines[0], lines[1] + " " + lines[2]);
					}

					line = reader.readLine();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param area
	 * @return 经度
	 */
	public static double getLongitude(String area) {

		return parserGpsInfo(area, 0);

	}

	/**
	 * 
	 * @param area
	 * @return 维度
	 */
	public static double getLatitude(String area) {

		return parserGpsInfo(area, 1);

	}

	private static double parserGpsInfo(String area, int type) {
		intialGpsMap();
		String[] areas = area.split(" ");

		if (areas.length > 2) {
			String province = areas[0].replaceAll("省", "").replaceAll("市", "");
			String shi = areas[1].replaceAll("市", "");
			String city = areas[2].replaceAll("县", "");

			String gps = gpsMap.get(province + city);
			if (gps != null) {

				return Double.parseDouble(gps.split(" ")[type].trim());
			} else {
				gps = gpsMap.get(province + shi);
				if (gps != null) {

					return Double.parseDouble(gps.split(" ")[type].trim());
				} else {
					gps = gpsMap.get(province + province);
					if (gps != null) {

						return Double.parseDouble(gps.split(" ")[type].trim());
					}
				}
			}

		}

		if (type == 0) {
			return 121.48;
		}

		return 31.22;
	}

	public static Font getFont() {

		if (font == null) {
			InputStream is = ConfigBean.class.getResourceAsStream("/kanxingpan.ttf");
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return font;

	}

}
