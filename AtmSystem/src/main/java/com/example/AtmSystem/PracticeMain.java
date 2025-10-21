/*
package com.example.AtmSystem;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PracticeMain {
    public static void main(String[] args) {
       */
/* Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
       Stream<Integer> str=stream.filter(integer ->  integer % 2 != 0);
        System.out.println(str.max( Integer::compareTo));
*//*

   */
/* List list = new ArrayList();
    list.add("1");
    list.add("krish");
    list.add(3);*//*

        // Stream stream1 = Stream.of("Krishna", 5, 5.23, "Sita", "Gita", "Radha");
    */
/*    Stream stream1= list.stream();
        stream1.forEach(i -> {
            if(i.equals("Krish")){
                System.out.println("Found!");
            }
        });*//*

*/
/*int[] arr= {1,2,3,1,2,3,4};
int[] uniq = Arrays.stream(arr).filter(i->i%2==0).distinct().toArray();
System.out.println(Arrays.toString(uniq));*//*


      */
/*  int n = 6;
         int k = 4;
         int[] arr = new int[n];
         int counter = k;
         for(int num :arr){
             System.out.println(num);
             while(num!=0) {
                 if (num == counter + 1) {
                     arr[num] = 0;
                     counter++;

                     }

                 }

             }*//*

   */
/* String sentence = "I am learning softwareengineer course";

    String ans = Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length)).get();
        System.out.println(ans);*//*



        */
/*List<Integer> numbers = Arrays.asList(2,1,4,3,8,5,5);
        int store =  numbers.stream().reduce(1, (c, e)->c+e ); System.out.println(store);
    *//*
*/
/*  int add = store.stream().reduce(0, (c, e)->c+e );

        System.out.println(add);*//*



List<String> names = Arrays.asList("krishna","Hari","Radha","Gita","Krishna");
List<String> sortedNames= names.stream().sorted(String.CASE_INSENSITIVE_ORDER).map(e->e.toUpperCase()).collect(Collectors.toList());
List namesAndSum =names.stream().map(e-> e+ " (" + e.length() + ") ").toList();
        System.out.println(namesAndSum);

List<String> result = sortedNames.stream().filter(name -> name.startsWith("K")).collect(Collectors.toList());
System.out.println(result);

        //System.out.println(sum);












    }

    }


*/


import java.util.*;

public class PracticeMain {
    public static void main(String[] args) {
        /*Queue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.offer(11);
        priorityQueue.offer(2);
        priorityQueue.offer(5);
        priorityQueue.offer(3);
        System.out.println(priorityQueue);
       priorityQueue.poll();
        //System.out.println(priorityQueue.peek());
        System.out.println(priorityQueue);
        //priorityQueue.peek();
        System.out.println(priorityQueue.peek());

*/
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offer(2);
        deque.offerFirst(10);
        deque.offerLast(20);
        deque.offer(30);
        deque.offer(40);
        System.out.println(deque);
       // deque.pollFirst();
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());
        System.out.println(deque);
        System.out.println(deque.peek());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());


    }
}























