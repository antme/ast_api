// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Config.java

package com.astrology;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.Velocity;

import com.astrology.util.AstrologyFonts;
import com.astrology.util.CommonUtil;

// Referenced classes of package com.astrology:
//			ConfigurationException, VersionInfo

public class Config
{

	private static Log log = LogFactory.getLog(Config.class);
	public static final String DEFAULT_CONFIG_FILE = "/jastrology.properties";
	private Properties config;
	private String confiname;
	private boolean isInitialised;
	private Hashtable colorlist;
	private Hashtable fontlist;
	private Hashtable imagelist;
	private static boolean velocityInited = false;
	private static Hashtable configs = new Hashtable();
	static Font defaultFont = new Font("Dialog", 0, 12);
	private static VersionInfo version;

	public static Config getConfig(String configfile)
	{
		if (CommonUtil.isEmpty(configfile))
			configfile = "/jastrology.properties";
		Object config = configs.get(configfile);
		if (config != null && (config instanceof Config))
		{
			return (Config)config;
		} else
		{
			config = new Config(configfile);
			configs.put(configfile, config);
			return (Config)config;
		}
	}

	public Config()
	{
		config = null;
		isInitialised = false;
		init();
	}

	public Config(String configfile)
	{
		config = null;
		isInitialised = false;
		confiname = configfile;
		init();
		configs.put(confiname, this);
	}

	public Config(String configname, Properties props)
	{
		config = null;
		isInitialised = false;
		confiname = configname;
		config = new Properties();
		config.putAll(props);
		clean();
	}

	public synchronized void reload()
		throws ConfigurationException
	{
		isInitialised = false;
		init();
	}

	public void set(String key, String value)
	{
		config.setProperty(key, value);
	}

	public void save(OutputStream out)
		throws IOException
	{
		config.store(out, "");
	}

	protected synchronized void init()
		throws ConfigurationException
	{
		if (isInitialised)
			return;
		try
		{
			log.info((new StringBuilder("loading config file ")).append(confiname).toString());
			InputStream is = Config.class.getResourceAsStream(confiname);
			if (is == null)
				throw new ConfigurationException((new StringBuilder("Can't find the properties file. Make sure '")).append(confiname).append("' is in the CLASSPATH.").toString());
			config = new Properties();
			config.load(is);
			is.close();
		}
		catch (IOException ex)
		{
			config = null;
			throw new ConfigurationException((new StringBuilder("Error while loading properties from '")).append(confiname).append("'. ").append(ex.getMessage()).toString());
		}
		catch (Exception e)
		{
			config = null;
			throw new ConfigurationException("Error while initial configuration", e);
		}
		clean();
		isInitialised = true;
		log.info("Config initialized!");
	}

	public static void initVelocity()
		throws Exception
	{
		if (velocityInited)
		{
			return;
		} else
		{
			Properties p = new Properties();
			p.load( Config.class.getResourceAsStream("/velocity.properties"));
			Velocity.init(p);
			velocityInited = true;
			return;
		}
	}

	protected void clean()
	{
		log.info("initial color and font cache... ");
		colorlist = new Hashtable();
		fontlist = new Hashtable();
		imagelist = new Hashtable();
	}

	public String getString(String key)
	{
		return config.getProperty(key);
	}

	public String getString(String key, String default_value)
	{
		return config.getProperty(key, default_value);
	}

	public int getInt(String key)
	{
		return getInt(key, 0);
	}

	public int getInt(String key, int default_value)
	{
		int result = default_value;
		String setting = config.getProperty(key);
		if (setting != null)
			try
			{
				result = Integer.parseInt(setting);
			}
			catch (NumberFormatException numberformatexception) { }
		return result;
	}

	public long getLong(String key, long default_value)
	{
		long result = default_value;
		String setting = config.getProperty(key);
		if (setting != null)
			try
			{
				result = Long.parseLong(setting);
			}
			catch (NumberFormatException numberformatexception) { }
		return result;
	}

	public float getFloat(String key, float default_value)
	{
		float result = default_value;
		String setting = config.getProperty(key);
		if (setting != null)
			try
			{
				result = Float.parseFloat(setting);
			}
			catch (NumberFormatException numberformatexception) { }
		return result;
	}

	public boolean getBoolean(String key)
	{
		String setting = config.getProperty(key);
		return setting != null && !setting.equals("") && !setting.equals("0") && !setting.equals("no") && !setting.equals("false");
	}

	public Color getColor(String key)
	{
		Object obj = colorlist.get((new StringBuilder(String.valueOf(key))).append(".color").toString());
		if (obj != null && (obj instanceof Color))
			return (Color)obj;
		Color color = null;
		try
		{
			color = Color.decode(getString((new StringBuilder(String.valueOf(key))).append(".color").toString()));
		}
		catch (Throwable fe)
		{
			color = UIManager.getColor("control");
		}
		finally
		{
			colorlist.put((new StringBuilder(String.valueOf(key))).append(".color").toString(), color);
			return color;
		}
	}

	public Font getFont(String key)
	{
		Object obj = fontlist.get((new StringBuilder(String.valueOf(key))).append(".font").toString());
		if (obj != null && (obj instanceof Font))
			return (Font)obj;
		Font font = null;
		try
		{
			StringTokenizer st = new StringTokenizer(getString((new StringBuilder(String.valueOf(key))).append(".font").toString()), ",");
			String fname = st.nextToken();
			int style = Integer.parseInt(st.nextToken());
			float size = Float.parseFloat(st.nextToken());
			if (fname.endsWith(".ttf"))
				font = AstrologyFonts.getFont(fname).deriveFont(style, size);
			else
				font = new Font(fname, style, (int)size);
		}
		catch (Throwable fe)
		{
			font = UIManager.getFont("control");
			if (font == null)
				font = defaultFont;
		}
		finally
		{
			fontlist.put((new StringBuilder(String.valueOf(key))).append(".font").toString(), font);
			return font;
		}
	}

	public BufferedImage getImage(String key)
	{
		BufferedImage image;
		Object obj = imagelist.get((new StringBuilder(String.valueOf(key))).append(".src").toString());
		if (obj != null && (obj instanceof BufferedImage))
			return (BufferedImage)obj;
		image = null;
		String path = getString((new StringBuilder(String.valueOf(key))).append(".src").toString());
		if (CommonUtil.isEmpty(path))
			return null;
		try
		{
			image = ImageIO.read( Config.class.getResource(path));
			imagelist.put((new StringBuilder(String.valueOf(key))).append(".src").toString(), image);
		}
		catch (Throwable e)
		{
			log.error((new StringBuilder("can not read image ")).append(key).toString(), e);
		}
		return image;
	}

	public static VersionInfo getVersionInfo()
	{
		if (version != null)
			return version;
		try
		{
			log.debug("loading version info ");
			InputStream is = Config.class.getResourceAsStream("/version.info");
			if (is == null)
				throw new ConfigurationException("Can't find the version.info file. ");
			Properties vp = new Properties();
			vp.load(is);
			is.close();
			version = new VersionInfo(vp.getProperty("codename"), vp.getProperty("version"), vp.getProperty("release"), vp.getProperty("builddate"));
		}
		catch (IOException ex)
		{
			throw new ConfigurationException((new StringBuilder("Error while loading properties from  version.info. ")).append(ex.getMessage()).toString());
		}
		return version;
	}

}
