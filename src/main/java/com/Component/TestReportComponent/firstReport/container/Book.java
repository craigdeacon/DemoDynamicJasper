/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.TestReportComponent.firstReport.container;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author craig.deacon
 */
public class Book
{
    private Long id;
    private String productLine;
    private String item;
    private String state;
    private String branch;
    private Long quantity;
    private Float amount;
    
    private Boolean showAsPrices;
    
    /**
     *
     */
    public static List<Statistic> statistics_ = new ArrayList<Statistic>();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private Code code = new Code();
    
    /**
     *
     */
    public static String[] images = {"confused.gif", "cool.gif", "happy.gif", "puaj.gif", "ungry.gif", "what.gif"};
    static int counter = 0;
    static 
    {
        try {
            statistics_.add(new Statistic(formatter.parse("01/01/2003"), "West", 14.3f, 50.4f, 43.1f));
            statistics_.add(new Statistic(formatter.parse("01/01/2004"),"West",40.0f,49.4f,44.5f));
            statistics_.add(new Statistic(formatter.parse("01/01/2005"),"North",33.3f,63.4f,45f));
            statistics_.add(new Statistic(formatter.parse("01/01/2006"),"East",91.1f,34.4f,46f));
            statistics_.add(new Statistic(formatter.parse("01/01/2007"),"South",99.3f,52.4f,47f));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @param productLine
     * @param item
     * @param state
     * @param branch
     * @param quantity
     * @param amount
     * @param showAsPrices
     */
    public Book( Long id, String productLine, String item, String state, String branch, Long quantity, Float amount, Boolean showAsPrices)
    {
        this(id, productLine, item, state, branch, quantity, amount);
        this.showAsPrices = showAsPrices;
    }

    /**
     *
     * @param id
     * @param productLine
     * @param item
     * @param state
     * @param branch
     * @param quantity
     * @param amount
     */
    public Book( Long id, String productLine, String item, String state, String branch, Long quantity, Float amount )
    {
        this.id = id;
        this.productLine = productLine;
        this.item = item;
        this.state = state;
        this.branch = branch;
        this.quantity = quantity;
        this.amount = amount;
    }
    
    /**
     *
     * @return
     */
    public Long getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId( Long id )
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getProductLine()
    {
        return productLine;
    }

    /**
     *
     * @param productLine
     */
    public void setProductLine( String productLine )
    {
        this.productLine = productLine;
    }

    /**
     *
     * @return
     */
    public String getItem()
    {
        return item;
    }

    /**
     *
     * @param item
     */
    public void setItem( String item )
    {
        this.item = item;
    }

    /**
     *
     * @return
     */
    public String getState()
    {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState( String state )
    {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getBranch()
    {
        return branch;
    }

    /**
     *
     * @param branch
     */
    public void setBranch( String branch )
    {
        this.branch = branch;
    }

    /**
     *
     * @return
     */
    public Long getQuantity()
    {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity( Long quantity )
    {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public Float getAmount()
    {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount( Float amount )
    {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public Boolean getShowAsPrices()
    {
        return showAsPrices;
    }

    /**
     *
     * @param showAsPrices
     */
    public void setShowAsPrices( Boolean showAsPrices )
    {
        this.showAsPrices = showAsPrices;
    }

    /**
     *
     * @return
     */
    public static List<Statistic> getStatistics_()
    {
        return statistics_;
    }

    /**
     *
     * @param statistics_
     */
    public static void setStatistics_( List<Statistic> statistics_ )
    {
        Book.statistics_ = statistics_;
    }

    /**
     *
     * @return
     */
    public static SimpleDateFormat getFormatter()
    {
        return formatter;
    }

    /**
     *
     * @param formatter
     */
    public static void setFormatter( SimpleDateFormat formatter )
    {
        Book.formatter = formatter;
    }

    /**
     *
     * @return
     */
    public static String[] getImages()
    {
        return images;
    }

    /**
     *
     * @param images
     */
    public static void setImages( String[] images )
    {
        Book.images = images;
    }

    /**
     *
     * @return
     */
    public static int getCounter()
    {
        return counter;
    }

    /**
     *
     * @param counter
     */
    public static void setCounter( int counter )
    {
        Book.counter = counter;
    }
    
    static Random random = new Random();

    /**
     *
     * @return
     */
    public InputStream getImage()
    {
        InputStream image = this.getClass().getClassLoader().getResourceAsStream( "");
        return image;
    }
    
    /**
     *
     * @return
     */
    public Code getCode()
    {
        return code;
    }
    
    /**
     *
     */
    public class Code 
    {

        /**
         *
         * @return
         */
        public String getCode()
        {
            return "001-123ABC-HRC";
        }
    }

    @Override
    public String toString()
    {
        return "Book{" + "id=" + id + ", productLine=" + productLine + ", item=" + item + ", state=" + state + ", branch=" + branch + ", quantity=" + quantity + ", amount=" + amount + ", showAsPrices=" + showAsPrices + ", code=" + code + '}';
    }
    
    
}
