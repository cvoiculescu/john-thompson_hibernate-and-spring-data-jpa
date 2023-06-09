package org.voiculescu.sdjpa.creditcard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;
import org.voiculescu.sdjpa.creditcard.interceptors.EncryptionInterceptor;

import java.util.Map;

//@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {
    //    @Autowired
    //    EncryptionInterceptor encryptionInterceptor;

    public InterceptorRegistration() {
//        this.encryptionInterceptor = encryptionInterceptor;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
//        hibernateProperties.put("hibernate.session_factory.interceptor", encryptionInterceptor);
    }

}
