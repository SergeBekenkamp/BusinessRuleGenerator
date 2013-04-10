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
$(document).ready(function(){ 
	var $submit = $('input[name=generate]')
    $('input[name=businessRules]').click(function(){
        if($('input[name="businessRules"]:checked').length > 0){
        	
        	$submit.removeAttr('disabled'); 
        } else {
        	 $submit.attr("disabled","disabled"); 
        }
    });
});

function checkEmpty() {
	
	    var atLeastOneIsChecked = $('input[name="businessRules"]:checked').length > 0;
   // filter over the empty inputs
   return $('#businessRules :checkbox:checked').length > 0;
}

function getInfo(id){
	$.get("info?businessrule=" + id, function(data) {
		 $('#ruleinfo').empty();
		 $('#ruleinfo').append(data);
		});
}
function checkAll(chkboxName) {
	var checkboxes = document.getElementsByName(chkboxName);
	for ( var i = 0; i < checkboxes.length; i++) {
		checkboxes[i].checked = true;
	}
	var $submit = $('input[name=generate]')
	$submit.removeAttr('disabled'); 
}

function uncheckAll(chkboxName) {
	var checkboxes = document.getElementsByName(chkboxName);
	for ( var i = 0; i < checkboxes.length; i++) {
		checkboxes[i].checked=false;
  }
	var $submit = $('input[name=generate]');
	$submit.attr("disabled","disabled");
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
<div id="filters"  style="overflow-y:scroll;">
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
					<input type="checkbox"   name="businessRules" value="<% out.print(id); %>" /><a onClick="getInfo(<%=id%>)"><% out.print(name); %></a><br />
			<% } %>
		</div>
		<input type="submit" name="generate" value="Generate" disabled="disabled" />
		<br/>
		<a href="http://ondora01.hu.nl:8080/apex/f?p=505:1:8000225992775025:::::" title="Back to Apex">Back to Apex</a>
	</form>
</div>
<div id="ruleinfo"></div>

<jsp:include page="footer.jsp" />