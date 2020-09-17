<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div style="padding-top: 15px; font-size: 22px; font-style: oblique;">Sistema PIB em Santa Lúcia</div>
	<div style="padding-top: 15px; font-size: 12px; font-style: oblique;">
		<img style="border-radius: 50%" width="50px" height="50px" src="<s:property value="#session['KEY_USUARIO_SESSION'].urlPhoto"/>">
		<i style="padding-left: 10px;"><s:property value="#session['KEY_USUARIO_SESSION'].pesNome" /></i>
	</div>
	<hr>
</div>