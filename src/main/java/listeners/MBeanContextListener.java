package listeners;

import beans.ResultBean;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.management.ManagementFactory;

@WebListener
public class MBeanContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        try {
            ManagementFactory.getPlatformMBeanServer().registerMBean(new ResultBean(), new ObjectName("beans:type=ResultBean"));
        } catch (InstanceAlreadyExistsException | MalformedObjectNameException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(new ObjectName("beans:type=ResultBean"));
        } catch (MBeanRegistrationException | InstanceNotFoundException | MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }
}
