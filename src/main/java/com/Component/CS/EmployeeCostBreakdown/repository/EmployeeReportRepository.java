/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.EmployeeCostBreakdown.repository;

import com.BO.EmployeeBO;
import com.Component.CS.EmployeeCostBreakdown.container.*;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;

import java.util.List;

/**
 * Dummy information for Employee Report
 *
 * @author craig.deacon
 */
public class EmployeeReportRepository
{

    private EmployeeBO employeeBO;
    public EmployeeReportRepository()
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
