<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <base src="/questionnaire/" />
  <body>
  <c:forEach items="${questions }" var="item" varStatus="i">
    <div class="question">
        <div class="li">
          <span class="lb">问题${i.count}</span><span class="description">${item.description }</span>
        </div>
        <div class="li">
          <span class="lb">A.</span><span class="item">${item.itemA }</span>票数:${sttc[item.oid].num_itemA}
        </div>
        <div class="li">
          <span class="lb">B.</span><span class="item">${item.itemB }</span>票数:${sttc[item.oid].num_itemB}
        </div>
        <div class="li">
          <span class="lb">C.</span><span class="item">${item.itemC }</span>票数:${sttc[item.oid].num_itemC}
        </div>
        <div class="li">
          <span class="lb">D.</span><span class="item">${item.itemD }</span>票数:${sttc[item.oid].num_itemD}
        </div>
    </div>
    </c:forEach>
  </body>
</html>
