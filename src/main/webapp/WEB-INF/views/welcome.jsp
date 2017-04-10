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

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>

    <div id="exTab3" class="container">
        <ul class="nav nav-pills">
            <li class="active">
                <a href="#1b" data-toggle="tab">Contacts</a>
            </li>
            <li><a href="#2b" data-toggle="tab">Message controller</a>
            </li>
        </ul>

        <div class="tab-content clearfix">
            <div class="tab-pane active tab" id="1b">
                <button type="button" class="btn btn-info btn-2g" data-toggle="modal" data-target="#addContact">Add
                    Contact
                </button>
                <div class="modal fade" id="addContact" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Add contact</h4>
                            </div>
                            <form action="${contextPath}/add_contact?${_csrf.parameterName}=${_csrf.token}"
                                  enctype="multipart/form-data" method="POST">
                                <div class="modal-body">
                                    <div class="form-group">
                                        Photo: <input type="file" name="avatar">
                                    </div>
                                    <%--<p>Write information about your new contact</p>--%>
                                    <div class="form-group">
                                        <label for="contactLogin">Login</label>
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
                                        <label for="contactStatus">Status</label>
                                        <select name="contactStatus" class="form-control" id="contactStatus">
                                            <option>friend</option>
                                            <option>business</option>
                                            <option>unknown</option>
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
                                <th>Login</th>
                                <th>Phone</th>
                                <th>Status</th>
                            </tr>
                            <c:forEach var="contact" items="${contacts}">
                                <tr class="">
                                    <td></td>
                                    <td><c:out value="${contact.contactLogin}"/></td>
                                    <td><c:out value="${contact.contactPhone}"/></td>
                                    <td><c:out value="${contact.contactStatus}"/></td>
                                    <td>
                                        <form<%--action="${contextPath}/detailsContact"--%>>
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
                                                                    <h4>Login: ${contact.contactLogin}</h4>
                                                                    <h4>Fullname: ${contact.contactFullname}</h4>
                                                                    <h4>Phone: ${contact.contactPhone}</h4>
                                                                    <h4>Status: ${contact.contactStatus}</h4>
                                                                    <h4>Importance: ${contact.contactImportance} /5</h4>
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
                                        <form action="${contextPath}/update/${contact.id}">
                                            <button id="${contact.id}_upd" type="submit"
                                                    class="btn btn-primary btn-lg outline action">Update
                                            </button>
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
            <div class="tab-pane" id="2b">
                <h3>We use the class nav-pills instead of nav-tabs which automatically creates a background color for
                    the tab</h3>
            </div>
        </div>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>