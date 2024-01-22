package com.example.jdk.update.jdk21;

import java.util.*;
import java.util.stream.Collectors;

public class JDK21Demo {


    public static void main(String[] args) {

        JDK21Demo jdk21Demo = new JDK21Demo();
        jdk21Demo.emoji();
        //go to pattern matching for switch
        jdk21Demo.sequencedCollections();
        jdk21Demo.strings();
    }

    private void strings() {
        String string = "the red brown fox jumps over the lazy dog";
        String[] parts = string.splitWithDelimiters(" ", 5);
        System.out.println(Arrays.stream(parts).collect(Collectors.joining("', '", "'", "'")));

        StringBuilder sb = new StringBuilder();
        sb.repeat("Hello ", 2);
        sb.repeat(0x1f600, 5);
        sb.repeat('!', 3);
        System.out.println(sb);
    }

    private void sequencedCollections() {

        List<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);   // List contains: [1]

        arrayList.addFirst(0);  // List contains: [0, 1]
        arrayList.addLast(2);   // List contains: [0, 1, 2]

        Integer firstElement = arrayList.getFirst();  // 0
        Integer lastElement = arrayList.getLast();  // 2
        System.out.printf(" first is %s and the last is %s", firstElement, lastElement);

        List<Integer> reversed = arrayList.reversed();
        System.out.println(reversed); // Prints [2, 1, 0]

        System.out.println("------------------------------");
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        map.firstEntry();   //1=One
        map.lastEntry();    //3=Three

        System.out.println(map);  //{1=One, 2=Two, 3=Three}

        Map.Entry<Integer, String> first = map.pollFirstEntry();   //1=One
        Map.Entry<Integer, String> last = map.pollLastEntry();    //3=Three

        System.out.println(map);  //{2=Two}

        map.putFirst(1, "One");     //{1=One, 2=Two}
        map.putLast(3, "Three");    //{1=One, 2=Two, 3=Three}

        System.out.println(map);  //{1=One, 2=Two, 3=Three}
        System.out.println(map.reversed());   //{3=Three, 2=Two, 1=One}
        System.out.println("------------------------------");
    }


    private void emoji() {
        String welcomeMsg = "Hey Java Developers! 🙋🏻‍♂️";
        OptionalInt emojiOptional = welcomeMsg.codePoints().filter(Character::isEmoji).findFirst();
        if (emojiOptional.isPresent()) {
            int emojiCodePoint = emojiOptional.getAsInt();
            if (Character.isEmojiModifierBase(emojiCodePoint)) {
                System.out.println("Emoji can be modified");
                if (Character.isEmojiModifier(emojiCodePoint)) {
                    System.out.println("Emoji is modified");
                } else {
                    System.out.println("Emoji has not been modified");
                }
            } else {
                System.out.println("Emoji cannot be modified");
            }
        } else {
            System.out.println("No emoji");
        }
    }
}
