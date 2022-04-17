function _getNumberOfRow(offset, count) {
	var number = offset + 1 + count;
	return '<div style="margin-left:20%;">'+number+'</div>';
}

function _getAlert() {
	alert("Test")
}

function _getScreenDataTable() {
	return $(window).height() - 175;
}

function _getScreenDataTableTab() {
	return $(window).height() - 218;
}

function _screenTabSize(){
	$(".screen-tab").css("height", "100%");
}

function _center(value){
    return '<div style="text-align: center;">'+value+'</div>';
}

function _convertDate(epochDate) {
    return new Date(epochDate).toLocaleString("id-ID")
}

function _checkBoxCustom(numberRow, isUncheck, idCheckBox) {
	var valCheck = "checked";
	if (isUncheck) {
		valCheck = "unchecked";
	}

	return '<div class="center" >'
			+ '<label class="containerChk" for="'
			+ idCheckBox + numberRow
			+ '"><input disabled type="checkbox" id="'
			+ idCheckBox + numberRow + '" ' + valCheck
			+ '> <span class="checkmarkChk"></span>'
			+ '</label></div>';
}

function _showModalMessage(title, message){
	$("#messageTitleReplace").text(title);
	$("#messageReplace").text(message);
	$("#modalAllMessage").modal("show");
}

function _showModalDelete(paramValue) {
	$("#messageDelete").text(
			"Are you sure delete this data " + paramValue + " ?");
	$("#modalDeleteRow").modal('show');
}

function _replaceModalDelete(paramValue) {
	if (paramValue != null) {
		$("#messageDelete").text("Delete data had been problem");
	} else {
		$("#messageDelete").text(paramValue);
	}
}

function _hideModalDelete(){
	$("#modalDeleteRow").modal('hide');
}

function _enabledDisabledButton(idEnableButton, idDisableButton) {

	if (idEnableButton !== null) {
		for (var b = 0; b < idEnableButton.length; b++) {
			$(idEnableButton[b]).removeAttr("disabled");
		}
	}

	if (idDisableButton !== null) {
		for (var a = 0; a < idDisableButton.length; a++) {
			$(idDisableButton[a]).attr("disabled", true);
		}
	}
}

function _idRole() {
	return $('select[name=roles_id]').val();
}
/**
 * Replace text
 * 
 * @param idTag
 * @param replaceTo
 * @returns
 */
function _replaceText(idTag, replaceTo) {
	$("" + idTag).text(replaceTo);
}

function _replaceId(idTag, replaceTo) {
	$("" + idTag).attr("id", replaceTo);
}

function _replaceClass(idTag, className, replaceTo) {
	$("" + idTag).removeClass(className);
	$("" + idTag).addClass(replaceTo);
}

/**
 * Function highlight check tr
 * 
 * @param thisVar
 * @returns
 */
function _checkHighlightTr(thisVar) {
	$(thisVar).closest("tr").addClass('checkTr');
}

function _startLoading() {
//	ajaxindicatorstart('Please wait..');
//	window.setTimeout(ajaxindicatorstart('Please wait..'), 500);
}

function _finishLoading() {
	timeout = setTimeout(ajaxindicatorstop(), 9000);
	clearTimeout(timeout);
}

function _jsonRequestListData(data) {
	var jsonRequest = {};
	var request = {};
	request["requestId"] = _uuid();
	request["limit"] = data.length;
	request["offset"] = data.start;
	request["search"] = data.search.value;
	jsonRequest["requestData"] = request;
	return JSON.stringify(jsonRequest);
}

function _jsonRequestListData(data, id) {
	var jsonRequest = {};
	var request = {};
	request["requestId"] = _uuid();
	request["id"] = id;
	request["limit"] = data.length;
	request["offset"] = data.start;
	request["search"] = data.search.value;
	jsonRequest["requestData"] = request;
	return JSON.stringify(jsonRequest);
}

function _jsonRequestDeleteData(id) {
	var jsonRequest = {};
	var request = {};
	request["id"] = id;
	jsonRequest["requestData"] = request;
	return JSON.stringify(jsonRequest);
}

function _uuid() {
    var now = Date.now();
    return uuidv4() +"-" +now
}

function uuidv4() {
  return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
    (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
  );
}

