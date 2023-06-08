package org.voiculescu.sdjpa.creditcard.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EncryptionInterceptor implements Interceptor {

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("onSave Interceptor");
        return Interceptor.super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("onLoad Interceptor");
        return Interceptor.super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("onFlushDirty Interceptor");
        return Interceptor.super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }
}
