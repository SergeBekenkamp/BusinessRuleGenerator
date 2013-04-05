<%@page import="java.util.List"%>
<%@page import="domain.ConditionalValue"%>
<%
	String rules = ""  + (Integer) request.getAttribute("id");
	String name = (String) request.getAttribute("name");
	String entity = (String) request.getAttribute("entity");
	String attribute = (String) request.getAttribute("attribute");
	String operator = (String) request.getAttribute("operator");
	List<ConditionalValue> values = (List<ConditionalValue>) request.getAttribute("conditionalvalues");
%>
<h1>Business Rule</h1>
Rule id:
<%="" + rules%><br>
Name:
<%=name%><br>
Entity:
<%=entity%><br>
Attribute:
<%=attribute%><br>
Operator:
<%=operator%><br>


<%
	for (ConditionalValue v : values) {
%>
<h2>Conditional Value</h2>
Name:
<%=v.getName()%>
<br>
Value:
<%=v.getValue() %><br>
Entity:
<%=v.getAttribute().getEntity().getTableName() %><br>
Attribute:
<%=v.getAttribute().getColumnName() %><br>
<%
	}
%>
