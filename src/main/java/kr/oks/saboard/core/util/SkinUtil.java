package kr.oks.saboard.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SkinUtil {
	public static List<String> getSkinList(HttpServletRequest request) throws Exception {
		String skinPath = request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/views/board/skin";
		
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
