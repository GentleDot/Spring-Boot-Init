<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="net.gentledot.vo.EmpVO" %>

<%
    String contextPath = request.getContextPath();
    List<EmpVO> list = (List<EmpVO>) request.getAttribute("resultList");
    String empno = (String) request.getAttribute("empno");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List</title>

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
    <link rel="stylesheet" href="<%= contextPath %>/css/w3.css">

    <style>
        .notFoundList {
            text-align: center;
        }
    </style>
</head>

<body>
<div class="w3-container w3-margin">
    <h2>List</h2>
    <p>목록 조회 페이지입니다 .</p>
    <div>
        <form action="<%= contextPath %>/empList.do" method="post" name="searchForm">
            사번조회 : <input type="text" class="w3-input  w3-border w3-round-large "
                          name="empno" value="<%= empno %>" autofocus style="width:30%;display:inline">
            <input type="submit" class="w3-btn w3-indigo w3-round-xxlarge"
                   value="검색">
            <input type="hidden" name="pageNo" value="${paging.pageNo}"/>
        </form>
    </div>
    <div class="w3-container  w3-right-align  w3-margin">
        <a href="<%= contextPath %>/emp/empAddView.do" class="w3-btn w3-light-blue">사원추가</a>
    </div>
    <div>
        <table class="w3-table w3-bordered w3-striped w3-border test">
            <tr class="w3-green">
                <th>사번</th>
                <th>이름</th>
                <th>수행업무</th>
                <th>매니저사번</th>
                <th>고용일</th>
                <th>연봉</th>
                <th>인센티브</th>
                <th>부서번호</th>
            </tr>
            <% for (EmpVO vo : list) {

                String empNo = vo.getEmpNo();
                String eName = vo.geteName();
                String job = vo.getJob();
                String mgr = vo.getMgr();
                String hireDate = vo.getHireDate();
                String sal = vo.getSal();
                String comm = vo.getComm();
                String deptNo = vo.getDeptNo();

            %>
            <tr>
                <td class="w3-center"><a href="<%= contextPath %>/emp/empView.do?empNo=<%= empNo %>"><%= empNo %>
                </a></td>
                <td class="w3-center"><%= eName %>
                </td>
                <td class="w3-center"><%= job %>
                </td>
                <td class="w3-center"><%= mgr %>
                </td>
                <td class="w3-center"><%= hireDate %>
                </td>
                <td class="w3-right-align"><%= sal %>
                </td>
                <td class="w3-right-align"><%= comm %>
                </td>
                <td class="w3-center"><%= deptNo %>
                </td>

            </tr>
            <%
                }
            %>
        </table>
        <%
            if (list.size() <= 0) {
        %>
        <div class="notFoundList">
            <span>조회한 값의 데이터가 없습니다.</span>
        </div>
        <% } %>
    </div>

</body>
</html>