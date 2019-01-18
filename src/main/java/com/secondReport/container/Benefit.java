/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secondReport.container;

/**
 * Entity for ConcatenatedReport
 * @author craig.deacon
 */
public class Benefit
{
    private String benefitName;
    private String supplier;
    private String policyNo;
    private String date;

    public Benefit( String benefitName, String supplier, String policyNo, String date )
    {
        this.benefitName = benefitName;
        this.supplier = supplier;
        this.policyNo = policyNo;
        this.date = date;
    }

    public String getBenefitName()
    {
        return benefitName;
    }

    public void setBenefitName( String benefitName )
    {
        this.benefitName = benefitName;
    }

    public String getSupplier()
    {
        return supplier;
    }

    public void setSupplier( String supplier )
    {
        this.supplier = supplier;
    }

    public String getPolicyNo()
    {
        return policyNo;
    }

    public void setPolicyNo( String policyNo )
    {
        this.policyNo = policyNo;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate( String date )
    {
        this.date = date;
    }
   
    
}
