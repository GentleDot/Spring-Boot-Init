<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="net.gentledot.vo.EmpVO" %>
<%@ page import="net.gentledot.utils.Tools"%>
<%@ page import="javax.tools.Tool" %>
<%@ page import="net.gentledot.vo.DeptVO" %>

<%
    String contextPath = request.getContextPath();
    EmpVO targetEmp = (EmpVO) request.getAttribute("result");
    List<DeptVO> deptList = (List<DeptVO>) request.getAttribute("deptList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>UpdateEmp</title>

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
            <h2>UpdateEmp</h2>
            <p>emp 수정 페이지</p>
        </div>

        <div class="row">
            <div class="col-md-12">
                <form class="info" action="<%= contextPath %>/empUpdate.do">
                    <div class="form-group">
                        <label for="empNo">사번 : </label>
                        <input type="text" id="empNo" name="empNo" value="<%= Tools.toEmptyBlank(targetEmp.getEmpNo()) %>" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="eName">이름 : </label>
                        <input type="text" id="eName" name="eName" value="<%= Tools.toEmptyBlank(targetEmp.geteName()) %>"/>
                    </div>
                    <div class="form-group">
                        <label for="job">수행업무 : </label>
                        <input type="text" id="job" name="job" value="<%= Tools.toEmptyBlank(targetEmp.getJob()) %>"/>
                    </div>
                    <div class="form-group">
                        <label for="mgr">매니저 사번 : </label>
                        <input type="text" id="mgr" name="mgr" value="<%= Tools.toEmptyBlank(targetEmp.getMgr()) %>"/>
                    </div>
                    <div class="form-group">
                        <label for="hireDate">고용일 : </label>
                        <input type="text" id="hireDate" name="hireDate" value="<%= Tools.dateFommatter(targetEmp.getHireDate(), "yyyy-mm-dd")%>"/>
                    </div>
                    <div class="form-group">
                        <label for="sal">연봉 : </label>
                        <input type="text" id="sal" name="sal" value="<%= Tools.toEmptyBlank(targetEmp.getSal()) %>"/>
                    </div>
                    <div class="form-group">
                        <label for="comm">인센티브 : </label>
                        <input type="text" id="comm" name="comm" value="<%= Tools.toEmptyBlank(targetEmp.getComm()) %>"/>
                    </div>
                    <div class="form-group">
                        <label for="deptNo">부서 : </label>
                        <select name="deptNo" id="deptNo">
                            <%
                                for (DeptVO vo : deptList){
                                    String deptNo = vo.getDeptNo();
                                    String dName = vo.getdName();

                                    if(deptNo.equals(targetEmp.getDeptNo())){

                            %>
                            <option value="<%= deptNo%>" selected><%= dName%></option>
                            <%
                                    }else{
                            %>
                            <option value="<%= deptNo%>"><%= dName%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-info" value="추가">
                    </div>
                </form>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <a href="<%= contextPath %>/empList.do" class="btn btn-default">목록</a>
            </div>
        </div>


    </div>

</body>
</html>