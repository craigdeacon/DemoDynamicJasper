package com.Database.DAO.Employee;

import com.Report.Component.CS.EmployeeCostBreakdown.container.Employee;
import com.Database.DAO.Employee.EmployeeDAO;

import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
public class EmployeeCostBreakdownTestRepo implements EmployeeDAO
{

    /**
     *
     * @param groupId
     * @return
     */
    @Override
    public ArrayList<Employee> getEmployeeCostBreakdownList(Integer groupId)
    {
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add( new Employee("Mitchell Ungar", new Float ("11.79"), new Float ("3.51"), new Float ("1.62"), null, new Float ("52.53"),
                          new Float ("7.85"), new Float ("5.41"), new Float ("201.64"), new Float ("189.11"), null, null, new Float ("4.50"),
                          new Float ("43.50")));
        employeeList.add( new Employee("Craig Deacon", new Float ("12.90"), new Float ("4.12"), new Float ("8.19"), new Float ("20.80"), null,
                          new Float ("6.25"), new Float ("4.91"), new Float ("225.91"), null, new Float ("7.66"), null, new Float ("4.15"),
                          new Float ("38.90")));
        return employeeList;
    }
}