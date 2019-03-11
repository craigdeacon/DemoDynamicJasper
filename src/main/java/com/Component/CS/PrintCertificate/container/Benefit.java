/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.PrintCertificate.container;

/**
 * Entity for PrintCertificateReport
 * @author craig.deacon
 */
public class Benefit
{
    private String benefit;
    private String supplier;
    private String policyNo;
    private String date;

    /**
     *
     * @param benefitName
     * @param supplier
     * @param policyNo
     * @param date
     */
    public Benefit( String benefitName, String supplier, String policyNo, String date )
    {
        this.benefit = benefitName;
        this.supplier = supplier;
        this.policyNo = policyNo;
        this.date = date;
        String name = "Christine";
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
   
    
}
