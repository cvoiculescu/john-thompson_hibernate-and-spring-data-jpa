package org.voiculescu.sdjpa.creditcard.listeners;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.voiculescu.sdjpa.creditcard.interceptors.EncryptedString;
import org.voiculescu.sdjpa.creditcard.services.EncryptionService;

import java.lang.reflect.Field;
import java.util.Objects;

public abstract class AbstractEncryptionListener {
    private final EncryptionService encryptionService;

    public AbstractEncryptionListener(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public void encrypt(Object[] state, String[] propertyNames, Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> encryptField(field, state, propertyNames), this::isFieldEncrypted);
    }


    private void encryptField(Field field, Object[] state, String[] propertyNames) {
        int idx = getPropertyIndex(field.getName(), propertyNames);
        Object currentValue = state[idx];
        state[idx] = encryptionService.encrypt(currentValue.toString());
    }

    public void decrypt(Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> decryptField(field, entity), this::isFieldEncrypted);
    }

    private void decryptField(Field field, Object entity) {
        field.setAccessible(true);
        Object value = ReflectionUtils.getField(field, entity);
        ReflectionUtils.setField(field, entity, encryptionService.decrypt(Objects.requireNonNull(value).toString()));
    }


    private boolean isFieldEncrypted(Field field) {
        return AnnotationUtils.findAnnotation(field, EncryptedString.class) != null;
    }

    private int getPropertyIndex(String name, String[] properties) {
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].equals(name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Property not found: " + name);
    }
}
