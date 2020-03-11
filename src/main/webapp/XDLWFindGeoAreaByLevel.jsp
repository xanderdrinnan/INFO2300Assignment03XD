<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h2>Re Edited for INFO2300 Assignment 03 - Xander Drinnan</h2>

<form method="post" action="Results">
 <select name="getLevel" id="getLevel">
  <option value="0">0</option>
  <option value="1">1</option>
  <option value="2">2</option>
 </select>
 <input type="submit" value="Submit">
</form>

<table border="1">

 <th></th>
 <th>Name</th>
 <th>Code</th>
 <th>Geographic Area ID</th>
 <th>Level</th>
 <th>Alternative Code</th>

 <c:forEach var="geo" items="${geoList}" varStatus="status">
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