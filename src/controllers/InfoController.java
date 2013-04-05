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

public class InfoController extends HttpServlet {
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int businessRule = convertIntParam(request.getParameter("businessrule"));
		if(businessRule == 0){
			businessRule = convertIntParam(request.getAttribute("businessrule"));
		}
		ImportBusinessRules ibr = new ImportBusinessRules();
		System.out.println("Businessrule id: " + businessRule);
		BusinessRule br = ibr.getBusinessRule(businessRule);
		request.setAttribute("id", businessRule);
		request.setAttribute("name", br.getName());
		request.setAttribute("entity", br.getEntity().getTableName());
		request.setAttribute("attribute", br.getAttribute().getColumnName());
		request.setAttribute("operator", br.getOperator().getName());
		request.setAttribute("conditionalvalues", br.getConditionalValues());

		
		RequestDispatcher rd = request.getRequestDispatcher("info.jsp");
		rd.forward(request, response);
	}
	
	private int convertIntParam(Object o) {
		int i = 0;
		if (o != null) {
			i = Integer.parseInt((String) o);
		}
		return i;
	}

}
