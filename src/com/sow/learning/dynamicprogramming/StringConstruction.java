package com.sow.learning.dynamicprogramming;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dynamic programming with memoize
 * <p>
 * Question: Given a array of strings and a string value find the following
 * canConstruct -> true if the string form array can construct the given value
 * howConstruct -> one Combination of strings from array used to construct the give value or else null
 * bestConstruct -> best Combination(will less no of strings) of strings from array used to construct the given
 * value or else null
 * <p/>
 */
public class StringConstruction {

    public static void main(String[] args) {
        StringConstruction stringConstruction = new StringConstruction();
//        stringConstruction.findHowConstruct(new String[]{"ab","abc","cd","def","abcd"},"abcdef");
//        stringConstruction.findCanMemoizeConstruct(new String[]{"ab","abc","cd","def","abcd"},"abcdef");
//        stringConstruction.finAllMemoizeConstruct(new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}, "enterapotentpot");
        stringConstruction.finAllMemoizeConstruct(new String[]{"purp","p","ur","le","purpl"}, "purple");
        stringConstruction.finAllMemoizeConstruct(new String[]{"a","aa","aaa","aaaa"}, "aaaaaaaaaaaaaaaaaaaaz");
    }

    public void findCanConstruct(String[] stringArray, String value) {
        boolean sol = solveCanConstruct(stringArray, value);
        System.out.println("sol = " + sol);
    }

    public void finAllMemoizeConstruct(String[] stringArray, String value) {
        Map<String,List<List<String>>> memo = new HashMap<>();
        List<List<String>> allMatchedLists = solveAllMemoizeConstruct(stringArray, value, memo);
        System.out.println("memo = " + memo);
        System.out.println("allMatchedLists = " + allMatchedLists);
    }

    private List<List<String>> solveAllMemoizeConstruct(String[] stringArray, String value,  Map<String,List<List<String>>> memo) {
       if(memo.containsKey(value)){
           return memo.get(value);
       }
        System.out.println("calculating for: "+value);
        List<List<String>> validList = new ArrayList<>();
        if (value.equals("")) {
            List<List<String>> matchedList = new ArrayList<>();
            matchedList.add(new ArrayList<>());
            return matchedList;
        }

        for (String s : stringArray) {
            if (value.startsWith(s)) {
                String reducedString = value.replaceFirst(s, "");
                List<List<String>> matchedList = solveAllMemoizeConstruct(stringArray, reducedString,memo);
                for (List<String> strings : matchedList) {
                    strings.add(s);
                }
                validList.addAll(matchedList);
            }
        }
        memo.put(value,validList);
        return validList;
    }

    public void findHowConstruct(String[] stringArray, String value) {
        List<String> stringList = solveHowConstruct(stringArray, value);
        System.out.println("stringList = " + stringList);
    }

    public void findBestConstruct(String[] stringArray, String value) {
        List<String> stringList = solveBestConstruct(stringArray, value);
        System.out.println("stringList = " + stringList);
    }

    private boolean solveCanConstruct(String[] stringArray, String value) {
        if (value.length() == 0) {
            return true;
        }
        for (final String s : stringArray) {
            if (value.startsWith(s)) {
                final String reducedString = value.replace(s, "");
                if (solveCanConstruct(stringArray, reducedString)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<String> solveHowConstruct(String[] stringArray, String value) {
        if (value.length() == 0) {
            return new ArrayList<>();
        }
        for (final String s : stringArray) {
            if (value.startsWith(s)) {
                final String reducedString = value.replace(s, "");
                final List<String> validSolutionList = solveHowConstruct(stringArray, reducedString);
                if (validSolutionList != null) {
                    validSolutionList.add(s);
                    return validSolutionList;
                }
            }
        }
        return null;
    }

    private List<String> solveBestConstruct(String[] stringArray, String value) {
        if (value.length() == 0) {
            return new ArrayList<>();
        }
        List<String> bestCombination = null;
        for (final String s : stringArray) {
            if (value.startsWith(s)) {
                final String reducedString = value.replace(s, "");
                final List<String> validSolutionList = solveHowConstruct(stringArray, reducedString);
                if (validSolutionList != null) {
                    validSolutionList.add(s);
                    if (bestCombination == null || bestCombination.size() > validSolutionList.size()) {
                        bestCombination = validSolutionList;
                    }
                }
            }
        }
        return bestCombination;
    }
}
