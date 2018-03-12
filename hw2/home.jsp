<%@ page import="
twitterapp.*, 
twitter4j.conf.ConfigurationBuilder.*,
 twitter4j.TwitterFactory.*,
 twitter4j.Status.*,
 java.util.List.*,
twitter4j.TwitterException.*
" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

wassup : <%= TwitterApp.tweet() %>

</body>
</html>