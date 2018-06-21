//package com.bullhorn;
//
//import sun.jvm.hotspot.ui.table.TableModelComparator;
//
//import java.util.*;
//
//public class SimpleComparatorTest {
//
//    public static void main(String[] args){
//        LinkedList<Integer> lst = new LinkedList<>();
//        for(int i=1;i<=9;i++){
//            lst.add(i);
//        }
//
//        lst.forEach((x)-> System.out.println(x));
//
//        List<TblX> tx = Arrays.asList(
//                new TblX("MPOW", 100000011L, "eRecruit"),
//                new TblX("A", 100000013L, "eRecruit"),
//                new TblX("MPOW", 100000015L, "eRecruit"),
//                new TblX("A", 100000011L, "eRecruit"),
//                new TblX("A", 100000012L, "eRecruit"),
//                new TblX("MPOW", 200000013L, "eRecruit"));
//
//        tx.forEach(System.out::println);
//        System.out.println("------");
//
//        Comparator<TblX> byClient
//                = (TblX t1, TblX t2) -> t1.getClient().compareTo(t2.getClient());
//
//        Collections.sort(tx,byClient);
//        tx.forEach(System.out::println);
//        System.out.println("------");
//
//        Comparator<TblX> bySequenceNumer
//                = (TblX t1, TblX t2) -> new Long (t1.getSequenceNumber() - t2.getSequenceNumber()).intValue();
//        Collections.sort(tx,bySequenceNumer);
//        tx.forEach(System.out::println);
//        System.out.println("------");
//
//        tx.sort(Comparator.comparing(TblX::getClient).thenComparing(TblX::getSequenceNumber));
//        tx.forEach(System.out::println);
//
//
//    }
//
//    private static class TblX{
//        String client;
//        Long sequenceNumber;
//        String mapName;
//
//        public TblX(String client, Long sequenceNumber, String mapName) {
//            this.client = client;
//            this.sequenceNumber = sequenceNumber;
//            this.mapName = mapName;
//        }
//
//        public String getClient() {
//            return client;
//        }
//
//        public void setClient(String client) {
//            this.client = client;
//        }
//
//        public Long getSequenceNumber() {
//            return sequenceNumber;
//        }
//
//        public void setSequenceNumber(Long sequenceNumber) {
//            this.sequenceNumber = sequenceNumber;
//        }
//
//        @Override
//        public String toString() {
//            return "TblX{" +
//                    "client='" + client + '\'' +
//                    ", sequenceNumber=" + sequenceNumber +
//                    '}';
//        }
//    }
//}
