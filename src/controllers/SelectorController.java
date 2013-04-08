package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import output.OutputFactory;
import databaseControl.ImportBusinessRules;
import domain.BusinessRule;
import domain.BusinessRuleType;
import domain.Category;
import domain.Entity;

public class SelectorController extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ImportBusinessRules ibr = new ImportBusinessRules();
		
		String brsids = this.convertStringParam(request.getParameter("P9_BRSIDS"));
		System.out.println(brsids);
		List<String> brsidsList = new ArrayList();
		if (brsids.isEmpty() == false){
			brsidsList = Arrays.asList(brsids.split(","));
		}
		int category = this.convertIntParam(request.getParameter("cat"));
		String ruleType = this.convertStringParam(request.getParameter("brt"));
		int entity = this.convertIntParam(request.getParameter("ent"));
		
		// get all businessrules id + name
		ArrayList<String> rules = new ArrayList<>();
		for (BusinessRule br : ibr.getAllBusinessRules(category, ruleType, entity)) {				
			String s = br.getId() + "," + br.getName();
			if (!brsidsList.isEmpty()) {
				if(brsidsList.contains(br.getId().toString())) {
					rules.add(s);
				}
			} else {
				rules.add(s);
			}
		}
		request.setAttribute("rules", rules);

		// get all categories id + name
		ArrayList<String> categories = new ArrayList<>();
		for (Category cat : ibr.getCategories()) {
			String s = cat.getId() + "," + cat.getName();
			categories.add(s);
		}
		request.setAttribute("categories", categories);

		// get all businessruletypes code + name
		ArrayList<String> ruletypes = new ArrayList<>();
		for (BusinessRuleType brt : ibr.getBusinessRuleTypes()) {
			String s = brt.getCode() + "," + brt.getName();
			ruletypes.add(s);
		}
		request.setAttribute("ruletypes", ruletypes);
		
		// get all entities id + name
		ArrayList<String> entities = new ArrayList<>();
		for (Entity ent : ibr.getEntities()) {
			String s = ent.getId() + "," + ent.getTableName();
			entities.add(s);
		}
		request.setAttribute("entities", entities);
		
		
		request.setAttribute("output", OutputFactory.getOutputTypes());
		
		request.setAttribute("languages", OutputFactory.getOutputTypes());

		RequestDispatcher rd = request.getRequestDispatcher("selector.jsp");
		rd.forward(request, response);
		
	}
	
	private String convertStringParam(Object o) {
		String s = "";
		if (o != null) {
			s = (String) o;
		}
		return s;
	}
	private int convertIntParam(Object o) {
		int i = 0;
		if (o != null) {
			i = Integer.parseInt((String) o);
		}
		return i;
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
