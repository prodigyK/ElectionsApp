<%@ page import="com.prodigy.fondbase.AuthorizedUser" %>
<%@ page import="com.prodigy.fondbase.SecurityUtil" %>
<%@ page import="com.prodigy.fondbase.model.security.Group" %>
<%@ page import="com.prodigy.fondbase.model.security.MenuCategory" %>
<%@ page import="com.prodigy.fondbase.model.security.Module" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.*" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top w3-card-2" id="navbar">
    <!-- Brand -->
    <a class="navbar-brand" href="#"><i class="fas fa-database"></i> <b>Database</b></a>

    <div class="container justify-content-md-center">

        <!-- Links -->
        <ul class="navbar-nav">

            <%
                boolean allRights = false;
                AuthorizedUser user = SecurityUtil.get();
                if (user.getGroup().getId() == 1000) {
                    allRights = true;
                }
                Group group = user.getGroup();
                List<Module> modules = user.getModules();
                List<MenuCategory> menu = user.getMenu();

                boolean isPresent = false;
                Set<MenuCategory> headCategoryTemp = new HashSet<>();
                for(MenuCategory menuCategory : menu){
                    if(menuCategory.getParent() == null){
                        headCategoryTemp.add(menuCategory);
                    }
                }

                for (Module module : modules) {
                    if (!module.isVisible()) continue;
                    for (MenuCategory item : menu) {
                        if (item.getParent() == null) continue;
                        if (item.getModule() != null && item.getModule().getId().equals(module.getId())) {
                            headCategoryTemp.add(item.getParent());
                        }
                    }
                }
                List<MenuCategory> headCategory = new ArrayList<>(headCategoryTemp);
                headCategory.sort((m1,m2) -> m1.getOrdered().compareTo(m2.getOrdered()));
                //Comparator.comparing(MenuCategory::getOrdered));
            %>

            <c:forEach items="<%=headCategory%>" var="headCategory">
                <%--
                    If user has access to the modules in this menu,
                    he will see this one.
                --%>
                <li class="nav-item mr-sm-2 active dropdown">
                    <a class="nav-link dropdown-toggle btn-sm btn-secondary" style="width: 150px; text-align: center;" href="#" id="navbardrop"
                       data-toggle="dropdown">
                        <b>${headCategory.menuIcon} ${headCategory.name}</b>
                    </a>

                    <div class="dropdown-menu">
                        <c:forEach items="<%=menu%>" var="childCategory">
                            <c:if test="${childCategory.parent.id == headCategory.id}">
                                <spring:url value="${childCategory.module.reference}" var="reference"/>
                                <a class="dropdown-item btn btn-secondary"
                                   href="${reference}"><b>${childCategory.name}</b></a>
                            </c:if>
                        </c:forEach>
                    </div>
                </li>

            </c:forEach>

        </ul>
    </div>

    <ul class="navbar-nav navbar-right">

        <li class="nav-item active dropdown">
            <a href="#" class="nav-link dropdown-toggle btn btn-sm" data-toggle="dropdown"><i class="fas fa-user"></i>
                <b><%=user.getUser().getName()%></b></a>
            <div class="dropdown-menu">
                <spring:url var="logout" value="spring_security_logout"/>
                <a class="dropdown-item" href="${logout}"><i class="fas fa-sign-out-alt"></i> Выход</a>
            </div>
        </li>
    </ul>


</nav>