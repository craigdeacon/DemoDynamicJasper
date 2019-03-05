package com.BO;

import com.Component.ME.EAPReport.container.EapGroup;
import com.DAO.EAPReport.EAPReportDAO;
import com.Component.ME.EAPReport.repository.EAPReportTestRepo;
import com.DemoDynamicJasper.spring.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.logging.Logger;

public class EAPReportBOTest
{
    private static final Logger LOGGER = Logger.getLogger(EAPReportBOTest.class.getName());
    @Test
    public void getGroupListTest()
    {
        LOGGER.info("getGroupListTest");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class );
        EAPReportDAO eapReportDAO = context.getBean(EAPReportTestRepo.class );

        //Arete List
        ArrayList<EapGroup> list = eapReportDAO.getGroupList(93064, "Arete");

        for(EapGroup group:list)
        {
            LOGGER.info(group.toString());
        }

        //HumanaCare List
        list = eapReportDAO.getGroupList(601076, "HumanaCare");
        for(EapGroup group:list)
        {
            LOGGER.info(group.toString());
        }
    }
}
