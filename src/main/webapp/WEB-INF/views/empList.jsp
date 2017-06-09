<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="net.gentledot.vo.EmpVO" %>
<%@ page import="net.gentledot.utils.Tools"%>

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

    <style>
        th{
            text-align: center;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <h2>List</h2>
        <p>목록 조회 페이지입니다 .</p>
    </div>
    <div class="row">
        <form action="<%= contextPath %>/empList.do" method="post" name="searchForm">
            사번조회 : <input type="text" class="w3-input  w3-border w3-round-large "
                          name="empno" value="<%= empno %>" autofocus style="width:30%;display:inline">
            <input type="submit" class="w3-btn w3-indigo w3-round-xxlarge"
                   value="검색">
            <input type="hidden" name="pageNo" value="${paging.pageNo}"/>
        </form>
    </div>
    <div class="row">
        <div class="col-md-12 text-right">
            <a href="<%= contextPath %>/empAddView.do" class="btn btn-info">사원추가</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered table-striped test">
                <tr class="success">
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

                    String empNo = Tools.toEmptyBlank(vo.getEmpNo());
                    String eName = Tools.toEmptyBlank(vo.geteName());
                    String job = Tools.toEmptyBlank(vo.getJob());
                    String mgr = Tools.toEmptyBlank(vo.getMgr());
                    String hireDate = Tools.dateFommatter(vo.getHireDate());
                    String sal = Tools.toEmptyBlank(vo.getSal());
                    String comm = Tools.toEmptyBlank(vo.getComm());
                    String deptNo = Tools.toEmptyBlank(vo.getDeptNo());

                %>
                <tr>
                    <td class="text-center"><a href="<%= contextPath %>/empView.do?empNo=<%= empNo %>"><%= empNo %>
                    </a></td>
                    <td class="text-center"><%= eName %>
                    </td>
                    <td class="text-center"><%= job %>
                    </td>
                    <td class="text-center"><%= mgr %>
                    </td>
                    <td class="text-center"><%= hireDate %>
                    </td>
                    <td class="text-right"><%= sal %>
                    </td>
                    <td class="text-right"><%= comm %>
                    </td>
                    <td class="text-center"><%= deptNo %>
                    </td>

                </tr>
                <%
                    }
                %>
            </table>
            <%
                if (list.size() <= 0) {
            %>
            <div class="notFoundList text-center">
                <span>조회한 값의 데이터가 없습니다.</span>
            </div>
            <% } %>
        </div>
    </div>

    <div>
        <jsp:include page="/WEB-INF/views/inc/paging.jsp">
            <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
            <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
            <jsp:param name="startPageNo" value="${paging.startPageNo}" />
            <jsp:param name="pageNo" value="${paging.pageNo}" />
            <jsp:param name="endPageNo" value="${paging.endPageNo}" />
            <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
            <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
        </jsp:include>
    </div>

</div>

<script type="text/javascript">
    function goPage(pageNo){
        document.searchForm.pageNo.value = pageNo;
        document.searchForm.submit();
    }

    $("input:submit").click(function(){
        $("input[name='pageNo']").val(1);
    });
</script>
</body>
</html>