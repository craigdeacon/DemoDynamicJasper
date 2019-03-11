package com.Database.BO;

import com.Report.Component.CS.EmployeeCostBreakdown.container.Employee;
import com.Database.DAO.Employee.EmployeeDAO;
import com.Database.DAO.Employee.EmployeeDAOImpl;
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
