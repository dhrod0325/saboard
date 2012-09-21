package kr.oks.saboard.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

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
			String realPath = request.getContextPath()
					+ Constants.UPLOAD_DIRECTORY;
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

	public static Object readFileObject(String fileName) throws Exception {// 저장한
																			// 객체를
																			// 가저와서
																			// 읽는다.
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(
				fis));
		Object o = ois.readObject();
		fis.close();
		ois.close();

		return o;
	}
	public static void main(String[] args) throws Exception{
		writeFileObject("","test");
		
	}
	public static void writeFileObject(String fileName, Object o)throws Exception {// 파일을 만들고 객체를 파일안에 저장한다.
		try {
			URL url = Class.class.getResource("/kr/oks/saboard/resources/SQL.properties");

			FileInputStream pin = new java.io.FileInputStream(url.getFile());

			Properties props = new java.util.Properties();

			props.load(pin);
			
			System.out.println(props.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
//		ClassLoader classLoader = Thread.currentThread()
//				.getContextClassLoader();
//		InputStream input = classLoader.getResourceAsStream(fileName);
//
//		FileOutputStream fos = new FileOutputStream(fileName);
//		ObjectOutputStream oos = new ObjectOutputStream(
//				new BufferedOutputStream(fos));
//
//		oos.writeObject(o);
//		oos.close();
//		fos.close();
	}

	public static void writeFile(String fileNmae) throws Exception {
		FileOutputStream fos = new FileOutputStream(fileNmae);
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
