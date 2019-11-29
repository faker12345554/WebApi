package com.admin.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

 @WebListener
public class SessionListener implements HttpSessionListener {

  //  protected static Logger logger=LoggerFactory.getLogger(ApiLoginController.class);


            public static Map userMap = new HashMap();
     private SessionContext sessionContext = SessionContext.getInstance();


             public void sessionCreated(HttpSessionEvent httpSessionEvent) {
                 HttpSession session = httpSessionEvent.getSession();
                 //logger.debug("info------>sessionCreated----->sessionId:" + session.getId());
                 sessionContext.AddSession(session);
             }

             public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
                 HttpSession session = httpSessionEvent.getSession();
                // logger.debug("info------>sessionDeath----->sessionId:" + httpSessionEvent.getSession().getId());
                 sessionContext.DelSession(session);
             }
}
