<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp" />
<%
	ArrayList<String> rules = (ArrayList<String>) request.getAttribute("rules");
	ArrayList<String> categories = (ArrayList<String>) request.getAttribute("categories");
	ArrayList<String> entities = (ArrayList<String>) request.getAttribute("entities");
	ArrayList<String> ruletypes = (ArrayList<String>) request.getAttribute("ruletypes");
	ArrayList<String> output = (ArrayList<String>) request.getAttribute("output");
	ArrayList<String> languages = (ArrayList<String>) request.getAttribute("languages");
%>
<script language="JavaScript">
<!--
function checkAll(chkboxName) {
	var checkboxes = document.getElementsByName(chkboxName);
	for ( var i = 0; i < checkboxes.length; i++) {
		checkboxes[i].checked = true;
	}
}

function uncheckAll(chkboxName) {
	var checkboxes = document.getElementsByName(chkboxName);
	for ( var i = 0; i < checkboxes.length; i++) {
		checkboxes[i].checked=false;
  }
}
function toggle(chkBox, field) {
	if (chkBox.checked != false) {
	    for ( var i = 0; i < field.length; i++) {
	        field[i].checked = false;
	    }
	    chkBox.checked = true;
	}
}
//-->
</script>
<div id="filters">
	<h1>Filter</h1>
	<form name="filter" action="selector" method="get">
		<h2>Categories</h2>
		<% for (String s : categories) {
				String[] parts = s.split(",");
				String id = parts[0];
				String name = parts[1];
				String selected = "";
				if (request.getParameter("cat") != null) {
					if (request.getParameter("cat").equals(id)) {
						selected = "checked";
					}
				} %>
				<input type="checkbox" value="<% out.print(id); %>" name="cat" onClick="toggle(this,document.filter.cat);" <% out.print(selected); %> /><% out.print(name); %><br />
		<% } %>
		<h2>BusinessRuleTypes</h2>
		<% for (String s : ruletypes) {
				String[] parts = s.split(",");
				String code = parts[0];
				String name = parts[1];
				String selected = "";
				if (request.getParameter("brt") != null) {
					if (request.getParameter("brt").equals(code)) {
						selected = "checked";
					} 
				} %>
				<input type="checkbox" value="<% out.print(code); %>" name="brt" onClick="toggle(this,document.filter.brt);" <% out.print(selected); %> /><% out.print(name); %><br />
		<% } %>
		<h2>Entities</h2>
		<% for (String s : entities) {
				String[] parts = s.split(",");
				String id = parts[0];
				String name = parts[1];
				String selected = "";
				if (request.getParameter("ent") != null) {
					if (request.getParameter("ent").equals(id)) {
						selected = "checked";
					}
				} %>
				<input type="checkbox" value="<% out.print(id); %>" name="ent" onClick="toggle(this,document.filter.ent);" <% out.print(selected); %> /><% out.print(name); %><br />
		<% } %>
		<input type="submit" value="Filter" />
		<input type="button" value="Remove filter" onClick="window.location='selector';" />
	</form>
</div>
<div id="selection">
	<form name="selector" action="generator" method="post">
		Output: 
		<select name="output">
			<% for (String s : output) { %>
				<option value="<% out.print(s); %>"><% out.print(s); %></option>
			<% } %>
		</select><br />
		Language:
		<select name="language">
			<option value="PLSQL">PLSQL</option>
		</select><br />
		<input type="button" value="Select all" onClick="checkAll('businessRules')" />
		<input type="button" value="Select none" onClick="uncheckAll('businessRules')" />
		<div id="businessrules">
			<% for (String s : rules) {
					String[] parts = s.split(",");
					String id = parts[0];
					String name = parts[1]; %>
					<input type="checkbox" name="businessRules" value="<% out.print(id); %>" /><% out.print(name); %><br />
			<% } %>
		</div>
		<input type="submit" name="generate" value="Generate" />
	</form>
</div>

<div id="ruleinfo"></div>
<jsp:include page="footer.jsp" />