/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firstReport.container;

import com.firstReport.container.Statistic;
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
    
    public static List<Statistic> statistics_ = new ArrayList<Statistic>();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private Code code = new Code();
    
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

    public Book( Long id, String productLine, String item, String state, String branch, Long quantity, Float amount, Boolean showAsPrices)
    {
        this(id, productLine, item, state, branch, quantity, amount);
        this.showAsPrices = showAsPrices;
    }

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
    
    

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getProductLine()
    {
        return productLine;
    }

    public void setProductLine( String productLine )
    {
        this.productLine = productLine;
    }

    public String getItem()
    {
        return item;
    }

    public void setItem( String item )
    {
        this.item = item;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public String getBranch()
    {
        return branch;
    }

    public void setBranch( String branch )
    {
        this.branch = branch;
    }

    public Long getQuantity()
    {
        return quantity;
    }

    public void setQuantity( Long quantity )
    {
        this.quantity = quantity;
    }

    public Float getAmount()
    {
        return amount;
    }

    public void setAmount( Float amount )
    {
        this.amount = amount;
    }

    public Boolean getShowAsPrices()
    {
        return showAsPrices;
    }

    public void setShowAsPrices( Boolean showAsPrices )
    {
        this.showAsPrices = showAsPrices;
    }

    public static List<Statistic> getStatistics_()
    {
        return statistics_;
    }

    public static void setStatistics_( List<Statistic> statistics_ )
    {
        Book.statistics_ = statistics_;
    }

    public static SimpleDateFormat getFormatter()
    {
        return formatter;
    }

    public static void setFormatter( SimpleDateFormat formatter )
    {
        Book.formatter = formatter;
    }

    public static String[] getImages()
    {
        return images;
    }

    public static void setImages( String[] images )
    {
        Book.images = images;
    }

    public static int getCounter()
    {
        return counter;
    }

    public static void setCounter( int counter )
    {
        Book.counter = counter;
    }
    
    static Random random = new Random();
    public InputStream getImage()
    {
        InputStream image = this.getClass().getClassLoader().getResourceAsStream( "");
        return image;
    }
    
    public Code getCode()
    {
        return code;
    }
    
    public class Code 
    {
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
