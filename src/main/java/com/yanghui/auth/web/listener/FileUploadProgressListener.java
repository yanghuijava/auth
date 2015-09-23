package com.yanghui.auth.web.listener;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.yanghui.auth.dto.Progress;

public class FileUploadProgressListener implements ProgressListener{
	
	private HttpSession session;

	public FileUploadProgressListener() {  }  
	
    public FileUploadProgressListener(HttpSession session) {
        this.session=session;  
        Progress progress = new Progress();
        session.setAttribute("progress", progress);  
    }  
	
	/**
	 * pBytesRead 到目前为止读取文件的比特数 pContentLength 文件总大小 pItems 目前正在读取第几个文件
	 */
	public void update(long pBytesRead, long pContentLength, int pItems) {
		Progress progress = (Progress) session.getAttribute("progress");
		progress.setBytesRead(pBytesRead);
		progress.setContentLength(pContentLength);
		progress.setItems(pItems);
		session.setAttribute("progress", progress);
	}
}
