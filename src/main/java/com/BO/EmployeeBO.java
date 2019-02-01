package com.BO;

import com.Component.CS.EmployeeCostBreakdown.container.Employee;
import com.DAO.Employee.EmployeeDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class EmployeeBO
{
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
    public ArrayList<Employee> getAllEmployees(Integer groupId)
    {
        return employeeDAO.getEmployeeCostBreakdownList(groupId);
    }
}
