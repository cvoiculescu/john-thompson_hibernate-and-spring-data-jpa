package org.voiculescu.sdjpa.creditcard.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;
import org.voiculescu.sdjpa.creditcard.interceptors.EncryptionInterceptor;

import java.util.Map;

@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {
    private final EncryptionInterceptor encryptionInterceptor;

    public InterceptorRegistration(EncryptionInterceptor encryptionInterceptor) {
        this.encryptionInterceptor = encryptionInterceptor;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", encryptionInterceptor);
    }

}
