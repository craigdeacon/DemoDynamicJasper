package com.BO;

import com.Component.CS.EmployeeCostBreakdown.container.Employee;
import com.DAO.Employee.EmployeeDAO;
import com.DAO.Employee.EmployeeDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
@Service
public class EmployeeBO
{
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeBO(EmployeeDAOImpl employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }

    /**
     *
     * @param groupId
     * @return
     */
    public ArrayList<Employee> getAllEmployees(Integer groupId)
    {
        return employeeDAO.getEmployeeCostBreakdownList(groupId);
    }
}
