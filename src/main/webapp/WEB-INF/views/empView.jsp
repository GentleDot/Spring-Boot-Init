<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="net.gentledot.vo.EmpVO" %>
<%@ page import="net.gentledot.utils.Tools"%>
<%@ page import="javax.tools.Tool" %>

<%
    String contextPath = request.getContextPath();
    EmpVO vo = (EmpVO) request.getAttribute("emp");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
    <div class="container">
        <div class="row">
            <h2>ViewDetail</h2>
            <p>상세 조회 페이지.</p>
        </div>

        <div class="row">
            <div class="col-md-12">
                <dl class="dl-horizontal">
                    <dt>사번 : </dt>
                    <dd><%= Tools.toEmptyBlank(vo.getEmpNo()) %></dd>
                    <dt>이름 : </dt>
                    <dd><%= Tools.toEmptyBlank(vo.geteName())%></dd>
                    <dt>수행업무 : </dt>
                    <dd><%= Tools.toEmptyBlank(vo.getJob())%>
                    </dd>
                    <dt>매니저 사번 : </dt>
                    <dd><%=Tools.toEmptyBlank(vo.getMgr())%>
                    </dd>
                    <dt>고용일 : </dt>
                    <dd><%=Tools.dateFommatter(vo.getHireDate(), "yyyy-MM-dd")%>
                    </dd>
                    <dt>연봉 : </dt>
                    <dd><%=Tools.toEmptyBlank(vo.getSal())%>
                    </dd>
                    <dt>인센티브</dt>
                    <dd><%=Tools.toEmptyBlank(vo.getComm())%>
                    </dd>
                    <dt>부서번호</dt>
                    <dd><%=Tools.toEmptyBlank(vo.getDeptNo())%>
                </dl>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <a href="<%= contextPath %>/empList.do" class="btn btn-info">목록</a>
                <a href="<%= contextPath %>/empUpdateView.do?empNo=<%= vo.getEmpNo() %>" class="btn btn-warning">수정</a>
                <a href="<%= contextPath %>/empDel.do?empNo=<%= vo.getEmpNo() %>" class="btn btn-danger">삭제</a>
            </div>
        </div>


    </div>

</body>
</html>