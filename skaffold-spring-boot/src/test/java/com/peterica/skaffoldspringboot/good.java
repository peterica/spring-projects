package com.peterica.skaffoldspringboot;
class Book {
    private String name; private int year;
    public String getName(){
        return name; }
    public int getYear(){ return year;
    }
    public void add(String name, int year){
        this.name = name;
        this.year = year; }
}
public class good {
    public static void main(String[]args) {
        Book b = new Book();
        b.add("정보처리기사", 23);
        System.out.println(b.getName()+b.getYear()+2000); // 정보처리기사232000
        System.out.print(b.getYear()+2000+b.getName()); // 2023정보처리기사
    }
}

