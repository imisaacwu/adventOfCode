package AoC2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("AoC2023/Day7.txt"));
        List<Hand> hands = new ArrayList<>(), hands2 = new ArrayList<>();
        while(file.hasNextLine()) {
            String s = file.nextLine();
            hands.add(new Hand(s.split(" ")[0].toCharArray(), Integer.parseInt(s.split(" ")[1])));
            hands2.add(new Hand(s.split(" ")[0].replaceAll("J", "?").toCharArray(), Integer.parseInt(s.split(" ")[1])));
        }
        hands.sort(null);
        hands2.sort(null);
        int winnings = 0, winnings2 = 0;
        for(int i = 0; i < hands.size(); i++) {
            winnings += (i+1) * hands.get(i).bet;
            winnings2 += (i+1) * hands2.get(i).bet;
        }

        System.out.printf("Total winnings: %s\nTotal winnings (with Jokers): %s\n", winnings, winnings2);
    }

    public static class Hand implements Comparable<Hand> {
        private final Map<Integer, String> type;
        private List<Card> cards;
        private double value;
        private int bet;
    
        public Hand(char[] c, int bet) {
            type = new HashMap<>(Map.of(
                8, "Five of a kind",
                6, "Four of a kind",
                5, "Full house",
                4, "Three of a kind",
                3, "Two pair",
                2, "One pair",
                1, "High card"));
            cards = new ArrayList<>();
            double i = 100.0;
            for(char card : c) {
                Card temp = new Card(card);
                value += temp.value / i;
                cards.add(temp);
                i *= 100;
            }
            value = Math.round(value * Math.pow(10, 10)) / Math.pow(10, 10);
            value += score();
            this.bet = bet;
        }
    
        public int compareTo(Hand other) {
            double diff = this.value - other.value;
            if(-1.0 <= diff && diff <= 1.0) {
                return diff == 0.0 ? 0 : diff > 0.0 ? 1 : -1;
            }
            return (int)diff;
        }
    
        public double score() {
            int score = 1, joker = 0;
            Set<Card> unique = new TreeSet<>(cards);
            Iterator<Card> i = unique.iterator();
            List<Card> subHand;
            while(i.hasNext() && score < 5.0) {
                subHand = new ArrayList<>(cards);
                subHand.retainAll(Arrays.asList(i.next()));
                if(subHand.get(0).name.equals("Joker")) {
                    joker = subHand.size();
                } else if(subHand.size() > 3) {
                    score = (subHand.size() - 1) * 2;
                } else if(subHand.size() == 3) {
                    score += 3.0;
                } else if(subHand.size() == 2) {
                    switch((int)score) {
                        case 4: // 3 of a kind -> Full house
                        case 2: // 1 pair -> 2 pair
                            score++;
                            break;
                        default:
                            score = 2;
                    }
                }
            }
            if(joker > 0) {
                score += 2 * (joker == 5 ? 4 : joker) + (score == 1 ? -1 : 0);
            }
            return score;
        }
    
        public String toString() {
            return cards +
                   String.format(" -> %1$-15s", type.get((int)value)) +
                   String.format(" | (%s)", value);
        }
    
        public class Card implements Comparable<Card> {
            private String name;
            private int value;
    
            public Card(char c) {
                if(50 <= c && c <= 57) {
                    name = "" + c;
                    value = c - '0';
                } else {
                    switch(c) {
                        case 'T':
                            name = "Ten";
                            value = 10;
                            break;
                        case 'J':
                            name = "Jack";
                            value = 11;
                            break;
                        case 'Q':
                            name = "Queen";
                            value = 12;
                            break;
                        case 'K':
                            name = "King";
                            value = 13;
                            break;
                        case 'A':
                            name = "Ace";
                            value = 14;
                            break;
                        case '?':
                            name = "Joker";
                            value = 1;
                            break;
                    }
                }
            }
    
            public int compareTo(Card other) {
                return this.value - other.value;
            }
    
            public boolean equals(Object other) {
                return other instanceof Card && ((Card)other).value == value;
            }
    
            public String toString() {
                return name.equals("Joker") ? "?" : name.substring(0, 1);
            }
        }
    }
}