package controllers;

import java.io.IOException;
import java.util.ArrayList;

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
		
		String selected = null;
		
//		String selected = (String) request.getAttribute("selected");
//		int category = (int) request.getAttribute("cat");
//		int ruleType = (int) request.getAttribute("brt");
//		int entity = (int) request.getAttribute("ent");
		
		
		if (selected != null){
			if (selected.equals("true")) {
				// get businessrules id + name selected from apex
				ArrayList<String> rules = new ArrayList<>();
				
				for (BusinessRule br : ibr.getSelectedBusinessRules(0, 0)) {
					String s = br.getId() + "," + br.getName();
					rules.add(s);
				}
				request.setAttribute("rules", rules);
			}
		} else {
			// get all businessrules id + name
			ArrayList<String> rules = new ArrayList<>();
			for (BusinessRule br : ibr.getAllBusinessRules()) {
				String s = br.getId() + "," + br.getName();
				rules.add(s);
			}
			request.setAttribute("rules", rules);
		}

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
			ruletypes.add(s);
		}
		request.setAttribute("entities", entities);
		
		
		request.setAttribute("output", OutputFactory.getOutputTypes());
		
		request.setAttribute("languages", OutputFactory.getOutputTypes());

		RequestDispatcher rd = request.getRequestDispatcher("selector.jsp");
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
