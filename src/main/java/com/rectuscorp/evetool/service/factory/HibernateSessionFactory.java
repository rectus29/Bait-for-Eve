package com.rectuscorp.evetool.service.factory; /**
 *
 * User: olivier
 * Date: 24/07/11
 * Time: 08:22
 */

import com.rectuscorp.evetool.entities.GenericEntity;
import com.rectuscorp.evetool.service.GenericManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HibernateSessionFactory implements ApplicationListener<ContextRefreshedEvent>, ServiceFactory {

    private static final Logger log = LogManager.getLogger(HibernateSessionFactory.class);

    private Map<Class<? extends GenericEntity>, GenericManager<?, Long>> services
            = new HashMap<Class<? extends GenericEntity>, GenericManager<?, Long>>();

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext ctx = contextRefreshedEvent.getApplicationContext();
        services.clear();
        Map<String, GenericManager> detectedServices = ctx.getBeansOfType(GenericManager.class);
        for (GenericManager service : detectedServices.values()) {
            services.put(service.getEntityClass(), service);
        }
    }

    public <T extends GenericEntity> GenericManager<T, Long> getServiceFor(Class<T> entityClass) {
        return (GenericManager<T, Long>) services.get(entityClass);
    }
}
