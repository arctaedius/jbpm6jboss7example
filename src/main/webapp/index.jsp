<html>
<head>
<title>Basic JBPM6 JBOSS7 example</title>
</head>
<body>
<p>Basic JBPM6 JBOSS7 example</p>
<p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %></p>
<ul>
<li><a href="startProcess.jsp">Start Process</a></li>
<li><a href="task?user=john&cmd=list">John's Task</a></li>
<li><a href="task?user=mike&cmd=list">Mike's Task</a></li>
</ul>
</body>
</html>