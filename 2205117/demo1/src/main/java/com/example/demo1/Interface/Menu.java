package com.example.demo1.Interface;
//package Players;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

//import Clubs.Club;
//import Database.Player;
//import Database.PlayerList;
//import Database.Search;

import com.example.demo1.Database.Search;
import com.example.demo1.Clubs.Club;
import com.example.demo1.Clubs.Club_search;

import com.example.demo1.Database.Player;
import com.example.demo1.Database.PlayerList;

public class Menu {

        public static void create_menu(){
        Scanner t = new Scanner(System.in);
        int window=0;
        boolean loop=true;
        while(loop){
            switch(window)
            {
                
                case 0:
                {
                    System.out.println("Main Menu");
                    System.out.println("1. Search Players");
                    System.out.println("2. Search Clubs");
                    System.out.println("3. Add Player");
                    System.out.println("4. Exit System");
                    try{int m = t.nextInt();
                    t.nextLine();
                    window = m;
                    if(m<0 || m>4) {
                        System.out.println("Enter a number from 1 to 4\n");
                        window = 0;
                    }
                    else window = m;
                    //else break;
                    
                    break;}
                    catch(InputMismatchException ex)
                    {
                        System.out.println("Enter a number from 1 to 4\n");
                        t.nextLine();
                        break;
                    }
                }
                case 1:
                {
                    Search finder = new Search();
                    System.out.println("Player Searching Options");
                    System.out.println("1. By player name");
                    System.out.println("2. By club and country");
                    System.out.println("3. By position");
                    System.out.println("4. By salary range");
                    System.out.println("5. Country wise player count");
                    System.out.println("6. Back to Main Menu");
                    
                    int m;
                    try{
                        m =t.nextInt();
                        t.nextLine();
                        if(m<0 || m>6) 
                        {
                            System.out.println("Enter a number from 1 to 6\n");
                            window = 1;
                        }
                        
                    }
                    catch(InputMismatchException ex)
                    {
                        System.out.println("Enter a number from 1 to 4\n");
                        t.nextLine();
                        break;
                    }

                    switch(m)
                    {
                        case 1:
                        {
                            String s=t.nextLine();
                            Player a=finder.by_name(s);
                            a.printplayer();
                            break;

                        }
                        case 2:
                        {
                            System.out.println("Input a country");
                            String s=t.nextLine();
                            Vector <Player> first = finder.by_country(s);
                            System.out.println("Input a club");
                            String p = t.nextLine();
                            if(p.equalsIgnoreCase("ANY")) PlayerList.printvector(first);
                            else {
                                Vector<Player> second = finder.by_club(p,first);
                                PlayerList.printvector(second);
                            }
                            break;
                        }
                        case 3:
                        {
                            String s=t.nextLine();
                            Vector<Player> b = finder.by_position(s);
                            PlayerList.printvector(b);
                            break;
                        }
                        case 4:
                        {
                            int a=t.nextInt();
                            int b=t.nextInt();
                            t.nextLine();
                            System.out.println(a + " " + b);
                            Vector<Player> c = finder.by_salaryrange(a, b);
                            PlayerList.printvector(c);
                            break;

                        }
                        case 5:
                        {
                            HashMap<String,Integer> M = finder.country_count();
                            break;
                        }
                        case 6:
                        {
                            window = 0 ;
                            break;
                        }
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("(1) Player(s) with the maximum salary of a club");
                    System.out.println("(2) Player(s) with the maximum age of a club");
                    System.out.println("(3) Player(s) with the maximum height of a club");
                    System.out.println("(4) Total yearly salary of a club");
                    System.out.println("(5) Back to Main Menu");
                    int m=-1;
                    try{m =t.nextInt();
                    t.nextLine();
                    if(m<0 || m> 5) 
                    {
                        System.out.println("Enter a number from 1 to 5\n");
                        window =2;
                    }
                    }
                    catch(InputMismatchException ex)
                    {
                        System.out.println("Enter a number from 1 to 5\n");
                        t.nextLine();
                        break;
                    }
                    switch(m) 
                    {
                        
                        case 1:
                        {
                            System.out.println("Enter name of club");
                            String s = t.nextLine();
                            Club myteam = new Club(s);
                            myteam.formclub();    //a list of players created from this club
                            Vector <Player> temp = myteam.max_salary();
                            PlayerList.printvector(temp);
                            //long a = myteam.max_salary();
                            break;
                        }
                        case 2:
                        {
                            System.out.println("Enter name of club");
                            String s = t.nextLine();
                            Club myteam = new Club(s);
                            myteam.formclub();
                            Vector <Player> b =myteam.max_age();
                            PlayerList.printvector(b);
                            break;
                        }
                        case 3:
                        {
                            System.out.println("Enter name of club");
                            String s = t.nextLine();
                            Club myteam = new Club(s);
                            myteam.formclub();
                            Vector <Player> c=myteam.max_height();
                            PlayerList.printvector(c);
                            break;
                        }
                        case 4:
                        {
                            System.out.println("Enter name of club");
                            String s = t.nextLine();
                            Club myteam = new Club(s);
                            myteam.formclub();
                            long d = myteam.total_yearly_salary();
                            break;
                        }
                    
                    //if(m==5) {window=0;break;}
                        case 5:
                        {
                            window=0;
                            break;
                        }
                        
                    }
                    break;
                }
                case 3:
                {
                    PlayerList.addplayer();
                    //PlayerList.printlist();
                    window = 0;
                    break;
                }
                case 4:
                {
                    Save_players.save();
                    loop=false;
                    break;
                }
                
            }
        }
    }
        
}





