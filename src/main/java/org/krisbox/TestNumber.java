package org.krisbox;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.math.BigInteger;

public class TestNumber implements Runnable {
    public TestNumber(BigInteger n, int target, ListView<String> resultList) {
        multiply(n, n, 0, target, resultList);
    }

    private void multiply(BigInteger n, BigInteger original, int step, int target, ListView<String> resultList) {
        char[] number = n.toString().toCharArray();

        BigInteger result = new BigInteger("1");

        for(char digit : number) {
            result = result.multiply(new BigInteger(String.valueOf(digit)));
        }

        if(result.toString().length() > 1) {
            step++;
            multiply(result, original, step++, target, resultList);
        }else{
            step++;
            if(step >= target) {
                resultList.getItems().add(displayResult(step, original));
            }
        }
    }

    public String displayResult(int step, BigInteger n) {
        if(step == 1) {
            return "Number " + n + "\tStep " + step;
        }else {
            return "Number " + n + "\tSteps " + step;
        }
    }

    @Override
    public void run() {

    }
}
