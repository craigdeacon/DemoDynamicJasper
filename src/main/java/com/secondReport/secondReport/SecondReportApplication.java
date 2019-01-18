package com.secondReport.secondReport;

import ar.com.fdvs.dj.domain.DynamicReport;
import com.secondReport.entity.ConcatenatedReport;
import com.secondReport.entity.EmployeeReport;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author craig.deacon
 */
public class SecondReportApplication
{



	public static void main(String[] args)
        {

                //		SpringApplication.run(JasperreportApplication.class, args);
            EmployeeReport empReport = new EmployeeReport();
            empReport.displayEmployeeReport();

//            ConcatenatedReport concateReport = new ConcatenatedReport();
//            concateReport.displayConcatReport();
            
//            TemplateReport templateReport = new TemplateReport();
//            templateReport.displayTemplateReport();
        }
          
         
}
