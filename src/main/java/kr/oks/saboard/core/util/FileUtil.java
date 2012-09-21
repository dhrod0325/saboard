package kr.oks.saboard.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import kr.oks.saboard.core.constants.Constants;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil {
	public static String uploadDirectbbsFile(
			MultipartHttpServletRequest request, MultipartFile multipartFile,
			String uploadDirectory) throws Exception {
		File ret = null;
		String storedFileName = null;
		OutputStream outputStream = null;

		try {
			String realPath = request.getSession().getServletContext().getRealPath("/") + Constants.UPLOAD_DIRECTORY;
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

	public static Object readFileObject(String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
		Object o = ois.readObject();
		fis.close();
		ois.close();

		return o;
	}
	
	public static void writeFileObject(String fileName, Object o)throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));

			oos.writeObject(o);
			oos.close();
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String readFile(String fileName) throws Exception {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		reader.close();

		return sb.toString();
	}
}
