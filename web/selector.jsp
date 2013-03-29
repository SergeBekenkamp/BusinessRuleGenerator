<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp" />
<%
ArrayList<String> rules = (ArrayList<String>) request.getAttribute("rules");
ArrayList<String> categories = (ArrayList<String>) request.getAttribute("categories");
ArrayList<String> entities = (ArrayList<String>) request.getAttribute("entities");
ArrayList<String> ruletypes = (ArrayList<String>) request.getAttribute("ruletypes");
%>
<div id="filters">
	<h2>Filter</h2>
	<h3>Categories</h3>
	<% for(String s : categories) {
		String[] parts = s.split(",");
		String id = parts[0];
		String name = parts[1]; %>
		<a href="?cat=<% out.print(id); %>"><% out.print(name); %></a><br />
	<% } %>
	<h3>BusinessRuleTypes</h3>
	<% for(String s : ruletypes) {
		String[] parts = s.split(",");
		String code = parts[0];
		String name = parts[1]; %>
		<a href="?brt=<% out.print(code); %>"><% out.print(name); %></a><br />
	<% } %>
</div>

<div id="selection">
	<form action="generator" method="post">
	<% for(String s : rules) {
		String[] parts = s.split(",");
		String id = parts[0];
		String name = parts[1]; %>
		<input type="checkbox" id="rules" value="<% out.print(id); %>" /> <% out.print(name); %><br />
	<% } %>
	<input type="submit" name="generate" value="Generate" />
	</form>
</div>

<div id="ruleinfo">

</div>
<jsp:include page="footer.jsp" />