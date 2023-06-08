package org.voiculescu.sdjpa.creditcard.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.voiculescu.sdjpa.creditcard.services.EncryptionService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EncryptionInterceptor implements Interceptor {

    private final EncryptionService encryptionService;

    public EncryptionInterceptor(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("onSave Interceptor");
        Object[] newState = processFields(entity, state, propertyNames, "onSave");
        return Interceptor.super.onSave(entity, id, newState, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("onLoad Interceptor");
        Object[] newState = processFields(entity, state, propertyNames, "onLoad");
        return Interceptor.super.onLoad(entity, id, newState, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("onFlushDirty Interceptor");
        Object[] newState = processFields(entity, currentState, propertyNames, "onFlushDirty");
        return Interceptor.super.onFlushDirty(entity, id, newState, previousState, propertyNames, types);
    }

    private Object[] processFields(Object entity, Object[] state, String[] propertyNames, String type) {
        List<String> annotatedFields = getEncryptedStringAnnotatedFields(entity);
        for (String field : annotatedFields) {
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals(field)) {
                    if (StringUtils.hasText(state[i].toString())) {
                        if ("onSave".equals(type) || "onFlushDirty".equals(type)) {
                            state[i] = encryptionService.encrypt(state[i].toString());
                        } else if ("onLoad".equals(type)) {
                            state[i] = encryptionService.decrypt(state[i].toString());
                        }
                    }
                }
            }
        }
        return state;
    }


    private List<String> getEncryptedStringAnnotatedFields(Object entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> !Objects.isNull(field.getAnnotation(EncryptedString.class)))
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
