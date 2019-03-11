/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.TestReportComponent.secondReport.repository;

import com.Component.TestReportComponent.secondReport.container.*;
import com.Report.Component.TestReportComponent.secondReport.container.Employee;
import com.Report.Component.TestReportComponent.secondReport.container.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy information for Employee Report
 *
 * @author craig.deacon
 */
public class EmployeeReportRepository
{

    /**
     *
     * @return
     */
    public static List<Employee> getEmployeeList()
    {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add( new Employee("Mitchell Ungar", new Float ("11.79"), new Float ("3.51"), new Float ("1.62"), null, new Float ("52.53"), 
                          new Float ("7.85"), new Float ("5.41"), new Float ("201.64"), new Float ("189.11"), null, null, new Float ("4.50"),
                          new Float ("43.50")));
        employeeList.add( new Employee("Craig Deacon", new Float ("12.90"), new Float ("4.12"), new Float ("8.19"), new Float ("20.80"), null, 
                          new Float ("6.25"), new Float ("4.91"), new Float ("225.91"), null, new Float ("7.66"), null, new Float ("4.15"),
                          new Float ("38.90")));
        return employeeList;
    }
    
    /**
     *
     * @return
     */
    public static String getGroupName()
    {
        Group group = new Group ("Benefits by Design");
        return group.getGroupName();
    }

}
