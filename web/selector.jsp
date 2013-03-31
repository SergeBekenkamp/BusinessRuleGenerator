<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp" />
<%
ArrayList<String> rules = (ArrayList<String>) request.getAttribute("rules");
ArrayList<String> categories = (ArrayList<String>) request.getAttribute("categories");
ArrayList<String> entities = (ArrayList<String>) request.getAttribute("entities");
ArrayList<String> ruletypes = (ArrayList<String>) request.getAttribute("ruletypes");
%>
<div id="filters">
	<h1>Filter</h1>
	<h2>Categories</h2>
	<% for(String s : categories) {
		String[] parts = s.split(",");
		String id = parts[0];
		String name = parts[1]; %>
		<a href="?cat=<% out.print(id); %>"><% out.print(name); %></a><br />
	<% } %>
	<h2>BusinessRuleTypes</h2>
	<% for(String s : ruletypes) {
		String[] parts = s.split(",");
		String code = parts[0];
		String name = parts[1]; %>
		<a href="?brt=<% out.print(code); %>"><% out.print(name); %></a><br />
	<% } %>
</div>
<script language="JavaScript">

function checkAll(chkboxName) {
		  var checkboxes = document.getElementsByName(chkboxName);
		  for (var i=0; i<checkboxes.length; i++) {
		     checkboxes[i].checked=true;
		  }
		}

function uncheckAll(chkboxName) {
	var checkboxes = document.getElementsByName(chkboxName);
	  for (var i=0; i<checkboxes.length; i++) {
	     checkboxes[i].checked=false;
	  }
}
</script>
<div id="selection">
	<form name="selector" action="generator" method="post">
		<input type="button" value="Select all" onClick="checkAll('businessRules')" />
		<input type="button" value="Select none" onClick="uncheckAll('businessRules')" />
		<div id="businessrules">
			<% for(String s : rules) {
				String[] parts = s.split(",");
				String id = parts[0];
				String name = parts[1]; %>
				<input type="checkbox" name="businessRules" value="<% out.print(id); %>" /> <% out.print(name); %><br />
			<% } %>
		</div>
		<input type="submit" name="generate" value="Generate" />
	</form>
</div>

<div id="ruleinfo">

</div>
<jsp:include page="footer.jsp" />