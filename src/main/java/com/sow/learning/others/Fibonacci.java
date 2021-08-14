package com.sow.learning.others;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

   private final Map<Integer,Integer> cache = new HashMap();

    public int fib(int value){
        if(value<=1){
            return 1;
        }
        else if(cache.containsKey(value)){
            return cache.get(value);
        }
        else {
            int result = fib(value - 1) + fib(value - 2);
            cache.put(value,result);
            return result;
        }
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int fib = fibonacci.fib(10);
        System.out.println("fib = " + fib);
    }
}

