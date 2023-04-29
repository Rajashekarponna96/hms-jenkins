package com.spdx.hms.util;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;

@UtilityClass
public class EntityRefreshUtil {

    public static <T> T refresh(EntityManager entityManager, Object o, Class<T> clazz) {
        entityManager.refresh(o);
        return (T) o;
    }
}
