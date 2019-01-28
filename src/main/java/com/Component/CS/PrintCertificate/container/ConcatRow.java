/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.PrintCertificate.container;

/**
 * My attempt at arranging data vertically rather than horizontally
 * 
 * @author craig.deacon
 */
public class ConcatRow
{
    String column1;
    String column2;
    String name;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
    String benefit;
    String supplier;
    String policyNo;
    String date;

    public ConcatRow( String column1, String column2, String name )
    {
        this.column1 = column1;
        this.column2 = column2;
        this.name = name;
    }

    public ConcatRow( String benefit, String supplier, String policyNo, String date )
    {
        this.benefit = benefit;
        this.supplier = supplier;
        this.policyNo = policyNo;
        this.date = date;
        this.name = "Christine";
    }

    public String getBenefit()
    {
        return benefit;
    }

    public void setBenefit( String benefitName )
    {
        this.benefit = benefitName;
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
    
    

    public String getColumn1()
    {
        return column1;
    }

    public void setColumn1( String column1 )
    {
        this.column1 = column1;
    }

    public String getColumn2()
    {
        return column2;
    }

    public void setColumn2( String column2 )
    {
        this.column2 = column2;
    }
    
    
}
