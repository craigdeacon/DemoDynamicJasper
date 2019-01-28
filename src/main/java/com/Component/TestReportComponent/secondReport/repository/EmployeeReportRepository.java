/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.TestReportComponent.secondReport.repository;

import com.BO.EmployeeBO;
import com.Component.TestReportComponent.secondReport.container.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dummy information for Employee Report
 *
 * @author craig.deacon
 */
public class EmployeeReportRepository
{
    public static List<Employee> getEmployeeList()
    {
        EmployeeBO employeeBO = new EmployeeBO();

        ArrayList<Employee> list = employeeBO.getAllEmployees(7);

        for (Employee employee:list)
        {
            System.out.println( employee.toString() );
//            employee.setTotal(employee.getTotal());
        }
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add( new Employee("Mitchell Ungar", new Double ("11.79"), new Double ("3.51"), new Double ("1.62"), null, new Double ("52.53"),
//                          new Double ("7.85"), new Double ("5.41"), new Double ("201.64"), new Double ("189.11"), null, null, new Double ("4.50"),
//                          new Double ("43.50")));
//        employeeList.add( new Employee("Craig Deacon", new Double ("12.90"), new Double ("4.12"), new Double ("8.19"), new Double ("20.80"), null,
//                          new Double ("6.25"), new Double ("4.91"), new Double ("225.91"), null, new Double ("7.66"), null, new Double ("4.15"),
//                          new Double ("38.90")));
        return list;
    }
    
    public static String getGroupName()
    {
        Group group = new Group ("Benefits by Design");
        return group.getGroupName();
    }

}
