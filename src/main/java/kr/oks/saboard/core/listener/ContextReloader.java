package kr.oks.saboard.core.listener;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class ContextReloader implements ApplicationContextAware,ApplicationListener<ContextRefreshedEvent> {
	private ApplicationContext applicationContext;

	public void reloadContext() {
		if (applicationContext instanceof ConfigurableWebApplicationContext) {
			ConfigurableWebApplicationContext configurableCtx = (ConfigurableWebApplicationContext) applicationContext;
			String[] configLocations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml" };
			configurableCtx.setConfigLocations(configLocations);
			configurableCtx.refresh();
		}
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 설정 확인
		if (new File("classpath:kr/oks/saboard/resources/custom.properties").exists()) {
			reloadContext();
		}
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.applicationContext = context;
	}
}