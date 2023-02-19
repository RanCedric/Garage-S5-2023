/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author aris
 */
public class Report extends DatabaseObject{
    int report_id;
    double montant_report;
    Date date_report;

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public double getMontant_report() {
        return montant_report;
    }

    public void setMontant_report(double montant_report) {
        this.montant_report = montant_report;
    }

    public Date getDate_report() {
        return date_report;
    }

    public void setDate_report(Date date_report) {
        this.date_report = date_report;
    }
    
    public static ArrayList<Report> getFromDatabaseFrom(Date date, Connection c) throws Exception {
            date = date == null ? new Date(System.currentTimeMillis()) : date;

            String query = "SELECT * "
                            + "FROM report "
                            + "WHERE date_report <= ? order by date_report";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setDate(1, date);

            ArrayList<Report> etatStockDate = new ArrayList<>();
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                    Report report = new Report();

                    report.setDate_report(res.getDate("date_report"));
                    report.setReport_id(res.getInt("report_id"));
                    report.setMontant_report(res.getDouble("montant_report"));
                    etatStockDate.add(report);
            }

            return etatStockDate;
    }
}
