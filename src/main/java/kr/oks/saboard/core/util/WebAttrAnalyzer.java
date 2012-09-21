package kr.oks.saboard.core.util;


import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;


public class WebAttrAnalyzer {
   @SuppressWarnings("unchecked")
   public static void analyze(HttpServletRequest request){

      int index = 0;
      log("---------------------------------");
      log("   HttpRequest Parameters");
      log("---------------------------------");
      Enumeration<String> enu = (Enumeration<String>)request.getParameterNames();
      while(enu.hasMoreElements()){  
         String name = enu.nextElement();
         log((++index)+". "+name+"="+request.getParameter(name));
      }
      
      log("---------------------------------");
      log("   HttpRequest Attributes");
      log("---------------------------------");
      enu = request.getAttributeNames();
      index = 0;
      while(enu.hasMoreElements()){
         String name = enu.nextElement();
         Object attribute = request.getAttribute(name);
         log((++index)+". "+name+"="+attribute);
         if(attribute instanceof AbstractApplicationContext){
            analyze((AbstractApplicationContext)attribute);
         }
      }
      
      HttpSession session = request.getSession(false);
      if(session==null)
         return;
      log("---------------------------------");
      log("   HttpSession Attributes");
      log("---------------------------------");
      enu = session.getAttributeNames();
      index = 0;
      while(enu.hasMoreElements()){
         String name = enu.nextElement();
         Object attribute = session.getAttribute(name);
         log((++index)+". "+name+"="+attribute);
         if(attribute instanceof AbstractApplicationContext){
            analyze((AbstractApplicationContext)attribute);
         }
      }
      
      log("---------------------------------");
      log("   ServletContext Attributes");
      log("---------------------------------");
      ServletContext context = session.getServletContext();
      enu = context.getAttributeNames();
      index = 0;
      while(enu.hasMoreElements()){
         String name = enu.nextElement();
         Object attribute = context.getAttribute(name);
         log((++index)+". "+name+"="+attribute);
         if(attribute instanceof AbstractApplicationContext){
            analyze((AbstractApplicationContext)attribute);
         }
      }
   }

   public static void analyze(Map<String, ?> map){
      log("---------------------------------");
      Set<String> keySet = map.keySet();
      for(String key: keySet){
         log(key+"="+map.get(key));
      }
      log("---------------------------------");
   }

   public static void analyze(AbstractApplicationContext context){
       String names[] = context.getBeanDefinitionNames();
       for(String name: names){
           Object bean = context.getBean(name);
           log("\t"+name+"> "+bean);
       }
   }
   
   public static void log(String str){
	   System.out.println(str);
   }
}