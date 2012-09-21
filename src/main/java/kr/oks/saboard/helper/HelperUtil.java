package kr.oks.saboard.helper;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import kr.oks.saboard.constants.Constants;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

public class HelperUtil implements ApplicationContextAware {
	private HelperHTMLInputFilter htmlInputFilter = new HelperHTMLInputFilter();
	private HelperXSSFilter xssFilter = new HelperXSSFilter();
	
	private WebApplicationContext context = null;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = (WebApplicationContext) context;
	}

	private String getContextPath(){
		return context.getServletContext().getRealPath("/");
	}
	
	public String htmlInputFilter(String input){
		return htmlInputFilter.filter(input);
	}
	
	public String removeXSS(String input){
		return xssFilter.removeXSS(input);
	}
	/**
	 * 
	 * @param multipartFile
	 * @param uploadDirectory
	 * @return
	 * @throws Exception
	 */
	public String uploadDirectbbsFile(MultipartFile multipartFile, String uploadDirectory) throws Exception {
		File ret = null;
		String storedFileName = null;
		OutputStream outputStream = null;

		try {
			String realPath = getContextPath() + Constants.UPLOAD_DIRECTORY;
			String tempFileName = multipartFile.getOriginalFilename();

			String fileName = System.currentTimeMillis() + tempFileName;

			File fileParhDir = new File(realPath);

			if (!fileParhDir.exists()) {
				fileParhDir.mkdirs();
			}

			ret = new File(realPath, fileName);

			multipartFile.transferTo(ret);

			storedFileName = fileName;
		} catch (Exception e) {
			throw e;
		} finally {
			if (outputStream != null)
				outputStream.close();
		}

		return storedFileName;
	}

	public List<String> getSkinList() throws Exception {
		String skinPath = getContextPath() + "/WEB-INF/views/board/skin";
		
		File dirFile = new File(skinPath);
		
		File[] fileList = dirFile.listFiles();
		
		List<String> tempSkinList = new ArrayList<String>();
		
		for (File tempFile : fileList) {
			if (tempFile.isDirectory()) {
				tempSkinList.add(tempFile.getName());
			}
		}

		return tempSkinList;
	}
}
