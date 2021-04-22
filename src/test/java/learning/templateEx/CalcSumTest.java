package learning.templateEx;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcSumTest {

    Calculator calculator;
    String numFilePath;

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilePath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(numFilePath)).isEqualTo(10);

        System.out.println(numFilePath);
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(numFilePath)).isEqualTo(24);
    }

    @Test
    public void sumOfNumbersLambda() throws IOException {
        assertThat(calculator.calcSumLambda(numFilePath)).isEqualTo(10);
    }
}
