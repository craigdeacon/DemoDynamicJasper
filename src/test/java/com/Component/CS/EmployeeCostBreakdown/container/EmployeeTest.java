/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.EmployeeCostBreakdown.container;

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
        Employee testEmployee = new Employee("Craig Deacon", new Float ("12.90"), new Float ("4.12"), new Float ("8.19"), new Float ("20.80"), null,
                                             new Float ("6.25"), new Float ("4.91"), new Float ("225.91"), null, new Float ("7.66"), null, new Float ("4.15"),
                                             new Float ("38.90"));
        float total = (float) 333.79;
        Assert.assertEquals( total, testEmployee.getTotal(), .1f );
    }
}
