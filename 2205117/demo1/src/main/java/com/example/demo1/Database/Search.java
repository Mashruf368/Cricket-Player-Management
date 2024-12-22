package com.example.demo1.Database;
//package Players;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Search {
    
    static List<Player> real = PlayerList.getlist();
    

    public static Player by_name(String a)
    {
        for(int i=0;i<real.size();i++)
        {
            if(real.get(i).getName().equalsIgnoreCase(a)) 
            {
                //real.get(i).printplayer();
                return real.get(i);
            }

        }
        System.out.println("No such player with this name\n");
        return null;
    }

    public Vector<Player> by_country(String a)
    {
        Vector <Player> v = new Vector<>();
        for(int i=0;i<real.size();i++)
        {
            if(real.get(i).getCountry().equalsIgnoreCase(a))
            {
                v.add(real.get(i));
            }
        }
        //PlayerList.printvector(v);
        return v;
    }

    public Vector<Player> by_club(String a,Vector<Player> p)
    {
        Vector <Player> v = new Vector<>();
        for(int i=0;i<p.size();i++)
        {
            if(p.get(i).getClub().equalsIgnoreCase(a))
            {
                v.add(p.get(i));
            }

        }
        //PlayerList.printvector(v);
        return v;
    }
    public static Vector<Player> by_position(String a)
    {
        Vector <Player> v = new Vector<>();
        for(int i=0;i<real.size();i++)
        {
            if(real.get(i).getPosition().equalsIgnoreCase(a)) 
            {

                v.add(real.get(i));
            }
        }
        //PlayerList.printvector(v);
        return v;
    }
    public static Vector<Player> by_salaryrange(int a,int b)
    {
        Vector <Player> v = new Vector<>();
        for(int i=0;i<real.size();i++)
        {
            System.out.println("salary " + real.get(i).getSalary());
            if(real.get(i).getSalary() >=a && real.get(i).getSalary() <=b) 
            {
                v.add(real.get(i));
            }
        }
        //PlayerList.printvector(v);
        return v;
    }

    public static HashMap<String,Integer> country_count()
    {
        HashMap<String,Integer> M =new HashMap<>();
        for(int i=0;i<real.size();i++)
        {
            String s = real.get(i).getCountry();
            if(M.containsKey(s)) M.put(s,(M.get(s)+1));
            else M.put(s,1);
        }
        for(String country : M.keySet())
        {
            System.out.println("Players from " +country + "  : " + M.get(country));
        }
        System.out.println("\n");
        return M;
    }

}
