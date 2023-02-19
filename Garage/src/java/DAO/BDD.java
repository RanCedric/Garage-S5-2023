/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package DAO;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Cédric
 */
public class BDD {
    Connection conn;
    PreparedStatement stmt;
    
    ArrayList<String> notSave;
    String table;
    
    public static String getBdaUrl(){
        return "jdbc:postgresql://localhost:5432/garage1";
    }
    
    public static String getBdaUser(){
        return "postgres";
    }
    
    public static String getBdaMdp(){
        return "root";
    }
    
    public void dontSave(String colm){
        if(notSave == null){
            notSave = new ArrayList<String>();
        }
        this.notSave.add(colm);
    }
    
    public void setTable(String table){
        this.table = table;
    }
    
    private String[] getField(){
        Field[] field = this.getClass().getDeclaredFields();
        String [] fieldString = new String[field.length];
        for(int i = 0;i<field.length;i++){
            fieldString[i] = field[i].getName();
        }
        return fieldString;
    }
    
    private String[][] valueOfField(){
        String[] fieldString = this.getField();
        Field[] field = this.getClass().getDeclaredFields();
        String[][] val = new String[fieldString.length][2];
        for(int i = 0;i<field.length;i++){
            try {
                field[i].setAccessible(true);
                val[i][0]= fieldString[i];
                val[i][1]= field[i].get(this).toString();
            }
            catch (Exception e) {
                ERROR("BDD.valueOfField",e);
            }
        }
        return val;
    }
    
    public void save(){
        if(notSave == null){
            notSave = new ArrayList<String>();
        }
        String[][] data = this.valueOfField();
        String requete = "insert into "+this.getClass().getSimpleName()+" ";
        String colreq = "(";
        String valreq = "(";
        for (String[] data1 : data) {
            boolean indicateur = true;
            for(int i=0;i<this.notSave.size();i++){
                if(notSave.get(i)==data1[0]){
                    indicateur = false;
                }
            }
            if(indicateur){
                colreq += ""+data1[0] + ",";
                valreq += "'"+data1[1] + "',";
            }
        }
        StringBuilder string = new StringBuilder(colreq);
        string.setCharAt(colreq.length()-1, ')');
        StringBuilder string1 = new StringBuilder(valreq);
        string1.setCharAt(valreq.length()-1, ')');
        
        requete += string +" values "+ string1;
        this.confirme(requete);
    }
    public void delete(){
        String requete = "delete from "+this.getClass().getSimpleName()+" ";
        this.confirme(requete);
    }
    
    public void getById(String id){
        String className = this.getClass().getSimpleName();
        String requete = "select * from "+className+" where "+className+"ID = '"+id+"'";
        try{
            ResultSet result = response(requete);
            Field[] field = this.getClass().getDeclaredFields();
            while(result.next()){
                for(int i = 0;i<field.length;i++){
                    field[i].setAccessible(true);
                    field[i].set(this,field[i].getType().cast(result.getString(i+1)));
                }
            }
            
        }
        catch(Exception e){
            ERROR("getById",e);
        }
    }
    
    public ArrayList<String[]> select(){
        String className = this.getClass().getSimpleName();
        String requete = "select * from "+className+" where 1=1";
        ArrayList<String[]> data = new ArrayList();
        try{
            String[] field = getField();        
            ResultSet response = response(requete);
            while(response.next()){
                String[] datacache = new String[field.length];
                for(int i=0;i<field.length;i++){
                    datacache[i]=response.getString(i+1);
                }
                data.add(datacache);
            }
        }
        catch(Exception e){
            ERROR("BDD.select",e);
        }
        return data;
    }
    public ArrayList<String[]> select(String condition){
        String requete = "select * from "+this.getClass().getSimpleName()+" where 1=1 and "+condition;
        ArrayList<String[]> data = new ArrayList();
        try{
            String[] field = getField();
            ResultSet response = response(requete);
            while(response.next()){
                String[] datacache = new String[field.length];
                for(int i=0;i<field.length;i++){
                    datacache[i]=response.getString(i+1);
                }
                data.add(datacache);
            }
        }
        catch(Exception e){
            ERROR("BDD.select",e);
        }
        return data;
    }
    
    public <T> ArrayList<T> select(Class<T> type) {
        String className = this.getClass().getSimpleName();
        String requete = "select * from " + className + " where 1=1";
        ArrayList<T> data = new ArrayList<T>();
        try {
            Field[] fields = type.getDeclaredFields();
            ResultSet response = response(requete);
            while (response.next()) {
                T obj = type.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    field.set(obj, response.getObject(i + 1));
                }
                data.add(obj);
            }
        } catch (Exception e) {
            ERROR("BDD.select(type)", e);
        }
        return data;
    }

    public <T> ArrayList<T> select(Class<T> type, String condition) {
        String className = this.getClass().getSimpleName();
        String requete = "select * from " + className + " where 1=1 and " + condition;
        ArrayList<T> data = new ArrayList<T>();
        try {
            Field[] fields = type.getDeclaredFields();
            ResultSet response = response(requete);
            while (response.next()) {
                T obj = type.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    field.set(obj, response.getObject(i + 1));
                }
                data.add(obj);
            }
        } catch (Exception e) {
            ERROR("BDD.select(type,condition)", e);
        }
        return data;
    }

    
    
    /* public ArrayList<Object> selectObject(String condition){
        String requete = "select * from "+this.getClass().getSimpleName()+" where 1=1 and "+condition;
        ArrayList<Object> data = new ArrayList();
        try{
            String[] field = getField();
            ResultSet response = response(requete);
            while(response.next()){
                Object obj = this;
                Field[] fieldd = obj.getClass().getDeclaredFields();
                for(int i=0;i<fieldd.length;i++){
                    fieldd[i].setAccessible(true);
                    fieldd[i].set(obj,fieldd[i].getType().cast(response.getString(i+1)));
                }
                Object objs = obj;
                data.add(objs);
            }
        }
        catch(Exception e){
            ERROR("BDD.selectObject",e);
        }
        return data;
    }
    */
    public String getOne(String requete){
        String ret = "0";
        try{
            ResultSet reut = response(requete);
            while(reut.next()){
                ret = reut.getString(1);
            }
        }
        catch (Exception e){}
        if(ret == null) ret = "0";
        return ret;
    }
    
    public void ERROR(String errorLocation,Exception e){
        System.out.println("");
        System.out.println("//debut");
        System.out.println("//	"+"DAO."+errorLocation);
        System.out.println("//	"+e.getMessage());
        System.out.println("//fin");
        System.out.println("");
    }
    
    public void open(){
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(getBdaUrl(),getBdaUser(),getBdaMdp());
        }
        catch(ClassNotFoundException | SQLException e){
            ERROR("BDD.open",e);
        }
    }
    
    public Connection getConnection(){
        open();
        return this.conn;
    }
    
    public void testConnection (){
        try{
            open();
            System.out.println("connection base de donne valider");
            close();
        }
        catch(Exception e){
            System.out.println("erreur de connection base de donne");
        }
    }
    
    public boolean confirme(String requete){
        boolean reponse = true;
        open();
        try {
            stmt = conn.prepareStatement(requete);
            stmt.executeQuery();
        } catch (SQLException ex) {
            ERROR("BDD.confirme",ex);
            reponse = false;
        }
        close();
        return reponse;
    }
    
    public ResultSet response(String requete) throws SQLException{
        open();
        stmt = conn.prepareStatement(requete);
        ResultSet ret = stmt.executeQuery();
        close();
        return ret;
    }
    
    public void close(){
        try {
            this.conn.close();
            this.conn = null;
            this.stmt = null;
        } catch (SQLException ex) {
            ERROR("BDD.close",ex);
        }
    }
    
    public void showInTerminal(){
        String[][] field = this.valueOfField();
        System.out.println("");
        System.out.println("//debut");
        for(int i=0;i<field.length;i++){
            System.out.println("//  "+field[i][0]+":"+field[i][1]);
        }
        System.out.println("//fin");
        System.out.println("");
        System.out.println("");
            
    }
}
