package learning.spring.proxy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class HelloTargetTest {

    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("kyu")).isEqualTo("Hello kyu");
        assertThat(hello.sayHi("kyu")).isEqualTo("Hi kyu");
        assertThat(hello.sayThankYou("kyu")).isEqualTo("Thank You kyu");
    }

    @Test
    public void upperProxy() {
        Hello proxiedHello = new HelloUppercase(new HelloTarget());
        assertThat(proxiedHello.sayHello("kyu")).isEqualTo("HELLO KYU");
        assertThat(proxiedHello.sayHi("kyu")).isEqualTo("HI KYU");
        assertThat(proxiedHello.sayThankYou("kyu")).isEqualTo("THANK YOU KYU");
    }

    @Test
    public void dynamicProxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] {Hello.class},
                new UppercaseHandler(new HelloTarget())
        );

    }
}