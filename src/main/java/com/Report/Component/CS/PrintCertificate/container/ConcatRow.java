/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.PrintCertificate.container;

/**
 * My attempt at arranging data vertically rather than horizontally
 * 
 * @author craig.deacon
 */
public class ConcatRow
{
    private String column1;
    private String column2;
    private String name;

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
    private String benefit;
    private String supplier;
    private String policyNo;
    private String date;

    /**
     *
     * @param column1
     * @param column2
     * @param name
     */
    public ConcatRow( String column1, String column2, String name )
    {
        this.column1 = column1;
        this.column2 = column2;
        this.name = name;
    }

    /**
     *
     * @param benefit
     * @param supplier
     * @param policyNo
     * @param date
     */
    public ConcatRow( String benefit, String supplier, String policyNo, String date )
    {
        this.benefit = benefit;
        this.supplier = supplier;
        this.policyNo = policyNo;
        this.date = date;
        this.name = "Christine";
    }

    /**
     *
     * @return
     */
    public String getBenefit()
    {
        return benefit;
    }

    /**
     *
     * @param benefitName
     */
    public void setBenefit( String benefitName )
    {
        this.benefit = benefitName;
    }

    /**
     *
     * @return
     */
    public String getSupplier()
    {
        return supplier;
    }

    /**
     *
     * @param supplier
     */
    public void setSupplier( String supplier )
    {
        this.supplier = supplier;
    }

    /**
     *
     * @return
     */
    public String getPolicyNo()
    {
        return policyNo;
    }

    /**
     *
     * @param policyNo
     */
    public void setPolicyNo( String policyNo )
    {
        this.policyNo = policyNo;
    }

    /**
     *
     * @return
     */
    public String getDate()
    {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate( String date )
    {
        this.date = date;
    }
    
    /**
     *
     * @return
     */
    public String getColumn1()
    {
        return column1;
    }

    /**
     *
     * @param column1
     */
    public void setColumn1( String column1 )
    {
        this.column1 = column1;
    }

    /**
     *
     * @return
     */
    public String getColumn2()
    {
        return column2;
    }

    /**
     *
     * @param column2
     */
    public void setColumn2( String column2 )
    {
        this.column2 = column2;
    }
    
    
}
