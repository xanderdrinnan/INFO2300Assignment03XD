<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h2>Results</h2>

<form method="post" action="KeywordResults">
    <input type="text" name="getKeyword">
    <input type="submit" value="Submit">
</form>

<table border="1">

    <th></th>
    <th>Name</th>
    <th>Code</th>
    <th>Geographic Area ID</th>
    <th>Level</th>
    <th>Alternative Code</th>

    <c:forEach var="geo" items="${keywordList}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${geo.name}</td>
            <td>${geo.code}</td>
            <td>${geo.geographicAreaID}</td>
            <td>${geo.level}</td>
            <td>${geo.alternativeCode}</td>

        </tr>
    </c:forEach>
</table>

</body>
</html>