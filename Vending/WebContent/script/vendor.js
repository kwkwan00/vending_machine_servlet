var invMarkup = '';
invMarkup += '<tr><td>';
invMarkup += '<table>';
invMarkup += '<tr><td><button type="button" onclick="javascript:dispenseItem(${id})" class="btnDispense"><img src="${imagePath}" /></button></td></tr>';
invMarkup += '<tr><td class="centeredText"><span class="uiText">Price: ${price}</span></div></td></tr></table>';
invMarkup += '</td><td>';
invMarkup += '<table class="restockItem" style="display:none">';
invMarkup += '<tr><td><button type="button" onclick="javascript:restockItem(${id})"><img src="../resources/images/add.jpg" /></button></td></tr>';
invMarkup += '<tr><td class="centeredText"><span id="stock${id}" class="uiText">Stock: ${stock}</span></td></tr>';
invMarkup += '</table>';
invMarkup += '</td></tr>';
$.template('inventoryTmpl', invMarkup);

var returnCoinInfo = '';
returnCoinInfo += 'Please take your change.';
returnCoinInfo += '<ol style="text-align:left;list-style-type:none">';
returnCoinInfo += '<li>Quarters: ${numQuarters}</li>';
returnCoinInfo += '<li>Dimes: ${numDimes}</li>';
returnCoinInfo += '<li>Nickels: ${numNickels}</li>';
returnCoinInfo += '</ol>';
$.template('returnTmpl', returnCoinInfo);
	
$(document).ready(function() {
	$.getJSON('../vending/getItems', renderInventory);
	
	$('#waitingNotification').dialog({ autoOpen: false, modal: true, resizable: false });
	$('#accountLogin').dialog({ 
		title: 'Administration Login',
		autoOpen: false, modal: true, resizable: false,
		position: ['center','top'],
		height: 220, width: 360
	});
	
	$('#returnCoinButton').click(function() {
		$.post('../vending/returnCoin', returnCoinCallback);
	});
	
	$('#adminLink').click(function() {
		$('#accountLogin').dialog('open');
	});
	
	$('#adminComplete').click(function() {
		$('.restockItem').hide();
		$('#adminComplete').hide();
		$('#adminLink').show();
	});
	
	$('#accountSubmit').click(function() {
		$('#accountLoginForm').one('submit', function() {
			$('.loginButton').attr('disabled', 'disabled');
			var iframe = $('#iframe-post-form');
			iframe.one('load', function() {
				var contents;
				if (iframe.contents().find('pre')) {
					contents = iframe.contents().find('pre');
				} else {
					contents = iframe.contents().find('body');
				}
				loginSuccessCallback($.parseJSON(contents.html()));
				setTimeout(function() { contents.html(''); }, 1);
			});
		});
	});
	
	$('#accountCancel').click(function() {
		$(':reset').click();
		$('#loginError').empty();
		$('.loginButton').removeAttr('disabled');
		$('#accountLogin').dialog('close');
	});

});

function restockItem(id) {
	$.post('../vending/restockItem', {itemId: id}, restockSuccessCallback);
}

function insertCoin(amount) {
	$.post('../vending/insertCoin', {amount: amount}, insertCoinSuccessCallback);
}

function dispenseItem(id) {
	$('.btnDispense').attr('disabled', 'disabled');
	$('#waitingNotification').dialog('open');
	$.post('../vending/purchaseItem', {itemId: id}, dispenseItemCallback);
}

function renderInventory(response) {
	$('#vendorMenu-options').empty();
	$(response.inventory).each(function(index, element) {
		$.tmpl('inventoryTmpl', element).appendTo('#vendorMenu-options');
	});
	updateUserBalance(response.userBalance);
}

function loginSuccessCallback(response) {
	if (response.successful) {
		$(':reset').click();
		$('#loginError').empty();
		$('.restockItem').show();
		$('#accountLogin').dialog('close');
		$('#adminLink').hide();
		$('#adminComplete').show();
	} else {
		$('#loginError').html(response.displayText);
		$('#loginError').show();
	}
	$('.loginButton').removeAttr('disabled');
}

function restockSuccessCallback(response) {
	renderStock(response.item);
}

function insertCoinSuccessCallback(response) {
	updateUserBalance(response.userBalance);
}

function dispenseItemCallback(response) {
	if (response.successful) {
		updateUserBalance(response.userBalance);
		renderStock(response.item);
	} 
	$('#displayMessage').html(response.displayText + ' ' + response.item.name);
	$('#waitingNotification').dialog('close');
	$('#displayMessage').slideDown('normal').delay(7000).slideUp('slow');
	$('.btnDispense').removeAttr('disabled');
}

function returnCoinCallback(response) {
	updateUserBalance(response.userBalance);
	$('#displayMessage').empty();
	$.tmpl('returnTmpl', response).appendTo('#displayMessage');
	$('#displayMessage').slideDown('normal').delay(7000).slideUp('slow');
}

function updateUserBalance(userBalance) {
	$('#userBalance').html('Balance: ' + formatCurrency(userBalance));
}

function renderStock(item) {
	$('#stock' + item.id).html('Stock: ' + item.stock);
}

function formatCurrency(num) {
    num = isNaN(num) || num === '' || num === null ? 0.00 : num;
    return parseFloat(num).toFixed(2);
}