/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.TestReportComponent.firstReport.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author craig.deacon
 */
class Statistic
{
    private Date date;
    private String name;
    private float percentage;
    private float average;
    private float amount;
    
    /**
     *
     */
    private static List<Dummy> dummyList = new ArrayList<>();
    
    static {
        dummyList.add(new Dummy ("Jasper", 1L));
        dummyList.add(new Dummy ("Socrates", 2L));
        dummyList.add(new Dummy ("Billy", 3L));
        dummyList.add(new Dummy ("Freud", 4L));
    }
    
    /**
     *
     */
    public Statistic()
    {
        super();
    }
  
    /**
     *
     * @param date
     * @param name
     * @param percentage
     * @param average
     * @param amount
     */
    public Statistic (Date date, String name, float percentage, float average, float amount)
    {
        super();
        this.date = date;
        this.name = name;
        this.percentage = percentage;
        this.average = average;
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public Date getDate()
    {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate( Date date )
    {
        this.date = date;
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
    public float getPercentage()
    {
        return percentage;
    }

    /**
     *
     * @param percentage
     */
    public void setPercentage( float percentage )
    {
        this.percentage = percentage;
    }

    /**
     *
     * @return
     */
    public float getAverage()
    {
        return average;
    }

    /**
     *
     * @param average
     */
    public void setAverage( float average )
    {
        this.average = average;
    }

    /**
     *
     * @return
     */
    public float getAmount()
    {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount( float amount )
    {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public static List<Dummy> getDummyList()
    {
        return dummyList;
    }

    /**
     *
     * @param dummyList
     */
    public static void setDummyList( List<Dummy> dummyList )
    {
        Statistic.dummyList = dummyList;
    }
    
    

}
