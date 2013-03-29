package controllers;

import generation.Generator;
import generation.Language;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseControl.ImportBusinessRules;
import domain.BusinessRule;

public class GeneratorController extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String[] businessRules = request.getParameterValues("businessRules");
    	ImportBusinessRules ibr = new ImportBusinessRules();
    	
    	ArrayList<BusinessRule> selectedBusinessRules = new ArrayList<BusinessRule>(); 
    	for(String businessRule: businessRules){
    		selectedBusinessRules.add(ibr.getBusinessRule(Integer.parseInt(businessRule)));
    	}
    	ArrayList<Language> selectedLanguages = new ArrayList<Language>(); 
    	Generator gen = new Generator(selectedLanguages, selectedBusinessRules);
    	gen.generate();
    	
        processRequest(request, response);
    }
}
