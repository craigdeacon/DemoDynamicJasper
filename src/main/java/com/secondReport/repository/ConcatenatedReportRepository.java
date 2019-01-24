/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secondReport.repository;

import com.secondReport.container.Benefit;
import com.secondReport.container.ConcatEmployee;
import com.secondReport.container.ConcatRow;
import java.util.ArrayList;
import java.util.List;

/**
 *Repository for ConcatenatedReport
 * 
 * @author craig.deacon
 */
public class ConcatenatedReportRepository
{

    public static ConcatEmployee getEmployee()
    {
        ConcatEmployee concatEmp = new ConcatEmployee( "Beston, Christine", "Active Accounting Inc", "A - All Employees", "1608463" );
        concatEmp.addBenefit( new Benefit("Dep Life", "Empire Life", "TC008-1417", "May 1, 2003" ));
        concatEmp.addBenefit( new Benefit("LTD", "Empire Life", "TC008-1417", "May 1, 2003" ));
        concatEmp.addBenefit( new Benefit("Life", "Empire Life", "TC008-1417", "May 1, 2003" ));
        concatEmp.addBenefit( new Benefit("CI", "Industrial Alliance", "10000-7170-1417", "May 1, 2003" ));
        concatEmp.addBenefit( new Benefit("AD&D", "Industrial Alliance", "10000-8101-1417", "May 1, 2003" ));
        return concatEmp;

    }
    
    public static ArrayList<Benefit> getBenefits()
    {
        ArrayList<Benefit> benefitList = new ArrayList<>();
        benefitList.add( new Benefit("Derp Life", "Empire Life", "TC008-1417", "May 1, 2003" ));
        benefitList.add( new Benefit("LTD", "Empire Life", "TC008-1417", "May 1, 2003" ));
        benefitList.add( new Benefit("Life", "Empire Life", "TC008-1417", "May 1, 2003" ));
        benefitList.add( new Benefit("CI", "Industrial Alliance", "10000-7170-1417", "May 1, 2003" ));
        benefitList.add( new Benefit("AD&D", "Industrial Alliance", "10000-8101-1417", "May 1, 2003" ));
        return benefitList;
    }
    
    public static ArrayList<String> getEmployeeArray()
    {
        ArrayList<String> empInfo = new ArrayList<>();
        empInfo.add( "Active Accounting Inc.");
        empInfo.add( "Beston, Christine M");
        empInfo.add("A - All Employees");
        return empInfo;
    }
    
    public static ArrayList<ConcatRow> getRows()
    {
        ArrayList<ConcatRow> rows = new ArrayList<>();
        rows.add ( new ConcatRow ("Group", "Active Accounting Inc", "Christine"));
        rows.add ( new ConcatRow ("Employee", "Beston, Christine M", "Christine"));
        rows.add ( new ConcatRow ("Class", "A - All Employees", "Christine"));
        rows.add ( new ConcatRow ("Cert #", "1608463", "Christine"));
//        rows.add( new ConcatRow("Dep Life", "Empire Life", "TC008-1417", "May 1, 2003" ));
//        rows.add( new ConcatRow("LTD", "Empire Life", "TC008-1417", "May 1, 2003" ));
//        rows.add( new ConcatRow("Life", "Empire Life", "TC008-1417", "May 1, 2003" ));
//        rows.add( new ConcatRow("CI", "Industrial Alliance", "10000-7170-1417", "May 1, 2003" ));
//        rows.add( new ConcatRow("AD&D", "Industrial Alliance", "10000-8101-1417", "May 1, 2003" ));
        return rows;
    }

}
