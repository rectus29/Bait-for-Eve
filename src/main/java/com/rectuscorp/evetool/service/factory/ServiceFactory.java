package com.rectuscorp.evetool.service.factory;

import com.rectuscorp.evetool.entities.core.GenericEntity;
import com.rectuscorp.evetool.service.GenericManager;

/**
 *
 * User: olivier
 * Date: 24/07/11
 * Time: 08:24
 */
public interface ServiceFactory {
    <T extends GenericEntity> GenericManager<T, Long> getServiceFor(Class<T> entityClass);
}
