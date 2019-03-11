package com.Database.DAO.Employee;

import com.Report.Component.CS.EmployeeCostBreakdown.container.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author craig.deacon
 */
public class EmployeeBOTest
{
    private Logger LOGGER = Logger.getLogger(EmployeeBOTest.class.getName());

    /**
     *
     */
    @Test
    public void getAllEmployeesTest()
    {
        LOGGER.info("getAllEmployeesTest");
        EmployeeCostBreakdownTestRepo testRepo = new EmployeeCostBreakdownTestRepo();
        /*Group Id doesn't matter since the data is controlled.*/
        ArrayList<Employee> employeeList = testRepo.getEmployeeCostBreakdownList(1234);

        /*Getting Money versions of the data.*/
        BigDecimal employee1TotalResult = new BigDecimal(employeeList.get(0).getTotal().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal employee2TotalResult = new BigDecimal(employeeList.get(1).getTotal().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);

        /*Testing Money Totals*/
        Assert.assertEquals("Employee total didn't match: " + employeeList.get(0).getName() ,employee1TotalResult.toString(),  "521.46");
        Assert.assertEquals("Employee total didn't match: " + employeeList.get(1).getName() ,employee2TotalResult.toString(), "333.79");
    }
}
