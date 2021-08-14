package com.sow.learning.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringConstructionTable {


    public static void main(String[] args) {
        StringConstructionTable stringConstructionTable = new StringConstructionTable();
        boolean canConstruct = stringConstructionTable.canConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"});
//        int howConstruct = stringConstructionTable.howConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"});
        int howConstruct = stringConstructionTable.howConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd","ef","c"});
        System.out.println("canConstruct = " + canConstruct);
        System.out.println("howConstruct = " + howConstruct);
    }

    public boolean canConstruct(String value, String[] wordBank) {
        boolean[] table = new boolean[value.length() + 1];
        //if the value is "" (empty string) then we can always construct it by not selecting any value
        table[0] = true;
        for (int i = 0; i < table.length; i++) {
            if (table[i]) {
                for (String s : wordBank) {
                    if (value.substring(i).startsWith(s) && (s.length() + i) <= table.length) {
                        table[s.length() + i] = true;
                    }
                }
            }
        }
        System.out.println("table = " + Arrays.toString(table));
        return table[value.length()];
    }

    public int howConstruct(String value, String[] wordBank) {
        List<List<List<String>>> allMatched= new ArrayList<>();
        for (int i = 0; i < value.toCharArray().length+1; i++) {
                    allMatched.add(new ArrayList<>());
        }

        allMatched.get(0).add(Collections.emptyList());

        //if the value is "" (empty string) then we can always construct it by not selecting any value

        for (int i = 0; i < value.length(); i++) {

                for (String s : wordBank) {
                    if (value.substring(i).startsWith(s) && (s.length() + i) <= value.length()) {

                            List<List<String>> wrappingList = allMatched.get((s.length() + i));
                            if(wrappingList.size()==0){
                                ArrayList<String> list = new ArrayList<>();
                                if(!value.substring(0,i).isEmpty()){
                                    list.add(value.substring(0,i));
                                }
                                list.add(s);
                                wrappingList.add(list);
                            }
                            else{
                                for (List<String> strings : wrappingList) {
                                    if(!value.substring(0,i).isEmpty()){
                                        strings.add(value.substring(0,i));
                                    }
                                    strings.add(s);
                                }
                            }

                    }
                }

        }
        System.out.println("table = " + allMatched);
        return 0;
    }
}
