/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.EmployeeCostBreakdown.container;

import com.Report.Component.CS.EmployeeCostBreakdown.container.Employee;
import org.junit.*;

/**
 *
 * @author craig.deacon
 */

public class EmployeeTest
{

    /**
     *
     */
    @Test
    public void employeeTotalTest()
    {
        Employee testEmployee = new Employee("Craig Deacon", new Double ("12.90"), new Double ("4.12"), new Double ("8.19"), new Double ("20.80"), null,
                                             new Double ("6.25"), new Double ("4.91"), new Double ("225.91"), null, new Double ("7.66"), null, new Double ("4.15"),
                                             new Double ("38.90"));
        Double total = (Double) 333.79;
        Assert.assertEquals( total, testEmployee.getTotal(), .1f );
    }
}
