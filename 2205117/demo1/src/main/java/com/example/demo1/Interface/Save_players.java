package com.example.demo1.Interface;
//package Players;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import Database.Player;
//import Database.PlayerList;

import com.example.demo1.Database.PlayerList;
import com.example.demo1.Database.Search;
import com.example.demo1.Clubs.Club;
import com.example.demo1.Clubs.Club_search;

import com.example.demo1.Database.Player;
public class Save_players {
    
    


    public static void save(){
        //System.out.println("in save function\n");
        List<Player> real = PlayerList.getlist();

        //for(int i=0;i<real.size();i++) real.get(i).printplayer();
    
    
        Set<String> player_names = new HashSet<>();
    ArrayList<Player> initial = new ArrayList<>();
    try{
        //BufferedReader me = new BufferedReader(new FileReader("players.txt"));
        BufferedReader me = new BufferedReader(new InputStreamReader(
        Main.class.getResourceAsStream("C:/Users/Hp/Downloads/2205117 (6)/2205117/demo1/src/main/java/com/example/demo1/Database/players.txt")
        )   );
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
                                    try{
                                        int r = Integer.parseInt(s.substring(j, i));
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
                //temp.printplayer();
                initial.add(temp);
                player_names.add(temp.getName());
                //player_number++;
                //System.out.println("no of players is " + player_number);
            }
            me.close();
        } catch(Exception ex){
            System.out.println("error 1");
        ex.printStackTrace();
        }
        //initial has old players
        //playerlist has new players


        try(FileWriter writer = new FileWriter("D:/test1/src/main/java/com/example/test1/players.txt"))
        {
            
            

            for(int i=0;i<real.size();i++)
            {
                    //if(player_names.contains(initial.get(i).name)){}
                    int j=0;
                    int present=0;
                    for(j=0;j<initial.size();j++){
                        if(real.get(i).getName().equals(initial.get(j).getName())){present=1;}
                    }
                    if(present==0)
                    {
                            //real.get(i).printplayer();
                            writer.write("\n");
                            writer.write(real.get(i).getName() + ",");
                            writer.write(real.get(i).getCountry()+ ",");
                            writer.write(real.get(i).getAge() + ",");
                            writer.write(real.get(i).getHeight() + ",");
                            writer.write(real.get(i).getClub() + ",");
                            writer.write(real.get(i).getPosition() + ",");
                            if(real.get(i).getJersey_no() !=0)
                            writer.write(real.get(i).getJersey_no() + ",");
                            else 
                            writer.write(",");
                            writer.write(real.get(i).getSalary() +"");
                    }
                    
                
            }
        }
        catch(IOException ex){
            System.out.println("error 2");
        }

    }

}



//  int i = 0;
    //  try (FileWriter me = new FileWriter("players.txt"))
    // {
    //     while (i<10)
    //     {
    //         System.out.println("in loop\n");
    //         real.get(i).printplayer();
    //         me.write(real.get(i).name + ",");
    //         me.write(real.get(i).country + ",");
    //         me.write(real.get(i).age + ",");
    //         me.write(real.get(i).height + ",");
    //         me.write(real.get(i).club + ",");
    //         me.write(real.get(i).position + ",");
    //         if(real.get(i).jersey_no ==0)
    //         me.write(real.get(i).jersey_no + ",");
    //         else 
    //         me.write(",");
    //         me.write(real.get(i).salary + "\n");

    //         me.flush();
    //         i++;
           
    //     }
        

    // }catch (IOException ex) {
    // System.out.println("Error writing to file: " + ex.getMessage());
    // }

    


