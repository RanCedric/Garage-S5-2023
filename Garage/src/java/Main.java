
import java.util.ArrayList;
import service.Service_General;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cédric
 */
public class Main {
    public static void main (String args[]){
        Service_General sg = new Service_General();
        
        ArrayList<String[]> liste_points = sg.getAllSexe();
        for(int i=0;i<liste_points.size();i++){
            System.out.println(liste_points.get(i)[0]);
            System.out.println(liste_points.get(i)[1]);
        }
    }
}
