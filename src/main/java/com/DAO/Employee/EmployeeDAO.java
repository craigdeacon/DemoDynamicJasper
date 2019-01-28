package com.DAO.Employee;

import com.Component.CS.EmployeeCostBreakdown.container.Employee;

import java.util.ArrayList;

public interface EmployeeDAO
{
    ArrayList<Employee> getAllEmployees(Integer groupId);
}
