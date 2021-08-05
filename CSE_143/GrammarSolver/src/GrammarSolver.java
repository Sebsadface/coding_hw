// Lefei (Sebastian) Liu
// 04/06/21
// CSE143
// TA: Randair Porter
// Homework 5 (GrammarSolver)
//
// Class GrammarSolver can reads in a list of grammar in Backus-Naur Form and allows users to
// randomly generate elements of the grammar

import java.util.*;

public class GrammarSolver {
    // the grammar rules that grammarSolver is using
    private SortedMap<String, List<String[]>> grammarRules;

    // pre: List<String> grammar is not empty && each nonterminal has only one entry
    //      in List<String> grammar (throws IllegalArgumentException if not)
    // post: constructs a grammarSolver with the list of grammar
    GrammarSolver(List<String> grammar) {
        checkArgument(grammar);
        grammarRules = new TreeMap<>();
        for (String rule : grammar) {
            String[] parts = rule.split("::=");
            checkArgument(parts[0]);
            grammarRules.put(parts[0], getTerminal(parts[1]));
        }
    }

    // post: returns true if the given symbol is a nonterminal of the grammar
    //       returns false otherwise
    public boolean grammarContains(String symbol) {
        return grammarRules.containsKey(symbol);
    }

    // pre: String symbol is a nonterminal of the grammar && times >= 0
    //      (throws IllegalArgumentException if not)
    // post: randomly generates the given number of occurrences of the given symbol
    //       using the grammar and returns the results as an array of strings
    //       equal probability is applied to any given nonterminal symbol
    public String[] generate(String symbol, int times) {
        checkArgument(symbol, times);
        String[] result = new String[times];
        Random rand = new Random();
        for (int i = 0; i < times; i++) {
            result[i] = generate(symbol, rand);
        }
        return result;
    }

    // post: returns a string representation of the various nonterminal symbols from the
    //       grammar as a sorted, comma-separated list enclosed in square brackets
    public String getSymbols() {
        return grammarRules.keySet().toString();
    }

    // post: checks whether the given list of grammar is empty
    //       (throws IllegalArgumentException if the list is empty)
   private void checkArgument(List<String> grammar) {
       if (grammar.isEmpty()) {
           throw new IllegalArgumentException("no grammar found");
       }
   }

   // post: checks whether each nonterminal has only one entry
   //       (throws IllegalArgumentException if not)
   private void checkArgument(String nonterminal) {
       if (grammarRules.containsKey(nonterminal)) {
           throw new IllegalArgumentException(nonterminal + " nonterminal cannot be defined");
       }
   }

   // post: checks whether String symbol is a nonterminal of the grammar && times >= 0
   //       (throws IllegalArgumentException if not)
   private void checkArgument(String symbol, int times) {
       if (!grammarContains(symbol) || times < 0) {
           throw new IllegalArgumentException("symbol: " + symbol + " times: " + times);
       }
   }

   // post: separates each components of a terminal according to standard BNF format
   //       and returns the terminal as a list of arrays of strings
   private List<String[]> getTerminal(String terminal) {
        List<String[]> result = new ArrayList<>();
        String[] rules = terminal.split("[|]");
        for (String rule : rules) {
            result.add(rule.trim().split("[ \t]+"));
        }
        return result;
   }

   // post: randomly generates the given symbol using the grammar and returns the results
   //       as a string, equal probability is applied to any given nonterminal symbol
   private String generate(String symbol, Random rand) {
        if (!grammarRules.containsKey(symbol)) {
            return symbol;
        } else {
            String result = "";
            List<String[]> terminals = grammarRules.get(symbol);
            for (String part : terminals.get(rand.nextInt(terminals.size()))) {
                result += generate(part, rand) + " ";
            }
            return result.trim();
        }
   }
}
