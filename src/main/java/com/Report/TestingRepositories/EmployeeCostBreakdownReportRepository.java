/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.TestingRepositories;

import com.Database.BO.EmployeeBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Report.Component.CS.EmployeeCostBreakdown.container.Employee;
import com.Report.Component.CS.EmployeeCostBreakdown.container.Group;

import java.util.List;

/**
 * Dummy information for Employee Report
 *
 * @author craig.deacon
 */
public class EmployeeCostBreakdownReportRepository
{

    private EmployeeBO employeeBO;
    public EmployeeCostBreakdownReportRepository()
    {
        this.employeeBO = SpringConfigurationBootstrap.getApplicationContext().getBean(EmployeeBO.class);
    }

    /**
     *
     * @return
     */
    public List<Employee> getEmployeeList()
    {

        return employeeBO.getAllEmployees(3423081);
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
