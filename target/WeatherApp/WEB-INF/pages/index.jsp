<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Weather Info</title>
</head>
<body>

<h2>Enter Zipcode</h2>
<form:form method="POST" action="/WeatherApp/getWeather" commandName="location">
   <table>
    <tr>
        <td><form:label path="zipcode">Zipcode</form:label></td>
        <td><form:input path="zipcode" /></td>
        <td><form:errors path="zipcode" /></td>
    </tr>

    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  

</form:form>
</body>
</html>