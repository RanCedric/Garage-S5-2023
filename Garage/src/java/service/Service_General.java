/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import model.Poste;
import model.Sexe;

/**
 *
 * @author itu
 */
public class Service_General
{
    
    public ArrayList<String[]> getAllSexe()
    {
        Sexe sexe=new Sexe();
        ArrayList<String[]> s=sexe.select();
        return s;
    }
    
    public ArrayList<String[]> getAllPoste()
    {
        Poste poste=new Poste();
        ArrayList<String[]> p=poste.select();
        return p;
    }
}
