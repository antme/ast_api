// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LocationProvider.java

package com.astrology.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

// Referenced classes of package com.astrology.util:
//			Location

public class LocationProvider
{

	HSSFWorkbook wb;
	String provinces[];

	public LocationProvider(InputStream in)
		throws IOException
	{
		POIFSFileSystem fs = new POIFSFileSystem(in);
		wb = new HSSFWorkbook(fs);
	}

	public String[] listProvinces()
	{
		if (provinces == null)
		{
			int count = wb.getNumberOfSheets();
			provinces = new String[count];
			for (int i = 0; i < count; i++)
				provinces[i] = wb.getSheetName(i);

		}
		return provinces;
	}

	public Location[] listLocations(String province)
	{
		HSSFSheet sheet = wb.getSheet(province);
		int begin = sheet.getFirstRowNum();
		int end = sheet.getLastRowNum();
		Location locs[] = new Location[(end - begin) + 1];
		for (int i = begin; i <= end; i++)
			locs[i - begin] = createLocation(sheet.getRow(i), province);

		return locs;
	}

	private Location createLocation(HSSFRow row, String province)
	{
		return new Location(row.getCell((short)5).getNumericCellValue(), row.getCell((short)4).getNumericCellValue(), row.getCell((short)0).getStringCellValue(), province);
	}
}
