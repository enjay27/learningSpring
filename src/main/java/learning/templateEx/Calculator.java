package learning.templateEx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiFunction;

public class Calculator {

    public int fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(filepath)))
        {
            int ret = callback.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int calcSum(String filePath) throws IOException {
        LineCallback sumCallback =
                new LineCallback() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return value + Integer.parseInt(line);
                    }
                };
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public int calcMultiply(String filePath) throws IOException {
        LineCallback sumCallback =
                new LineCallback() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return value * Integer.parseInt(line);
                    }
                };
        return lineReadTemplate(filePath, sumCallback, 1);
    }

    public Integer lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Integer res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Integer lineReadTemplateLambda(String filePath, BiFunction<String, Integer, Integer> callback, int initVal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Integer res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callback.apply(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int calcSumLambda(String filePath) throws IOException {
        BiFunction<String, Integer, Integer> sumCallback = (line, value) -> value + Integer.parseInt(line);
        return lineReadTemplateLambda(filePath, sumCallback, 0);
    }
}
