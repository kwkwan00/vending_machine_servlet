<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vending Machine</title>
<link type="text/css" rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/smoothness/jquery-ui.css" />
<link type="text/css" rel="stylesheet" href="../resources/vendor.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="../script/form2object.js"></script>
<script type="text/javascript" src="../script/vendor.js"></script>
</head>
<body>
	<div id="vendorPanel">
		<table id="vendorInfo">
			<tr id="vendorInfo-coinOptions">
				<td class="centeredText">
					<table id="insertCoinsTable">
						<tr><td>
							<button type="button" onclick="javascript:insertCoin(1.00)">
								<span class="insertCoinButton">$1</span>
							</button>
						</td></tr>
						<tr><td>
							<button type="button" onclick="javascript:insertCoin(0.25)">
								<span class="insertCoinButton">25¢</span>
							</button>
						</td></tr>
						<tr><td>
							<button type="button" onclick="javascript:insertCoin(0.10)">
								<span class="insertCoinButton">10¢</span>
							</button>
						</td></tr>
						<tr><td>
							<button type="button" onclick="javascript:insertCoin(0.05)">
								<span class="insertCoinButton">5¢</span>
							</button>
						</td></tr>
					</table>
					<span id="userBalance"></span>
				</td>
				<td id="vendorInfo-returnCoin">
					<button id="returnCoinButton" type="button">
						<img src="../resources/images/returncoin.jpg" />
					</button>
				</td>
			</tr>
		</table>
		<div id="displayMessage" style="display: none"></div>
		<div id="vendorMenu">
			<table id="vendorMenu-options">
			</table>
		</div>
		<div id="adminLinkDiv"><a id="adminLink" href="#">Admin</a><a id="adminComplete" href="#" style="display:none">Done Restocking</a></div>
	</div>
	<div id="accountLogin" style="display:none;">
		<form id="accountLoginForm" action="../vending/verifyAccount" method="post" target="iframe-post-form">
			<table>
				<tr>
					<td><label for="usernameInput" >Username: </label></td>
					<td><input id="usernameInput" name="username" type="text" /></td>
				</tr>
				<tr>
					<td><label for="passwordInput">Password: </label></td>
					<td><input id="passwordInput" name="password" type="password" /></td>

				</tr>
			</table>
			<div id="loginError" style="display:none"></div>
			<button id="accountSubmit" type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only loginButton">
				<span>OK</span>
			</button>
			<button id="accountCancel" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only loginButton">
				<span>Cancel</span>
			</button>
			<button type="reset" style="display:none"></button>
		</form>
	</div>
	<div id="waitingNotification" style="display:none">Please wait while your product is dispensed...</div>
	<iframe id="iframe-post-form" name="iframe-post-form" style="display:none"></iframe>
</body>
</html>