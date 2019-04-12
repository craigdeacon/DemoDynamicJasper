/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.BO;

import com.Database.DAO.GstHstByRevenueTypeDAO.GstHstByRevenueTypeDAO;
import com.Database.DAO.GstHstByRevenueTypeDAO.GstHstByRevenueTypeDAOImpl;
import com.Report.Component.ME.GstHstByRevenueTypeReport.container.GstHstByRevenueTypeGroup;
import com.Report.TestingRepositories.GstHstByRevenueTypeReportRepository;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author craig.deacon
 */
@Service
public class GstHstRevenueByTypeBO
{
    private static final Logger LOGGER = Logger.getLogger(EAPReportBO.class.getName() );
    private GstHstByRevenueTypeDAO gstHstByRevenueTypeDAO;
    
    @Autowired
    public GstHstRevenueByTypeBO(GstHstByRevenueTypeDAOImpl gstHstByRevenueTypeDAO)
    {
        this.gstHstByRevenueTypeDAO = gstHstByRevenueTypeDAO;
    }
    
    public ArrayList<GstHstByRevenueTypeGroup> getGstHstByRevenueTypeGroupList()
    {
        return gstHstByRevenueTypeDAO.getGstHstByRevenueTypeList();
    }
}
