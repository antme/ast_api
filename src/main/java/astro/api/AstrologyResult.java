package astro.api;

import java.util.List;

public class AstrologyResult {

	private List<String> xingxinXingzuo;
	private List<String> xingxinGonwei;
	private List<String> gonweiXingzuo;
	private List<String> xiangwei;

	private String fileName;
	
	
	

	public List<String> getXiangwei() {
		return xiangwei;
	}

	public void setXiangwei(List<String> xiangwei) {
		this.xiangwei = xiangwei;
	}

	public List<String> getGonweiXingzuo() {
		return gonweiXingzuo;
	}

	public void setGonweiXingzuo(List<String> gonweiXingzuo) {
		this.gonweiXingzuo = gonweiXingzuo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getXingxinXingzuo() {
		return xingxinXingzuo;
	}

	public void setXingxinXingzuo(List<String> xingxinXingzuo) {
		this.xingxinXingzuo = xingxinXingzuo;
	}

	public List<String> getXingxinGonwei() {
		return xingxinGonwei;
	}

	public void setXingxinGonwei(List<String> xingxinGonwei) {
		this.xingxinGonwei = xingxinGonwei;
	}

}
