package kr.oks.saboard;

import java.io.File;

import kr.oks.saboard.core.constants.Constants;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileDownLoadController implements ApplicationContextAware{
	
	private WebApplicationContext context = null;
	
	@RequestMapping(value="/file",method = RequestMethod.GET)
	public ModelAndView download(@RequestParam(value="fileName")String fileName) throws Exception{
		File downloadFile = getFile(fileName);
		return new ModelAndView("fileDownloadView","downloadFile",downloadFile);
	}
	
	private File getFile(String fileName) {
		String path =  context.getServletContext().getRealPath("/"+Constants.UPLOAD_DIRECTORY+"/"+fileName);
		return new File(path);
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = (WebApplicationContext) context;
	}
}
