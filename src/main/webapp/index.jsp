<%@page import="com.ruzicka.unicorns.Ranch"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="manifest" href="site.webmanifest">
  <link rel="apple-touch-icon" href="icon.png">

  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/main.css">

  <script src="js/vendor/jquery.js"></script>
  <script src="js/Ranch.js"></script>
  <script src="js/Unicorn.js"></script>
  <script src="js/main.js"></script>

    <%
     	Ranch ranch = new Ranch();
		pageContext.setAttribute("names", ranch.getAllUnicornNames());
		pageContext.setAttribute("colors", ranch.getAllUnicornColors());
		pageContext.setAttribute("locations", ranch.getAllUnicornLocations());
	%>
	<script>
		$(function() {
		    let ranch = new Ranch("${names}","${colors}","${locations}");

		    let unicorns = ranch.unicorns;
		    $(".ranch-title").html(Ranch.ranchName);
		    unicorns.forEach(unicorn => {
		        $("#unicorn-list").append('<tr class="unicorn" data-name="'+unicorn.name+'" data-color="'+unicorn.color+'" data-location="'+unicorn.location+'"><td>'+unicorn.name+'</td><td>'+unicorn.color+'</td><td>'+unicorn.location+'</td></tr>');
		    });
		    $("#unicorn-list").click(function(event) {
		        let target = event.target; // where was the click?
		        if (target.tagName != 'TD') return; // not on a TD tag? Then we're not interested

		        parent = $(target).parent();
		        let newLocation = "";
		        Ranch.ranchLocations.forEach((location,index) => {
		            if (location == $(parent).data("location")) {
		                if(index >= Ranch.ranchLocations.length-1) {
		                    newLocation = Ranch.ranchLocations[0];
		                }
		                else {
		                    newLocation = Ranch.ranchLocations[index+1];
		                }
		            }
		        });
		        $.ajax({url: "update.jsp?name="+$(parent).data("name")+"&location="+newLocation, success: function(result){
		           //nothing to do here yet
		        }});
		        $(parent).data("location", newLocation);
		        $(parent).html('<td>'+$(parent).data("name")+'</td><td>'+$(parent).data("color")+'</td><td>'+newLocation+'</td>');
		    });
		});
	</script>


</head>

<body>


  <h1 class="ranch-title"></h1>
  <p>Please click on the unicorn that you wish to change locations for.  Continue clicking until the location is correct.</p>
  <table id="unicorn-list">
    <tr><th>Name</th><th>Color</th><th>Location</th></tr>
  </table>


 </body>

</html>
