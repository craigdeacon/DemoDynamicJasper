/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.PrintCertificate.container;

import java.util.ArrayList;
import java.util.List;

/**
 * First attempt at data for PrintCertificateReport, not currently used
 * @author craig.deacon
 */
public class ConcatEmployee
{
    private String name;
    private String group;
    private String employeeClass;
    private String certNo;
    private List<Benefit> benefitList;

    /**
     *
     * @param name
     * @param group
     * @param employeeClass
     * @param certNo
     */
    public ConcatEmployee( String name, String group, String employeeClass, String certNo )
    {
        this.name = name;
        this.group = group;
        this.employeeClass = employeeClass;
        this.certNo = certNo;
        this.benefitList = new ArrayList<>();
    }

    /**
     *
     * @param benefit
     */
    public void addBenefit(Benefit benefit)
    {
        this.benefitList.add( benefit );
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getGroup()
    {
        return group;
    }

    /**
     *
     * @param group
     */
    public void setGroup( String group )
    {
        this.group = group;
    }

    /**
     *
     * @return
     */
    public String getEmployeeClass()
    {
        return employeeClass;
    }

    /**
     *
     * @param employeeClass
     */
    public void setEmployeeClass( String employeeClass )
    {
        this.employeeClass = employeeClass;
    }

    /**
     *
     * @return
     */
    public String getCertNo()
    {
        return certNo;
    }

    /**
     *
     * @param certNo
     */
    public void setCertNo( String certNo )
    {
        this.certNo = certNo;
    }

    /**
     *
     * @return
     */
    public List<Benefit> getBenefitList()
    {
        return benefitList;
    }

    /**
     *
     * @param benefitList
     */
    public void setBenefitList( List<Benefit> benefitList )
    {
        this.benefitList = benefitList;
    }
    
    
    
}
