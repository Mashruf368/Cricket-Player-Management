package com.example.demo1.Database;
//package Players;

import java.util.*;
import java.io.*;


public class PlayerList
{
    public static List<Player> playerList = new ArrayList<>();
    //public static void main(String [] args)
    //{
        public static void makelist(){
        try(BufferedReader me= new BufferedReader(new FileReader("C:/Users/Hp/Downloads/2205117 (6)/2205117/demo1/src/main/java/com/example/demo1/Database/players.txt")))
        {
        //BufferedReader me = new BufferedReader(new FileReader("players.txt"));
        // BufferedReader me = new BufferedReader(new InputStreamReader(
        // Main.class.getResourceAsStream("players.txt")
        // )   );
        String s;
        
            while((s=me.readLine())!=null)
            {
                //s=me.readLine();
                
                int index=0;
                int j=0;
                Player temp = new Player();
                //System.out.println(s);
                for(int i=0;i<s.length();i++)
                {
                    //System.out.println(s.charAt(i));
                    if(s.charAt(i)==',' || i==s.length()-1)
                    {
                        //System.out.println("found at " + i + " " +j);
                        switch(index)
                        {
                            case 0:
                                {
                                    String r = s.substring(j, i);
                                    temp.setName(r); 
                                    break;
                                }
                            case 1:
                                {
                                    String r = s.substring(j, i);
                                    temp.setCountry(r); ;
                                    break;
                                }
                            case 2:
                                {
                                    int r = Integer.parseInt(s.substring(j, i));
                                    temp.setAge(r); 
                                    break;
                                }
                            case 3:
                                {
                                    double r =Double.parseDouble(s.substring(j,i));
                                    temp.setHeight(r); 
                                    break;

                                }
                            case 4:
                                {
                                    String r = s.substring(j,i);
                                    temp.setClub(r); 
                                    break;
                                }
                            case 5:
                                {
                                    String r =s.substring(j,i);
                                    temp.setPosition(r);
                                    break;
                                }
                            case 6:
                                {
                                    // int r =Integer.parseInt(s.substring(j,i));
                                    // if((s.charAt(j)==',')) temp.setJersey_no(0);
                                    // else
                                    // temp.setJersey_no(r); 
                                    // break;


                                    try{
                                        int r = Integer.parseInt(s.substring(j, i));
                                        temp.setJersey_no(r) ;
                                        break;
                                    }catch (NumberFormatException e){
                                        //System.out.println("error in " + temp.getName());
                                        temp.setJersey_no(0);
                                    }
                                }

                            default:
                                {
                                    // int r = Integer.parseInt(s.substring(j, i));
                                    // temp.setSalary(r); 
                                    // break;

                                    try{
                                        int r = Integer.parseInt(s.substring(j, i+1));
                                        temp.setSalary(r); 
                                        break;
                                    }catch (NumberFormatException e){
                                        //System.out.println("error in " + temp.getName());
                                    }
                                }
                        }
                        j=i+1;
                        index++;
                    }
                    
                }
                //System.out.println(temp.name + " " + temp.country + " " + temp.age + " " + temp.club + " " + temp.height + " " + temp.salary + " " + temp.jersey_no + " " + temp.position);
                playerList.add(temp);
                
            }
            me.close();
        } catch(Exception ex){
            System.out.println("error 3");
            ex.printStackTrace();
        }
    }
    public static void printlist(){
    for(int i=0;i<playerList.size();i++)
    {
        //System.out.println(playerList.get(i).name + " " + playerList.get(i).country + " " + playerList.get(i).age + " " + playerList.get(i).club + " " + playerList.get(i).height + " " + playerList.get(i).salary + " " + playerList.get(i).jersey_no + " " + playerList.get(i).position);
        playerList.get(i).printplayer();
    }
    }

    public static List<Player> getlist()
    {
        return playerList;
    }

    public static void  addplayer()
    {
        Scanner t = new Scanner(System.in);
        System.out.println("Enter player name");

        String a = t.nextLine();
        boolean q=PlayerList.checklist(a);
        if(!q)
        {
            System.out.println("Player with name already exists");
            return;
        }

        System.out.println("Enter country");
        String b = t.nextLine();
        System.out.println("Enter player age");
        int c = t.nextInt();
        t.nextLine();
        System.out.println("Enter player height");
        double d = t.nextDouble();
        t.nextLine();
        System.out.println("Enter club name");
        String e = t.nextLine();
        System.out.println("Enter position of player");
        String f = t.nextLine();
        System.out.println("Enter jersey number");
        String g = t.nextLine();
        int g_no=0;
        if(g != "" ) g_no = Integer.parseInt(g);
        System.out.println("Enter salary");
        long h = t.nextInt();
        t.nextLine();

        Player temp = new Player(a, b, c, d, e, f, g_no, h);
        playerList.add(temp);

    }

    public static void addplayer(Player a)
    {
        playerList.add(a);
        printlist();
    }


    public static void printvector(Vector<Player> v)
    {
        System.out.println("Number of players found : " + v.size());
        int index=1;
        for(int i=0;i<v.size();i++) 
        {
            System.out.println("Player " + index++);
            v.get(i).printplayer();
        }
    }
    static boolean  checklist(String a)
    {
        for(int i=0;i<playerList.size();i++)
        {
            if(playerList.get(i).getName().equalsIgnoreCase(a)) return false;
        }
        return true;
    }


    public static void printlist(List<Player> p){
        for(int i=0;i<p.size();i++)
        {
            //System.out.println(playerList.get(i).name + " " + playerList.get(i).country + " " + playerList.get(i).age + " " + playerList.get(i).club + " " + playerList.get(i).height + " " + playerList.get(i).salary + " " + playerList.get(i).jersey_no + " " + playerList.get(i).position);
            playerList.get(i).printplayer();
        }
   
}

    public static ArrayList<String> getClubNames() {
        // Use HashSet to store unique club names
        Set<String> sp = new HashSet<>();

        // Iterate through the players and add their club names to the Set
        for (int i = 0; i < playerList.size(); i++) {
            sp.add(playerList.get(i).getClub());
        }

        // Convert the Set to an ArrayList
        return new ArrayList<>(sp);
    }
}
