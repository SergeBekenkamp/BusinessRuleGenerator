package controllers;

import generation.Generator;
import generation.Language;
import generation.TemplateLoader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseControl.ImportBusinessRules;
import domain.BusinessRule;

public class GeneratorController extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("output", getGeneratedCode());
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
		String outputType = request.getParameter("generateOptions");

		ArrayList<BusinessRule> selectedBusinessRules = new ArrayList<BusinessRule>();
		for (String businessRule : businessRules) {
			selectedBusinessRules.add(ibr.getBusinessRule(Integer.parseInt(businessRule)));
		}
		ArrayList<Language> selectedLanguages = new ArrayList<Language>();
		System.out.println(this.getServletContext().getRealPath("templates"));
		Generator gen = new Generator(new Language("test", "test"), selectedBusinessRules, outputType, this.getServletContext().getRealPath("templates"));

		try {
			gen.generate();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		processRequest(request, response);
	}
	
	protected String getGeneratedCode(){
		TemplateLoader tl = new TemplateLoader(this.getServletContext().getRealPath("templates") + "\\output.txt");
		String s = "", s2 = "";
		while (s2 != null) {
		s2 = tl.nextLine();
		s += s2;
		}
		tl.close();
		return s;
		
	}
}
