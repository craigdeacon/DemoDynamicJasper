package com.DAO.Employee;

import com.Component.CS.EmployeeCostBreakdown.container.Employee;

import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
public interface EmployeeDAO
{

    /**
     *
     * @param groupId
     * @return
     */
    ArrayList<Employee> getEmployeeCostBreakdownList(Integer groupId);
}
