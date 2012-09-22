package kr.oks.saboard;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import kr.oks.saboard.core.constants.Constants;
import kr.oks.saboard.core.util.SessionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController{
	@RequestMapping(value="/file.do",method = RequestMethod.GET)
	public ModelAndView download(HttpServletRequest request,@RequestParam(value="fileName")String fileName) throws Exception{
		File downloadFile = getFile(request,fileName);
		return new ModelAndView("fileDownloadView","downloadFile",downloadFile);
	}
	
	private File getFile(HttpServletRequest request,String fileName) {
		String path = SessionUtil.getServletRealPath(request, "/"+Constants.UPLOAD_DIRECTORY+"/"+fileName);
		return new File(path);
	}
}
