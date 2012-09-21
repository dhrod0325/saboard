package kr.oks.saboard.core.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

public class BeanFinder implements ApplicationContextAware {
	static WebApplicationContext[] contextList = null;

	public void setApplicationContext(ApplicationContext applicationContext) {
		if (contextList == null) {
			contextList = new WebApplicationContext[1];
			contextList[0] = (WebApplicationContext) applicationContext;
		} else {
			WebApplicationContext[] newContextList = new WebApplicationContext[contextList.length + 1];
			System.arraycopy(contextList, 0, newContextList, 0,contextList.length);
			newContextList[contextList.length] = (WebApplicationContext) applicationContext;
			contextList = newContextList;
		}
	}

	public static String[] getBeanDefinitionNames() {

		if (contextList.length == 0)
			return null;

		if (contextList.length == 1)
			return contextList[0].getBeanDefinitionNames();

		String[] beanNameList = contextList[0].getBeanDefinitionNames();
		for (int i = 1; i < contextList.length; i++) {
			String[] tempNameList = contextList[i].getBeanDefinitionNames();
			if ((tempNameList == null) || (tempNameList.length == 0))
				continue;
			String[] aggregatedList = new String[beanNameList.length
					+ tempNameList.length];
			System.arraycopy(beanNameList, 0, aggregatedList, 0,
					beanNameList.length);
			System.arraycopy(tempNameList, 0, aggregatedList,
					beanNameList.length, tempNameList.length);
			beanNameList = aggregatedList;
		}
		return beanNameList;
	}

	public static String[] getBeanNamesForType(Class<?> clazz) {

		if (contextList.length == 0)
			return null;

		if (contextList.length == 1)
			return contextList[0].getBeanNamesForType(clazz);

		String[] beanNameList = contextList[0].getBeanNamesForType(clazz);
		for (int i = 1; i < contextList.length; i++) {
			String[] tempNameList = contextList[i].getBeanNamesForType(clazz);
			if ((tempNameList == null) || (tempNameList.length == 0))
				continue;
			String[] aggregatedList = new String[beanNameList.length
					+ tempNameList.length];
			System.arraycopy(beanNameList, 0, aggregatedList, 0,
					beanNameList.length);
			System.arraycopy(tempNameList, 0, aggregatedList,
					beanNameList.length, tempNameList.length);
			beanNameList = aggregatedList;
		}
		return beanNameList;
	}

	public static Object getBean(String name) {
		for (WebApplicationContext context : contextList) {
			try {
				Object target = context.getBean(name);
				if (target != null)
					return target;
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static Object getBean(Class<?> clazz) {
		for (WebApplicationContext context : contextList) {
			String[] beanNameList = context.getBeanNamesForType(clazz);
			if ((beanNameList != null) && (beanNameList.length != 0))
				return context.getBean(beanNameList[0]);
		}
		return null;
	}

	public static ServletContext getServletContext() {
		if ((contextList == null) || (contextList.length == 0))
			return null;
		return contextList[0].getServletContext();
	}
}
