<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    <link href="${contextPath}/resources/css/rating.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h2>Welcome ${pageContext.request.userPrincipal.name} | <a
            onclick="document.forms['logoutForm'].submit()">Logout</a>
    </h2>
    </c:if>

    <div id="exTab3" class="container">
        <ul class="nav nav-pills">
            <li class="active">
                <a href="#1b" data-toggle="tab">Employeers</a>
            </li>
            <li>
                <a href="#3b" data-toggle="tab">Departments</a>
            </li>
            <li><a href="#2b" data-toggle="tab">Work time</a>
            </li>
        </ul>

        <div class="tab-content clearfix">
            <div class="tab-pane active tab" id="1b">
                <button type="button" class="btn btn-info btn-2g" data-toggle="modal" data-target="#addContact">Add
                    Employee
                </button>
                <div class="modal fade" id="addContact" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Add Employee</h4>
                            </div>
                            <form action="${contextPath}/add_contact?${_csrf.parameterName}=${_csrf.token}"
                                  enctype="multipart/form-data" method="POST">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="avatar">Avatar</label>
                                        <input id="avatar" type="file" name="avatar">
                                    </div>
                                    <%--<p>Write information about your new contact</p>--%>
                                    <div class="form-group">
                                        <label for="contactLogin">Login/Code</label>
                                        <input name="contactLogin" type="text" class="form-control" id="contactLogin">
                                    </div>
                                    <div class="form-group">
                                        <label for="contactFullname">Fullname</label>
                                        <input name="contactFullname" type="text" class="form-control"
                                               id="contactFullname">
                                    </div>
                                    <div class="form-group">
                                        <label for="contactPhone">Phone</label>
                                        <input name="contactPhone" type="text" class="form-control" id="contactPhone">
                                    </div>
                                    <div>
                                        <label for="importance">Importance</label>
                                        <input id="important" type="hidden" name="important" value=""/>
                                        <div id="importance">
                                            <div class="stars">
                                                <input class="star star-5" id="5" type="radio" name="star"
                                                       onclick="document.getElementById('important').value = '5'"/>
                                                <label class="star star-5" for="5"></label>
                                                <input class="star star-4" id="4" type="radio" name="star"
                                                       onclick="document.getElementById('important').value = '4'"/>
                                                <label class="star star-4" for="4"></label>
                                                <input class="star star-3" id="3" type="radio" name="star"
                                                       onclick="document.getElementById('important').value = '3'"/>
                                                <label class="star star-3" for="3"></label>
                                                <input class="star star-2" id="2" type="radio" name="star"
                                                       onclick="document.getElementById('important').value = '2'"/>
                                                <label class="star star-2" for="2"></label>
                                                <input class="star star-1" id="1" type="radio" name="star"
                                                       onclick="document.getElementById('important').value = '1'"/>
                                                <label class="star star-1" for="1"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="contactStatus">Department</label>
                                        <select name="contactStatus" class="form-control" id="contactStatus">
                                            <option>Production department</option>
                                            <option>Marketing department</option>
                                            <option>Sales department</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="contactDescription">Description</label>
                                        <textarea name="contactDescription" class="form-control" rows="5"
                                                  id="contactDescription"></textarea>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-default">Save</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="contactListTable">
                    <c:if test="${contacts.size() != 0}">
                        <table class="table table-hover table-bordered tableContact">
                            <tr>
                                <th>#</th>
                                <th>Login/Code</th>
                                <th>Phone</th>
                                <th>Department</th>
                            </tr>
                            <c:forEach var="contact" items="${contacts}">
                                <tr class="">
                                    <td></td>
                                    <td><c:out value="${contact.contactLogin}"/></td>
                                    <td><c:out value="${contact.contactPhone}"/></td>
                                    <td><c:out value="${contact.contactStatus}"/></td>
                                    <td>
                                        <form>
                                            <button id="${contact.id}_det" type="button"
                                                    class="btn btn-primary btn-lg outline action" data-toggle="modal"
                                                    data-target="#detailsContact${contact.id}">Details
                                            </button>
                                        </form>
                                        <div class="modal fade" id="detailsContact${contact.id}" role="dialog">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">
                                                            &times;
                                                        </button>
                                                        <h4 class="modal-title">Contact detail information</h4>
                                                    </div>
                                                    <div class="modal-body details">
                                                        <table class="detailsTable">
                                                            <tr>
                                                                <td style="display:none;"></td>
                                                                <td>
                                                                    <img src="${contextPath}/avatar/${contact.id}"
                                                                         width="150"/>
                                                                </td>
                                                                <td class="contactInfo">
                                                                    <h4>Login/Code: ${contact.contactLogin}</h4>
                                                                    <h4>Fullname: ${contact.contactFullname}</h4>
                                                                    <h4>Phone: ${contact.contactPhone}</h4>
                                                                    <h4>Department: ${contact.contactStatus}</h4>
                                                                    <h4>Importance: ${contact.contactImportance} /
                                                                        5</h4>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="display:none;"></td>
                                                                <td colspan="2" class="description">
                                                                    <h4>Total days: ${contact.getAllWeekends() + contact.getAllBusinessTrip() + contact.getAllDayoffs()}
                                                                    </h4>
                                                                    <h4>Business trips: <br> Total
                                                                        days: ${contact.getAllBusinessTrip()} <br> Total
                                                                        contributions: ${contact.getAllBusinessTripContr()}
                                                                    </h4>
                                                                    <h4>Dayoffs: <br> Total
                                                                        days: ${contact.getAllDayoffs()}
                                                                    </h4>
                                                                    <h4>Weekends: <br> Total
                                                                        days: ${contact.getAllWeekends()}
                                                                    </h4>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="display:none;"></td>
                                                                <td colspan="2" class="description">
                                                                    <h4>Description</h4>
                                                                    <h5>${contact.contactDescription}</h5>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <form action="${contextPath}/detailsContact" method="POST">
                                                        <div class="modal-footer">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                   value="${_csrf.token}"/>
                                                            <button type="submit" class="btn btn-default"
                                                                    data-dismiss="modal">Close
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                    </td>
                                    <td>
                                        <form action="${contextPath}/update/${contact.id}?${_csrf.parameterName}=${_csrf.token}"
                                              enctype="multipart/form-data" method="POST">
                                            <button id="${contact.id}_upd" type="button"
                                                    class="btn btn-primary btn-lg outline action" data-toggle="modal"
                                                    data-target="#updateContact">Update
                                            </button>
                                            <div class="modal fade updateTr" id="updateContact" role="dialog">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal">
                                                                &times;
                                                            </button>
                                                            <h4 class="modal-title">Update contact</h4>
                                                        </div>
                                                        <form action="${contextPath}/update/?${_csrf.parameterName}=${_csrf.token}"
                                                              enctype="multipart/form-data" method="POST">
                                                            <div class="modal-body">
                                                                <img src="${contextPath}/avatar/${contact.id}"
                                                                     width="150"/>
                                                                <div class="form-group">
                                                                    <label for="avatarUpd">Avatar</label>
                                                                    <input id="avatarUpd" type="file" name="avatarUpd">
                                                                </div>
                                                                    <%--<p>Write information about your new contact</p>--%>
                                                                <div class="form-group">
                                                                    <label for="contactLoginUpd">Login/Code</label>
                                                                    <input name="contactLoginUpd"
                                                                           value="${contact.contactLogin}" type="text"
                                                                           class="form-control" id="contactLoginUpd">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="contactFullnameUpd">Fullname</label>
                                                                    <input name="contactFullnameUpd"
                                                                           value="${contact.contactFullname}"
                                                                           type="text" class="form-control"
                                                                           id="contactFullnameUpd">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="contactPhoneUpd">Phone</label>
                                                                    <input name="contactPhoneUpd" type="text"
                                                                           value="${contact.contactPhone}"
                                                                           class="form-control" id="contactPhoneUpd">
                                                                </div>
                                                                <div>
                                                                    <label for="importanceUpd">Importance</label>
                                                                    <input id="importantUpd" type="hidden"
                                                                           name="importantUpd" value=""/>
                                                                    <div id="importanceUpd">
                                                                        <div class="stars">
                                                                            <input class="star star-5" id="5"
                                                                                   type="radio" name="star"
                                                                                   onclick="document.getElementById('importantUpd').value = '5'"/>
                                                                            <label class="star star-5" for="5"></label>
                                                                            <input class="star star-4" id="4"
                                                                                   type="radio" name="star"
                                                                                   onclick="document.getElementById('importantUpd').value = '4'"/>
                                                                            <label class="star star-4" for="4"></label>
                                                                            <input class="star star-3" id="3"
                                                                                   type="radio" name="star"
                                                                                   onclick="document.getElementById('importantUpd').value = '3'"/>
                                                                            <label class="star star-3" for="3"></label>
                                                                            <input class="star star-2" id="2"
                                                                                   type="radio" name="star"
                                                                                   onclick="document.getElementById('importantUpd').value = '2'"/>
                                                                            <label class="star star-2" for="2"></label>
                                                                            <input class="star star-1" id="1"
                                                                                   type="radio" name="star"
                                                                                   onclick="document.getElementById('importantUpd').value = '1'"/>
                                                                            <label class="star star-1" for="1"></label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="contactStatusUpd">Department</label>
                                                                    <select name="contactStatusUpd" class="form-control"
                                                                            id="contactStatusUpd">
                                                                        <c:if test="${contact.contactStatus eq 'Production department'}">
                                                                            <option selected="selected">Production
                                                                                department
                                                                            </option>
                                                                            <option>Marketing department</option>
                                                                            <option>Sales department</option>
                                                                        </c:if>
                                                                        <c:if test="${contact.contactStatus eq 'Marketing department'}">
                                                                            <option>Production department</option>
                                                                            <option selected="selected">Marketing
                                                                                department
                                                                            </option>
                                                                            <option>Sales department</option>
                                                                        </c:if>
                                                                        <c:if test="${contact.contactStatus eq 'Sales department'}">
                                                                            <option>Production department</option>
                                                                            <option>Marketing department</option>
                                                                            <option selected="selected">Sales
                                                                                department
                                                                            </option>
                                                                        </c:if>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="contactDescriptionUpd">Description</label>
                                                                    <textarea name="contactDescriptionUpd"
                                                                              class="form-control" rows="5"
                                                                              id="contactDescriptionUpd">${contact.contactDescription}</textarea>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                    <%-- <input id="updatingContactId" type="hidden"
                                                                            value="${contact.id}" name="updatingContactId"/>--%>
                                                                <button type="submit" class="btn btn-default">Update
                                                                </button>
                                                                <button type="button" class="btn btn-default"
                                                                        data-dismiss="modal">Close
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>

                                        </form>
                                    </td>
                                    <td>
                                        <form>
                                            <button id="${contact.id}_del" type="button"
                                                    class="btn btn-primary btn-lg outline action" data-toggle="modal"
                                                    data-target="#deleteContact${contact.id}"
                                            >Delete
                                            </button>
                                        </form>
                                        <div class="modal fade" id="deleteContact${contact.id}" role="dialog">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">
                                                            &times;
                                                        </button>
                                                        <h4 class="modal-title">Do you really want to delete this
                                                            contact?</h4>
                                                    </div>
                                                    <form action="${contextPath}/deleteContact" method="POST">
                                                        <div class="modal-footer">
                                                            <input id="deletingContactId" type="hidden"
                                                                   value="${contact.id}" name="deletingContactId"/>
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                   value="${_csrf.token}"/>
                                                            <button type="submit" class="btn btn-default">Delete
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${contacts.size() == 0}">
                        <h3>Hi, you can add your contacts here!</h3>
                    </c:if>
                </div>
            </div>


            <div class="tab-pane active tab" id="3b">
                <div class="form-group">
                    <label for="contactStatusPageSel">Chose department</label>
                    <select name="contactStatusPageSel" class="form-control" id="contactStatusPageSel">
                        <option>Production department</option>
                        <option>Marketing department</option>
                        <option>Sales department</option>
                    </select>
                    <form action="${contextPath}/showTable?${_csrf.parameterName}=${_csrf.token}"
                          enctype="multipart/form-data" method="POST">
                        <button id="table_det" type="submit"
                                class="btn btn-default" data-toggle="modal"
                                data-target="#showTable">Show table
                        </button>
                        <input id="contactStatusPage1" value="Production department" type="hidden"
                        name="contactStatusPage1"/>
                    </form>
                    <form action="${contextPath}/showDayoff?${_csrf.parameterName}=${_csrf.token}"
                          enctype="multipart/form-data" method="POST">
                        <button id="dayoff_det" type="submit"
                                class="btn btn-default" data-toggle="modal"
                                data-target="#empWithDayoff">Show employeers with dayoff
                        </button>
                        <input id="contactStatusPage2" value="Production department" type="hidden"
                        name="contactStatusPage2"/>
                    </form>
                    <form action="${contextPath}/addDepartment?${_csrf.parameterName}=${_csrf.token}"
                          enctype="multipart/form-data" method="POST">
                        <label for="newDepartment">New department name</label>
                        <input id="newDepartment" type="text"
                               name="newDepartment"/>
                        <button id="newDep" type="submit"
                                class="btn btn-default" data-toggle="modal"
                                data-target="#showTable">Add department
                        </button>
                    </form>
                    <div class="contactListTable">
                        <c:if test="${contactListByDepartment1.size() != 0}">
                            <table class="table table-hover table-bordered tableContact">
                                <tr>
                                    <th>#</th>
                                    <th>Login/Code</th>
                                    <th>Fullname</th>
                                    <th>Phone</th>
                                    <th>Description</th>
                                </tr>
                                <c:forEach var="cd" items="${contactListByDepartment1}">
                                    <tr>
                                        <td></td>
                                        <td><c:out value="${cd.contactLogin}"/></td>
                                        <td><c:out value="${cd.contactFullname}"/></td>
                                        <td><c:out value="${cd.contactPhone}"/></td>
                                        <td><c:out value="${cd.contactDescription}"/></td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </c:if>
                    </div>
                    <div class="contactListTable">
                        <c:if test="${contactListByDepartment2.size() != 0}">
                            <table class="table table-hover table-bordered tableContact">
                                <tr>
                                    <th>#</th>
                                    <th>Login/Code</th>
                                    <th>Fullname</th>
                                    <th>Total time</th>
                                    <th>Business trip</th>
                                    <th>Weekend</th>
                                    <th>Dayoff</th>
                                </tr>
                                <c:forEach var="cd" items="${contactListByDepartment2}">
                                    <tr>
                                        <td></td>
                                        <td><c:out value="${cd.contactLogin}"/></td>
                                        <td><c:out value="${cd.contactFullname}"/></td>
                                        <td><c:out value="${cd.getAllWeekends() + cd.getAllBusinessTrip() + cd.getAllDayoffs()}"/></td>
                                        <td><c:out value="${cd.getAllBusinessTrip()}"/></td>
                                        <td><c:out value="${cd.getAllWeekends()}"/></td>
                                        <td><c:out value="${cd.getAllDayoffs()}"/></td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </c:if>
                    </div>
                    <%--<div class="modal fade" id="showTable" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">
                                        &times;
                                    </button>
                                    <h4 class="modal-title">Table</h4>
                                </div>
                                <div class="modal-body details">

                                </div>
                                <form action="${contextPath}/detailsContact" method="POST">
                                    <div class="modal-footer">
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}"/>
                                        <button type="submit" class="btn btn-default"
                                                data-dismiss="modal">Close
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>--%>

                </div>
            </div>


            <div class="tab-pane tab" id="2b">
                <div id="exTab4" class="container">
                    <ul class="nav nav-pills">
                        <li><a href="#22b" data-toggle="tab">Business trip</a>
                        </li>
                        <li><a href="#23b" data-toggle="tab">Weekend</a>
                        </li>
                        <li><a href="#24b" data-toggle="tab">Dayoff</a>
                        </li>
                    </ul>

                    <div class="tab-content clearfix">
                        <div class="tab-pane active tab" id="22b">
                            <form action="${contextPath}/saveBusinessTrip?${_csrf.parameterName}=${_csrf.token}"
                                  enctype="multipart/form-data" method="POST">
                                <label for="businessTripEmpId1">Employeers</label>
                                <select name="businessTripEmpId1" class="form-control" id="businessTripEmpId1">
                                    <c:forEach var="contact" items="${contacts}">
                                        <option><c:out value="${contact.id}"/> (<c:out value="${contact.contactFullname}"/>)</option>
                                    </c:forEach>
                                </select>

                                <%--<input name="businessTripEmpId"
                                       type="text" class="form-control"
                                       id="businessTripEmpId">--%>

                                <label for="businessTripEmpId">Employee</label>
                                <input name="businessTripEmpId"
                                       type="text" class="form-control"
                                       id="businessTripEmpId" autocomplete="on">
                                <label for="businessTripTimeCount">Time count</label>
                                <input name="businessTripTimeCount"
                                       type="text" class="form-control"
                                       id="businessTripTimeCount">
                                <label for="date">Date</label>
                                <input name="date"
                                       type="text" class="form-control"
                                       id="date">
                                <label for="businessTripContributions">Contributions</label>
                                <input name="businessTripContributions"
                                       type="text" class="form-control"
                                       id="businessTripContributions">

                                <button type="submit" class="btn btn-default">Add field
                                </button>
                            </form>


                            <c:if test="${businessTripList.size() != 0}">
                                <table class="table table-hover table-bordered tableContact">
                                    <tr>
                                        <th>#</th>
                                        <th>Employee id</th>
                                        <th>Employee login/code</th>
                                        <th>Time count</th>
                                        <th>Distributions</th>
                                    </tr>
                                    <c:forEach var="businessTrip" items="${businessTripList}">
                                        <tr class="">
                                            <td></td>
                                            <td><c:out value="${businessTrip.contact.id}"/></td>
                                            <td><c:out value="${businessTrip.contact.contactLogin}"/></td>
                                            <td><c:out value="${businessTrip.time_count}"/></td>
                                            <td><c:out value="${businessTrip.contributions}"/></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>


                        </div>
                        <div class="tab-pane active tab" id="23b">
                            <form action="${contextPath}/saveWeekend?${_csrf.parameterName}=${_csrf.token}"
                                  enctype="multipart/form-data" method="POST">
                                <label for="weekendEmpId1">Employeers</label>
                                <select name="weekendEmpId1" class="form-control" id="weekendEmpId1">
                                    <c:forEach var="contact" items="${contacts}">
                                        <option><c:out value="${contact.id}"/> (<c:out value="${contact.contactFullname}"/>)</option>
                                    </c:forEach>
                                </select>
                                <%--<input name="idfc"
                                       type="text" class="form-control"
                                       id="idfc">--%>
                                  <label for="weekendEmpId">Employee</label>
                                  <input name="weekendEmpId"
                                         type="text" class="form-control"
                                         id="weekendEmpId" autocomplete="on">
                                <label for="weekendTimeCount">Time count</label>
                                <input name="weekendTimeCount"
                                       type="text" class="form-control"
                                       id="weekendTimeCount">
                                <label for="weekendDescription">Date</label>
                                <input name="weekendDescription"
                                       type="text" class="form-control"
                                       id="weekendDescription">
                                <button type="submit" class="btn btn-default">Add field
                                </button>
                            </form>

                            <c:if test="${weekendList.size() != 0}">
                                <table class="table table-hover table-bordered tableContact">
                                    <tr>
                                        <th>#</th>
                                        <th>Employee id</th>
                                        <th>Employee login/code</th>
                                        <th>Time count</th>
                                        <th>Date</th>
                                    </tr>
                                    <c:forEach var="weekend" items="${weekendList}">
                                        <tr class="">
                                            <td></td>
                                            <td><c:out value="${weekend.contact.id}"/></td>
                                            <td><c:out value="${weekend.contact.contactLogin}"/></td>
                                            <td><c:out value="${weekend.time_count}"/></td>
                                            <td><c:out value="${weekend.date}"/></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>

                        </div>

                        <div class="tab-pane active tab" id="24b">
                            <form action="${contextPath}/saveDayoff?${_csrf.parameterName}=${_csrf.token}"
                                  enctype="multipart/form-data" method="POST">
                                <label for="dayoffEmpId1">Employeers</label>
                                <select name="dayoffEmpId1" class="form-control" id="dayoffEmpId1">
                                    <c:forEach var="contact" items="${contacts}">
                                        <option><c:out value="${contact.id}"/> (<c:out value="${contact.contactFullname}"/>)</option>
                                    </c:forEach>
                                </select>
                                <%--<input name="dayoffEmpId"
                                       type="text" class="form-control"
                                       id="dayoffEmpId">--%>
                                    <label for="dayoffEmpId">Employee</label>
                                    <input name="dayoffEmpId"
                                           type="text" class="form-control"
                                           id="dayoffEmpId" autocomplete="on">
                                <label for="dayoffTimeCount">Time count</label>
                                <input name="dayoffTimeCount"
                                       type="text" class="form-control"
                                       id="dayoffTimeCount">
                                <label for="dayoffDescription1">Description</label>
                                <input name="dayoffDescription1"
                                       type="text" class="form-control"
                                       id="dayoffDescription1">
                                <label for="dayoffDescription">Date</label>
                                <input name="dayoffDescription"
                                       type="text" class="form-control"
                                       id="dayoffDescription">

                                <button type="submit" class="btn btn-default">Add field
                                </button>
                            </form>


                            <c:if test="${dayoffFullList.size() != 0}">
                                <table class="table table-hover table-bordered tableContact">
                                    <tr>
                                        <th>#</th>
                                        <th>Employee login/code</th>
                                        <th>Employee fullname</th>
                                        <th>Time count</th>
                                        <th>Description</th>
                                    </tr>
                                    <c:forEach var="dayoff" items="${dayoffFullList}">
                                        <tr class="">
                                            <td></td>
                                            <td><c:out value="${dayoff.contact.contactLogin}"/></td>
                                            <td><c:out value="${dayoff.contact.contactFullname}"/></td>
                                            <td><c:out value="${dayoff.time_count}"/></td>
                                            <td><c:out value="${dayoff.description}"/></td>
                                                <%-- <td><c:out value="${dayoff.time_count}"/></td>
                                                 <td><c:out value="${dayoff.description}"/></td>--%>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>


                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>