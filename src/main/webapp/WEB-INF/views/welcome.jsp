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
                <button type="button" class="btn btn-info btn-2g" data-toggle="modal" data-target="#myModal">Add
                    Contact
                </button>
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Add contact</h4>
                            </div>
                            <div class="modal-body">
                                <%--<p>Write information about your new contact</p>--%>
                                <div class="form-group">
                                    <label for="contactLogin">Login</label>
                                    <input type="text" class="form-control" id="contactLogin">
                                </div>
                                <div class="form-group">
                                    <label for="contactFullname">Fullname</label>
                                    <input type="text" class="form-control" id="contactFullname">
                                </div>
                                <div class="form-group">
                                    <label for="contactFullname">Phone</label>
                                    <input type="text" class="form-control" id="contactPhone">
                                </div>
                                <div>
                                    <label for="importance">Importance</label>
                                    <div id="importance">
                                        <div class="stars">
                                            <form action="">
                                                <input class="star star-5" id="star-5" type="radio" name="star"/>
                                                <label class="star star-5" for="star-5"></label>
                                                <input class="star star-4" id="star-4" type="radio" name="star"/>
                                                <label class="star star-4" for="star-4"></label>
                                                <input class="star star-3" id="star-3" type="radio" name="star"/>
                                                <label class="star star-3" for="star-3"></label>
                                                <input class="star star-2" id="star-2" type="radio" name="star"/>
                                                <label class="star star-2" for="star-2"></label>
                                                <input class="star star-1" id="star-1" type="radio" name="star"/>
                                                <label class="star star-1" for="star-1"></label>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <label for="sel1">Status</label>
                                        <select class="form-control" id="sel1">
                                            <option>friend</option>
                                            <option>business</option>
                                            <option>unknown</option>
                                        </select>
                                    </div>
                                <div class="form-group">
                                    <label for="contactDescription">Description</label>
                                    <textarea class="form-control" rows="5" id="contactDescription"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
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