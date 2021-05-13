package learning.spring.proxy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import static org.assertj.core.api.Assertions.assertThat;

class PointcutTest {
    @Test
    public void classNamePointcutAdvisor() {
        //포인트컷 준비
        NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
            public ClassFilter getClassFilter() {
                return new ClassFilter() {
                    @Override
                    public boolean matches(Class<?> clazz) {
                        return clazz.getSimpleName().startsWith("HelloT");
                    }
                };
            }
        };

        classMethodPointcut.setMappedName("sayH*");

        checkAdvised(new HelloTarget(), classMethodPointcut, true);

        class HelloWorld extends HelloTarget {};
        checkAdvised(new HelloWorld(), classMethodPointcut, false);

        class HelloTKyu extends HelloTarget {};
        checkAdvised(new HelloTKyu(), classMethodPointcut, true);
    }

    private void checkAdvised(Object target, Pointcut pointcut, boolean advised) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new DynamicProxyTest.UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();

        if (advised) {
            assertThat(proxiedHello.sayHello("kyu")).isEqualTo("HELLO KYU");
            assertThat(proxiedHello.sayHi("kyu")).isEqualTo("HI KYU");
            assertThat(proxiedHello.sayThankYou("kyu")).isEqualTo("Thank You kyu");
        }
        else {
            assertThat(proxiedHello.sayHello("kyu")).isEqualTo("Hello kyu");
            assertThat(proxiedHello.sayHi("kyu")).isEqualTo("Hi kyu");
            assertThat(proxiedHello.sayThankYou("kyu")).isEqualTo("Thank You kyu");
        }
    }

}