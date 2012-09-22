package kr.oks.saboard;

import java.io.File;
import java.io.FileOutputStream;

import kr.oks.saboard.core.constants.Constants;
import kr.oks.saboard.core.listener.ContextReloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SetupController {
	
	@Autowired
	private ContextReloader contextReloader;

	@RequestMapping("/install")
	public String home() {
		//파라미터 하나만 넘기고
		return "/install/step1";
	}

	@RequestMapping("/install/save")
	public String step1() throws Exception {
		saveConfig(); 
		contextReloader.reloadContext();
		
		return "redicret:" + Constants.URL_ADMIN_INDEX;
	}

	private void saveConfig() throws Exception{
		FileOutputStream fos = new FileOutputStream(new File(""));
		// 설정 파일을 지정한 경로에 저장
	}

	public void setContextReloader(ContextReloader contextReolader) {
		this.contextReloader = contextReolader;
	}
}


