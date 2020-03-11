<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h2>Results</h2>

<form method="post" action="PopulationResults">
    <input type="number" name="altCode">
    <input type="submit" value="Submit">
</form>

<table border="1">

    <th></th>
    <th>Name</th>
    <th>Code</th>
    <th>Alternative Code</th>
    <th>Level</th>
    <th>Total Population</th>

    <c:forEach var="age" items="${populationList}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <<td>${age.XDLWGeographModel.name}</td>
            <td>${age.XDLWGeographModel.code}</td>
            <td>${age.XDLWGeographModel.alternativeCode}</td>
            <td>${age.XDLWGeographModel.level}</td>>
            <td>${age.XDLWGeographModel.getTotalPop}</td>

        </tr>
    </c:forEach>
</table>

</body>
</html>