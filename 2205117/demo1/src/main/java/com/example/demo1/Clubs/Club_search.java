package com.example.demo1.Clubs;
//package Players;

import java.util.Vector;

import com.example.demo1.Database.Player;

public class Club_search {
    Vector<Player> team = new Vector<>();
    public Vector<Player> max_salary()
    {
        long max=-1;
        Vector<Player> v= new Vector<>();
        for(int i=0;i<team.size();i++)
        {
            
            if(team.get(i).getSalary() > max) 
            {
                max = team.get(i).getSalary();
                v.clear();
                v.add(team.get(i));
            }
            else if(team.get(i).getSalary() == max) v.add(team.get(i));
        }
        // PlayerList.printvector(v);
        //return v;
        //return max;
        return v;

        
    }

    void printteam()   //works
    {
        for(int i=0;i<team.size();i++)
        {
            System.out.println(team.get(i).getName() + "\n");
        }
    }

    public Vector<Player> max_age()
    {
        int max=-1;
        Vector<Player> v= new Vector<>();
        for(int i=0;i<team.size();i++)
        {
            
            if(team.get(i).getAge() > max) 
            {
                max = team.get(i).getAge();
                //index=i;
                v.clear();
                v.add(team.get(i));
            }
            else if(team.get(i).getAge() == max) v.add(team.get(i));
        }
        //team.get(index).printplayer();
        //return max;
        return v;
    }
    public Vector<Player> max_height()
    {
        double max=-1;
        //int index=-1;
        Vector <Player> v = new Vector<>();
        for(int i=0;i<team.size();i++)
        {
            
            if(team.get(i).getHeight() > max) 
            {
                max = team.get(i).getHeight();
                v.clear();
                v.add(team.get(i));
            }
            else if(team.get(i).getHeight() == max) v.add(team.get(i));
            
        }
        //team.get(index).printplayer();
        //return max;
        return v;
    }

    public long total_yearly_salary()
    {
        long s=0;
        for(int i=0;i<team.size();i++)
        {
            s+=team.get(i).getSalary()*52;
        }
        //return s;
        System.out.println("Total yearly salary is " + s);
        return s;
    }
}
