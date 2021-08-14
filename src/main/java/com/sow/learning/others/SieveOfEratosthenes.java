package com.sow.learning.others;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SieveOfEratosthenes {
    private void findAllPrimeNumbers(int number){
        List<Integer> integerList = IntStream.range(2, number + 1).boxed().collect(Collectors.toList());
        solve(integerList,number,0);
    }

    private void solve(List<Integer> integerList,int number,int index) {
        int selectedNumber = integerList.get(index);
        if(selectedNumber>Math.sqrt(number)){
            System.out.println(integerList);
        }
        else{
            List<Integer> filteredList = integerList.stream()
                    .filter(integer -> {
                        if(integer>selectedNumber){
                            return integer % selectedNumber != 0;
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
            solve(filteredList,number,index+1);
        }
    }

    public static void main(String[] args) {
        SieveOfEratosthenes s = new SieveOfEratosthenes();
        s.findAllPrimeNumbers(30);
    }
}
