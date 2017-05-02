/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemporaryView;

/**
 *
 * @author Miltron
 */
public class WebSiteUnderConstructionPrinter {
    public String getConstructionPage (String contextPath){
        StringBuilder pageContent = new StringBuilder();
        pageContent.append("<!DOCTYPE html>");
            pageContent.append("<html>");          
            pageContent.append("<head>");
            pageContent.append("<title>Servlet MainServlet</title>");            
            pageContent.append("</head>");
            pageContent.append("<body>");
            pageContent.append("<h1>Servlet MainServlet at " + contextPath + "</h1>");
            pageContent.append("Hey i've changed this.");
            pageContent.append("</body>");
            pageContent.append("</html>");
            return pageContent.toString();
    }
   
}

