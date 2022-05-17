package com.gramdel.listeners;

import com.gramdel.beans.ResultBean;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

public class MXBeanContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ManagementFactory.getPlatformMBeanServer().registerMBean(new ResultBean(), new ObjectName("com.gramdel.beans:type=ResultBean,name=result"));
        } catch (InstanceAlreadyExistsException | MalformedObjectNameException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(new ObjectName("com.gramdel.beans:type=ResultBean,name=result"));
        } catch (MBeanRegistrationException | InstanceNotFoundException | MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }
}
