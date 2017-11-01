<%--@elvariable id="condition" type="main.emulator.panel.contract.PageInfo"--%>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 26.10.2017
  Time: 16:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h2>${condition.personInfo.errorMessage}</h2>
</div>
<div>
    <c:forEach items="${condition.serverInfo.elevators}" var="elevator">
        <h1>elevator № ${elevator.id} current floor ${elevator.currentFloor}</h1>
    </c:forEach>
    <h2>person condition: ${condition.personInfo.personConditionMessage}</h2>
    <h2>elevatorRequest id: ${condition.personInfo.requestId}</h2>
    <form action="getInfo" method="post">
        <input type="submit" value="check condition">
    </form>
</div>