<!DOCTYPE html>
<html xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:c="http://java.sun.com/jsp/jstl/core"
      xmlns:tiles="http://tiles.apache.org/tags-tiles"
      xmlns:form="http://www.springframework.org/tags/form"
      xmlns:spring="http://www.springframework.org/tags"
      xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"> <html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Student Information</h2>
   <table>
    <tr>
        <td>zipcode</td>
        <td>${location.zipcode}</td>
    </tr>
    <tr>
        <td>City</td>
        <td>${location.name}</td>
    </tr>
    <tr>
        <td>State</td>
        <td>${location.state}</td>
    </tr>
    <tr>
        <td>zipcode</td>
        <td>${location.temperature}</td>
    </tr>

</table>  
<a href="/WeatherApp/welcome">Back</a>
</body>
</html>