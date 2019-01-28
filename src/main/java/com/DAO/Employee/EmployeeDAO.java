package com.DAO.Employee;

import com.Component.TestReportComponent.secondReport.container.Employee;

import java.util.ArrayList;

public interface EmployeeDAO
{
    ArrayList<Employee> getAllEmployees(Integer groupId);
}
