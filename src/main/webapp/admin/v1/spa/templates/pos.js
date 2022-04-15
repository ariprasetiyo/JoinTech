$(function() {
	var csrfToken = $('#csrfToken').val();
	_screenTabSize();

	$("#saveRole").validate({
		rules : {
			inputRoleName : {
				minlength : 3,
				required : true
			}
		}
	});

	// Get data from table
	// Edit data
	var idTmpUserGroupButtonEdit = null;
	var isEdit = false;
	$("#tableUserGroup")
			.on(
					'click',
					'.editButton',
					function() {
						if (idTmpUserGroupButtonEdit != null) {
							_showModalMessage("Attention",
									"Couldn't edit before another process saved or cancelled");
							return;
						}

						$("#inputRoleInput").attr("disabled", "disabled");
						$("#cancelEdit").removeAttr("disabled");
						$(this).attr("disabled", "disabled");
						idTmpUserGroupButtonEdit = $(this).attr("id").replace(
								"editAuth", "");
						var isActive = $("#disabled" + idTmpUserGroupButtonEdit)
								.is(":checked");

						$("#checkBoxIsActive").prop("checked", isActive);

						getDataOnTable(this);
						isEdit = true;
					});

	// save or edit button
	$("#saveUserGroup").on('click', function() {
		var url;
		if (!isEdit) {
			url = '/admin/v1/api/userGroup/saveUserGroup';
		} else {
			url = '/admin/v1/api/userGroup/editUserGroup';
		}
		saveEditUser(idTmpUserGroupButtonEdit, url);
		resetTagHtml();
	});

	// cancel button
	$("#cancelEdit").on('click', function() {
		resetTagHtml();
		idTmpUserGroupButtonEdit = null;
	});

	function resetTagHtml() {
		$("#inputRoleInput").val("");
		$("#checkBoxIsActive").prop("checked", false);
		$("#cancelEdit").attr("disabled", "disabled");
		$("#inputRoleInput").removeAttr("disabled");
		$("#editAuth" + idTmpUserGroupButtonEdit).removeAttr("disabled");
		idTmpUserGroupButtonEdit = null;
		isEdit = false;
	}

	function jsonRequestDataEditSaver(id) {
		var roleName, isActive;
		roleName = $("#inputRoleInput").val();
		isActive = $("#checkBoxIsActive").is(':checked');
		if(isActive == true){
			isActive = false;
		}else{
			isActive = true
		}

		var jsonRequest = {};
		var jsonData = {};

		jsonData["id"] = id;
		jsonData["roleName"] = roleName;
		jsonData["disabled"] = isActive;
		jsonRequest["requestData"] = jsonData;
		return JSON.stringify(jsonRequest);
	}

	function saveEditUser(id, url) {
		$.ajax({
			type : 'POST',
			url : url,
			contentType : 'application/json',
			data : jsonRequestDataEditSaver(id),
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},/*
				 * , data : { username : textUsername, name : textName, email :
				 * textEmail, noHp : textNoHp, roleId : selectRole, isActive :
				 * checkBoxIsActive, password : textPassword, id : id },
				 */
			datatype : 'json',
			success : function(data, textStatus, jqXHR) {
				var message = data.message;
				var messageInfo = data.statusType;
				if (message != undefined) {
					messageInfo += " " + message;
				}
				$("#infoSaveUser").text(messageInfo);
				$("#infoSaveUser").attr('class', 'success-message');
				$('#tableUserGroup').DataTable().ajax.reload();
			},
			complete : function() {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				var objJson = JSON.parse(jqXHR.responseText);
				var errorMessage = "";
				for (a = 0; a < objJson.fieldErrors.length; a++) {
					errorMessage += objJson.fieldErrors[a].objectName;
					errorMessage += " : ";
					errorMessage += objJson.fieldErrors[a].defaultMessage;
					if ((objJson.fieldErrors.length - 1) == a) {
						errorMessage += ".";
						continue;
					}
					errorMessage += ", ";
				}
				$("#infoSaveUser").attr('class', 'warning-message')
				$("#infoSaveUser").text(errorMessage);
			}
		});
	}

	function listDataTable(data, callback, settings) {
		$
				.ajax({
					async : true,
					type : 'POST',
					contentType : 'application/json',
					url : '/admin/v1/api/inventory/master/items',
					headers : {
						'X-XSRF-TOKEN' : csrfToken
					},
					/*
					 * data : { limit : data.length, offset : data.start, search :
					 * data.search.value },
					 */
					data : _jsonRequestListData(data, _idRole()),
					dataType : "json",
					beforeSend : function() {

					},
					success : function(dataResponse, textStatus, jqXHR) {
						var out = [];
						var itemCode = null;

						function buttonAction(i, itemCode) {
	                        if(editButtonAction == editButtonActionStatic){
                                return '<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />'
                                                                    + '<input type = "hidden" id = "itemCode'
                                                                    + itemCode
                                                                    + '" class="idDataHide'
                                                                    + i
                                                                    + '" /> '
                                                                    + '<button type = "submit" id = "editAuth'
                                                                    + itemCode
                                                                    + '" class = "btn btn-primary editButton" > Add </button> ';
	                        }
	                        return '';
						}

						for (var i = 0, ien = dataResponse.responseData.data.length; i < ien; i++) {
							itemCode = dataResponse.responseData.data[i].itemCode;
							out
									.push([
											_getNumberOfRow(data.start, i),
											_center(_convertDate(dataResponse.responseData.data[i].modifiedTime)),
											_center(dataResponse.responseData.data[i].itemCode),
											_center(dataResponse.responseData.data[i].itemName),
											_center(dataResponse.responseData.data[i].stock),
											buttonAction(i, itemCode) ]);
						}

						setTimeout(
								function() {
									callback({
										draw : data.draw,
										data : out,
										recordsTotal : dataResponse.responseData.totalRecord,
										recordsFiltered : dataResponse.responseData.totalRecord
									});
								}, 50);
					},
					complete : function() {
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
	}

	function _getMiniScreenDataTableTab() {
        	return $(window).height() - 700;
    }

	// dataTables ajax logic
	var tableUserGroup = $('#tableMasterItems').DataTable({
		/*
		 * l - Length changing f - Filtering input t - The table! i -
		 * Information p - Pagination r - pRocessing < and > - div elements
		 * <"class" and > - div with a class Examples: <"wrapper"flipt>, <lf<t>ip>
		 */
		"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
		serverSide : true,
		ordering : false,
		searching : true,
		ajax : function(data, callback, settings) {
			listDataTable(data, callback, settings);
		},
		scrollY : _getMiniScreenDataTableTab(),
		scroller : {
			loadingIndicator : true
		}
	});

//	var tableUserGroup = $('#posTmptList').DataTable({
//    		/*
//    		 * l - Length changing f - Filtering input t - The table! i -
//    		 * Information p - Pagination r - pRocessing < and > - div elements
//    		 * <"class" and > - div with a class Examples: <"wrapper"flipt>, <lf<t>ip>
//    		 */
//    		"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
//    		serverSide : true,
//    		ordering : false,
//    		searching : true,
//    		ajax : function(data, callback, settings) {
//    			listDataTable(data, callback, settings);
//    		},
//    		scrollY : _getMiniScreenDataTableTab(),
//    		scroller : {
//    			loadingIndicator : true
//    		}
//    	});

	$("#submitAction").on('click', function() {
		tableUserGroup.ajax.reload();
	});

	function getDataOnTable(varThis) {

		// find the row
		var $row = $(varThis).closest("tr");
		var $tds = $row.find("td");
		var loopColumn = 1;

		// loop the column of per row
		$.each($tds, function() {
			if (loopColumn === 4) {
				$("#inputRoleInput").val($(this).text());
			}
			loopColumn += 1;
		});
	}

});
