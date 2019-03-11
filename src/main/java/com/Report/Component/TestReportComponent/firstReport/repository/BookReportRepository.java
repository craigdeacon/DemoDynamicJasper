/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.TestReportComponent.firstReport.repository;

import com.Report.Component.TestReportComponent.firstReport.container.Book;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author craig.deacon
 */
public class BookReportRepository
{

    /**
     *
     * @return
     */
    public static List<Book> getBookCollection()
    {
        List<Book> bookList = new ArrayList<Book>();
        bookList.add( new Book (new Long("1"), "book", "Harry Potter 7", "Florida", "Main Street", new Long ("2500"), new Float ("10000")) );
        bookList.add( new Book (new Long("2"), "book", "Harry Potter 7", "Florida", "Main Street", new Long ("2500"), new Float ("10000")) );
        bookList.add( new Book (new Long("3"), "book", "Harry Potter 7", "Florida", "Main Street", new Long ("2500"), new Float ("10000")) );
        bookList.add( new Book (new Long("4"), "book", "Harry Potter 7", "Florida", "Main Street", new Long ("2500"), new Float ("10000")) );
        return bookList;
    }
}
