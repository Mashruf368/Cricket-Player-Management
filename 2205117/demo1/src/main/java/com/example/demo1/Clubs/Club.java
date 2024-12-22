package com.example.demo1.Clubs;
//package Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.example.demo1.Database.Player;
import com.example.demo1.Database.PlayerList;

public class Club extends com.example.demo1.Clubs.Club_search {
    String team_name;
    private static List<Player> all_players = new ArrayList<>();
    //Vector<Player> team = new Vector<>();
    public Club(String a)
    {
        team_name=a;
    }
    Club()
    {

    }

    public Vector<Player> formclub()
    {
        
        all_players = PlayerList.getlist();
        for(int i=0;i<all_players.size();i++)
        {
            if(all_players.get(i).getClub().equalsIgnoreCase(team_name)) team.add(all_players.get(i));
        }
        if(team.size()>0) return team;
        else 
        {
            System.out.println("No such club with this name");
            return null;
        }
        
    }
    

    

}





    // void max_salary()
    // {
    //     long max=-1;
    //     Vector<Player> v= new Vector<>();
    //     for(int i=0;i<team.size();i++)
    //     {
            
    //         if(team.get(i).salary > max) 
    //         {
    //             max = team.get(i).salary;
    //             v.clear();
    //             v.add(team.get(i));
    //         }
    //         if(team.get(i).salary == max) v.add(team.get(i));
    //     }
    //     PlayerList.printvector(v);
    //     //return v;
        
    // }

    // void printteam()   //works
    // {
    //     for(int i=0;i<team.size();i++)
    //     {
    //         System.out.println(team.get(i).name + "\n");
    //     }
    // }

    // void max_age()
    // {
    //     int max=-1;
    //     int index=-1;
    //     for(int i=0;i<team.size();i++)
    //     {
            
    //         if(team.get(i).age > max) 
    //         {
    //             max = team.get(i).age;
    //             index=i;
    //         }
    //     }
    //     team.get(index).printplayer();
        
    // }
    // void max_height()
    // {
    //     double max=-1;
    //     int index=-1;
    //     for(int i=0;i<team.size();i++)
    //     {
            
    //         if(team.get(i).height > max) 
    //         {
    //             max = team.get(i).height;
    //             index=i;
    //         }
    //     }
    //     team.get(index).printplayer();
       
    // }

    // void total_yearly_salary()
    // {
    //     long s=0;
    //     for(int i=0;i<team.size();i++)
    //     {
    //         s+=team.get(i).salary*52;
    //     }
    //     //return s;
    //     System.out.println("Total yearly salary is " + s);
    // }
