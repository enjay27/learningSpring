package learning.spring.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.*;

public class DynamicProxyTest {

    @Test
    public void proxyFactoryBean() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());

        Hello proxiedHello = (Hello) pfBean.getObject();
        assert proxiedHello != null;
        assertThat(proxiedHello.sayHello("kyu")).isEqualTo("HELLO KYU");
        assertThat(proxiedHello.sayHi("kyu")).isEqualTo("HI KYU");
        assertThat(proxiedHello.sayThankYou("kyu")).isEqualTo("THANK YOU KYU");

    }

    @Test
    public void pointcutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) pfBean.getObject();

        assert proxiedHello != null;
        assertThat(proxiedHello.sayHello("kyu")).isEqualTo("HELLO KYU");
        assertThat(proxiedHello.sayHi("kyu")).isEqualTo("HI KYU");
        assertThat(proxiedHello.sayThankYou("kyu")).isEqualTo("Thank You kyu");
    }

    static class UppercaseAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Object ret = invocation.proceed();
            if(ret instanceof String){
                return ((String)ret).toUpperCase();
            }
            return ret;
        }
    }

    static interface Hello {
        String sayHello(String name);
        String sayHi(String name);
        String sayThankYou(String name);
    }

    static class HelloTarget implements Hello {

        @Override
        public String sayHello(String name) {
            return "Hello " + name;
        }

        @Override
        public String sayHi(String name) {
            return "Hi " + name;
        }

        @Override
        public String sayThankYou(String name) {
            return "Thank You " + name;
        }
    }



}
