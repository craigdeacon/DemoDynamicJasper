/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secondReport.container;

import java.util.ArrayList;
import java.util.List;

/**
 * First attempt at data for ConcatenatedReport, not currently used
 * @author craig.deacon
 */
public class ConcatEmployee
{
    private String name;
    private String group;
    private String employeeClass;
    private String certNo;
    private List<Benefit> benefitList;

    public ConcatEmployee( String name, String group, String employeeClass, String certNo )
    {
        this.name = name;
        this.group = group;
        this.employeeClass = employeeClass;
        this.certNo = certNo;
        this.benefitList = new ArrayList<>();
    }

    public void addBenefit(Benefit benefit)
    {
        this.benefitList.add( benefit );
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getGroup()
    {
        return group;
    }

    public void setGroup( String group )
    {
        this.group = group;
    }

    public String getEmployeeClass()
    {
        return employeeClass;
    }

    public void setEmployeeClass( String employeeClass )
    {
        this.employeeClass = employeeClass;
    }

    public String getCertNo()
    {
        return certNo;
    }

    public void setCertNo( String certNo )
    {
        this.certNo = certNo;
    }

    public List<Benefit> getBenefitList()
    {
        return benefitList;
    }

    public void setBenefitList( List<Benefit> benefitList )
    {
        this.benefitList = benefitList;
    }
    
    
    
}
