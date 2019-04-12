/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.TestingRepositories;

import com.Database.DAO.GstHstByRevenueTypeDAO.GstHstByRevenueTypeDAO;
import com.Report.Component.ME.GstHstByRevenueTypeReport.container.GstHstByRevenueTypeGroup;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author craig.deacon
 */
@Component
public class GstHstByRevenueTypeReportRepository implements GstHstByRevenueTypeDAO
{

    @Override
    public ArrayList<GstHstByRevenueTypeGroup> getGstHstByRevenueTypeList()
    {
        ArrayList<GstHstByRevenueTypeGroup> gstHstByRevenueTypeList = new ArrayList<>();
        
        gstHstByRevenueTypeList.add( new GstHstByRevenueTypeGroup( "The Avengers", "ON", 143.87, 19.32, 0., 0., 0., 0., 19.32));
        gstHstByRevenueTypeList.add( new GstHstByRevenueTypeGroup( "Fantastic Four", "ON", 94.32, 13.93, 0., 0., 0., 0., 13.93));
        gstHstByRevenueTypeList.add( new GstHstByRevenueTypeGroup( "West Coast Avengers", "BC", 0., 0., 216.15, 86.16, 0., 0., 86.16));
        gstHstByRevenueTypeList.add( new GstHstByRevenueTypeGroup( "Alpha Force", "BC", 0., 0., 32.95, 4.15, 0., 0., 4.15));
        gstHstByRevenueTypeList.add( new GstHstByRevenueTypeGroup( "Bionic 6", "SK", 0., 0., 0., 0., 18.57, 1.12, 1.12));
        gstHstByRevenueTypeList.add( new GstHstByRevenueTypeGroup( "Masters of the Universe", "SK", 0., 0., 0., 0., 68.45, 4.89, 4.89));
        
        return gstHstByRevenueTypeList;
    }
    
}
