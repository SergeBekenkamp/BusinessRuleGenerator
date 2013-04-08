package controllers;

import generation.Generator;
import generation.Language;
import generation.TemplateLoader;

import java.io.File;
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

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] businessRules = request.getParameterValues("businessRules");
		ImportBusinessRules ibr = new ImportBusinessRules();
		String outputType = request.getParameter("output");
		String lang = request.getParameter("language");

		ArrayList<BusinessRule> selectedBusinessRules = new ArrayList<BusinessRule>();
		for (String businessRule : businessRules) {
			selectedBusinessRules.add(ibr.getBusinessRule(Integer.parseInt(businessRule)));
		}

		System.out.println(this.getServletContext().getRealPath("templates"));
		Language language = new Language(lang, this.getServletContext().getRealPath("languages"));
		String templateDir = this.getServletContext().getRealPath("templates");
		String outputLocation = this.getServletContext().getRealPath("output");
		Generator gen = new Generator(language, selectedBusinessRules, outputType, templateDir, outputLocation);
		String output = "";
		try {
			output = gen.generate();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("output", output.replace("\r\n", "<br />"));
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
