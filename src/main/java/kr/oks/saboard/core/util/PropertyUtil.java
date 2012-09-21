package kr.oks.saboard.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyUtil {
	// 프로퍼티 파일 저장 경로
	URL url = Class.class
			.getResource("/kr/oks/saboard/resources/SQL.properties");

	private String PROPERTIY_FILE = url.getFile();
	private File profile = null;
	private FileInputStream fis = null;
	private static FileOutputStream fos = null;
	private static Properties pros = null;

	// 클레스 생성자
	public PropertyUtil() {
		this.init();
	}

	public void init() {
		profile = new File(PROPERTIY_FILE);
		pros = new Properties();
		try {
			if (!profile.exists())
				profile.createNewFile();
			fis = new FileInputStream(profile);
			fos = new FileOutputStream(profile);
			pros.load(new BufferedInputStream(fis));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPropertyFile(String path) {
		this.PROPERTIY_FILE = path;
	}

	// 키(key)로 값(value)을 가져온다.
	public static String getProperty(String key) throws IOException {
		return pros.getProperty(key);
	}

	// 키(key)로 값(value)을 저장한다.
	public static void setProperty(String key, String value) throws IOException {
		pros.setProperty(key, value);
	}

	// 프로퍼티 파일에 최종 저장 한다.
	public static void storeProperty() throws IOException {
		pros.store(fos, "");
	}

}