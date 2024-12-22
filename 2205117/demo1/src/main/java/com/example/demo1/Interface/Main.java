package com.example.demo1.Interface;
//package Players;


import com.example.demo1.Database.Player;
import com.example.demo1.Database.PlayerList;


public class Main {
    public static void main(String [] args)
    {
        PlayerList.makelist();
        com.example.demo1.Interface.Menu.create_menu();
        //PlayerList.printlist();

    }
}







        // PlayerList.printlist();
        // List<Player> real = PlayerList.getlist();
        // System.out.println(real.get(0).name);

        //Search.khujtesi();

        //Search dekhi = new Search();
        // Player s = dekhi.by_name("Virat Kohli");
        // System.out.println(s.name + " " + s.country);
        // Vector <Player> k = dekhi.by_country("Australia");
        // for(int i=0;i<k.size();i++)
        // {
        //     System.out.println(k.get(i).name + " " + k.get(i).country);
        // }

        // HashMap<String,Integer> M = dekhi.country_count();

        // for(String country : M.keySet())
        // {
        //     System.out.println(country + " " + M.get(country));
        // }

        // String s = "Mumbai Indians";
        // Club myteam = new Club(s);
        // myteam.formclub();
        // myteam.printteam();


        // Player ami = new Player();
        // ami.name = "mashruf";
        // ami.age = 21;
        // ami.height = 1.7;
        // ami.salary = 13014020;
        // ami.club = "Fight Club";
        // ami.country = "Saturn";
        // PlayerList.addplayer(ami);
        // PlayerList.printlist();


        
        //PlayerList list = new PlayerList();

        // list.makelist();
        // list.printlist();


