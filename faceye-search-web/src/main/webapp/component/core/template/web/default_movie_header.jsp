<%@ include file="/component/core/taglib/taglib.jsp"%>
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		<span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
	</button>
	 
	<a class="navbar-brand" href="<c:url value="/"/>"><fmt:message key="global.home" /></a>
</div>

<div class="navbar-collapse collapse">
	<ul class="nav navbar-nav">
	<!-- 
		<li class="active"><a href="<c:url value="/"/>"><fmt:message key="global.home" /></a></li>
		 -->
		<li class="active"><a href="<c:url value="/search/movie/home"/>"><fmt:message key="search.movie"/></a></li>
	</ul>
</div>