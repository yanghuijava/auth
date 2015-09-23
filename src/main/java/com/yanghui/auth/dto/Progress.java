package com.yanghui.auth.dto;

import java.text.DecimalFormat;

public class Progress {
	
	
	private long bytesRead;
	private long contentLength;
	private int items;
	private String percent;
	
	public String getPercent() {
		DecimalFormat df = new DecimalFormat("#%");
		this.percent = df.format(this.bytesRead * 1D/this.contentLength);
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public long getBytesRead() {
		return bytesRead;
	}
	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Progress [bytesRead=" + bytesRead + ", contentLength="
				+ contentLength + ", items=" + items + "]";
	}
}
