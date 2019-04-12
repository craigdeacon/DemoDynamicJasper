/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.DAO.GstHstByRevenueTypeDAO;

import com.Report.Component.ME.GstHstByRevenueTypeReport.container.GstHstByRevenueTypeGroup;
import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
public interface GstHstByRevenueTypeDAO
{
    public ArrayList<GstHstByRevenueTypeGroup> getGstHstByRevenueTypeList();
}
