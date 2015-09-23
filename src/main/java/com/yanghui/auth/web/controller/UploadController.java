package com.yanghui.auth.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.yanghui.auth.biz.utils.DateUtil;
import com.yanghui.auth.dto.Message;
import com.yanghui.auth.dto.Progress;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@RequestMapping("/save.json")
	@ResponseBody
	public Message upload(@RequestParam(value="files", required=true)MultipartFile[] files){
		InputStream is = null;
		OutputStream os = null;
		try {
			for(MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				os = new FileOutputStream("F:/tmp/upload/" + fileName.substring(0, fileName.lastIndexOf(".")) + DateUtil.format(new Date(), "yyyyMMddhhmmss") 
						+ fileName.substring(fileName.lastIndexOf(".",fileName.length())));
				is = file.getInputStream();
				int read = 0;
				byte[] buf = new byte[1024];
				while((read = is.read(buf)) != -1) {
					os.write(buf, 0, read);
				}
				os.flush();
			}
			return new Message("上传成功！", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message("上传失败！", false);
		}finally{
			try {
				if(is != null)is.close();
				if(os != null)os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
    public Message handleException(Exception ex,HttpServletRequest request) {       
         if (ex instanceof MaxUploadSizeExceededException){  
        	 return new Message("文件过大！", false);
         } 
         return new Message("上传失败！", false);
    } 
	
	@RequestMapping("/getProgress.json")
	@ResponseBody
	public Progress getProgress(HttpServletRequest request){
		Progress progress = (Progress)request.getSession().getAttribute("progress");
		if(progress == null) {
			progress = new Progress();
			progress.setBytesRead(0);
		}
		return progress;
	}
}
