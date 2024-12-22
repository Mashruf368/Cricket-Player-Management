package com.example.demo1.Database;
//package Players;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int jersey_no;
    private long salary;
    public Player(String a,String b,int c,double d,String e,String f,int g,long h)
    {
        name = a;
        country = b;
        age = c;
        height = d;
        club = e;
        position = f;
        jersey_no = g;
        salary = h;
    }
    public Player()
    {
        
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setClub(String club) {
        this.club = club;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setJersey_no(int jersey_no) {
        this.jersey_no = jersey_no;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }
    public String getCountry() {
        return country;
    }
    public int getAge() {
        return age;
    }
    public double getHeight() {
        return height;
    }
    public String getClub() {
        return club;
    }
    public String getPosition() {
        return position;
    }
    public int getJersey_no() {
        return jersey_no;
    }
    public long getSalary() {
        return salary;
    }

    public void printplayer()
    {

        System.out.println(this.toString());
    }

    @Override
    public String toString()
    {
        String r;
        String s = "Name : " +name +"\nCountry : " + country + "\nAge : " + age + "\nHeight : " + height + "m\nClub : " + club + "\nPosition : " + position;
        if(jersey_no!=0) r = "\nJersey Number : " + jersey_no ;
        else r = "\nJersey Number : Unavailable";
        String t = "\nWeekly Salary : " + salary + "\n";
        return s+r+t;

    }
}
