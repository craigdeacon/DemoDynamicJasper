/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.TestReportComponent.firstReport.container;

/**
 *
 * @author craig.deacon
 */
class Dummy
{
    private String name;
    private Long number;

    /**
     *
     * @param name
     * @param number
     */
    public Dummy( String name, Long number )
    {
        this.name = name;
        this.number = number;
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
    public Long getNumber()
    {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber( Long number )
    {
        this.number = number;
    }
    
    
}
