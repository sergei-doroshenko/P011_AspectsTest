package org.sergei.aspect.staticcrosscutting;

import org.apache.commons.beanutils.BeanUtils;
import org.sergei.domain.Customer;

import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by sergei on 12/21/15.
 */
public aspect BeanMakerAspect {
    private PropertyChangeSupport Customer.propertyChangeSupport;

    public void Customer.addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void Customer.removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    pointcut beanCreation(Customer bean) : initialization(org.sergei.domain.Customer+.new(..)) && this(bean);

    pointcut beanPropertyChange(Customer bean, Object newValue)
            : execution(void org.sergei.domain.Customer+.set*(*)) && args(newValue) && this(bean);

    after(Customer bean) returning : beanCreation(bean) {
        bean.propertyChangeSupport = new PropertyChangeSupport(bean);
    }

    void around(Customer bean, Object newValue) : beanPropertyChange(bean, newValue) {
        String methodName = thisJoinPointStaticPart.getSignature().getName();
        String propertyName = Introspector.decapitalize(methodName.substring(3));
        Object oldValue = getPropertyValue(bean, propertyName);
        proceed(bean, newValue);
        bean.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    private static Object getPropertyValue(Object bean,String propertyName) {
        try {
            return BeanUtils.getProperty(bean, propertyName);
        } catch (Exception ex) {
            return null;
        }
    }
}
