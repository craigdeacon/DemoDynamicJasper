/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.operationjasper.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author craig.deacon
 */
public class Statistic
{
    private Date date;
    private String name;
    private float percentage;
    private float average;
    private float amount;
    
    public static List<Dummy> dummyList = new ArrayList<>();
    
    static {
        dummyList.add(new Dummy ("Jasper", 1L));
        dummyList.add(new Dummy ("Socrates", 2L));
        dummyList.add(new Dummy ("Billy", 3L));
        dummyList.add(new Dummy ("Freud", 4L));
    }
    
    public Statistic()
    {
        super();
    }
  
    public Statistic (Date date, String name, float percentage, float average, float amount)
    {
        super();
        this.date = date;
        this.name = name;
        this.percentage = percentage;
        this.average = average;
        this.amount = amount;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public float getPercentage()
    {
        return percentage;
    }

    public void setPercentage( float percentage )
    {
        this.percentage = percentage;
    }

    public float getAverage()
    {
        return average;
    }

    public void setAverage( float average )
    {
        this.average = average;
    }

    public float getAmount()
    {
        return amount;
    }

    public void setAmount( float amount )
    {
        this.amount = amount;
    }

    public static List<Dummy> getDummyList()
    {
        return dummyList;
    }

    public static void setDummyList( List<Dummy> dummyList )
    {
        Statistic.dummyList = dummyList;
    }
    
    

}
