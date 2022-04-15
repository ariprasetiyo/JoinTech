$(function() {

	$("#modalMenuid").on(
			'change',
			function() {
				if ($('#modalMenuid option:selected').text() == $(
						'#modalParentMenuid option:selected').text()) {
					$("#infoSavaAuthorization").text(
							"Menu and child menu couldn't same");
					$("#infoSavaAuthorization")
							.attr('class', 'warning-message');

					// $('#myModal').modal('toggle');
					// $('#myModal').modal('show');
					// $('#myModal').modal('hide');
					$('#modalMenuid').val("1");
				}
			});

	$("#modalParentMenuid").on(
			'change',
			function() {
				if ($('#modalMenuid option:selected').text() == $(
						'#modalParentMenuid option:selected').text()) {
					$("#infoSavaAuthorization")
							.attr('class', 'warning-message');
					$("#infoSavaAuthorization").text(
							"Menu and child menu couldn't same");
					$('#modalParentMenuid').val("1");
				}
			});

	$("#saveModalAddMenu").on(
			'click',
			function() {
				var vInsert = $('#isCreateAddMenu').is(':checked');
				var vUpdate = $('#isUpdateAddMenu').is(':checked');
				var vDelete = $('#isDeleteAddMenu').is(':checked');
				var vDisable = $('#isDisableAddMenu').is(':checked');
				var menuId = $('#modalMenuid').val();
				var parentId = $('#modalParentMenuid').val();
				var roleId = $('select[name=roles_id]').val();

				if(parentId == '' || parentId == '-'){
				    parentId = null
				}

				addDataMenu(jsonAddData(vInsert, vDelete, vUpdate, vDisable,
						menuId, parentId, roleId));
			});

	$(".select2").select2();

	// DELETE
	var idMenuDelete = null;
	var idElement = null;
	$('#tableAuthorization').on('click', '.deleteButton', function() {
		var id = $(this).attr('id').replace('deleteAuth', '');
		idElement = id;
		idMenuDelete = $("#idData" + id).val();
		_showModalDelete("");
	});

	$("#deleteTableRowCancel").on("click", function() {
		var idElementSave = "#saveAuth" + idElement;
		_replaceText(idElementSave, "Edit");
		_replaceClass(idElementSave, "saveButton", "editButton");
		_replaceId(idElementSave, "editAuth" + idMenuDelete);
		isEnableTriggerButtonSaveAndDelete(idElementSave);
	});

	$("#deleteTableRow").on("click", function() {
		if (idMenuDelete !== null && idMenuDelete !== undefined) {
			deleteDataMenu(idMenuDelete, _jsonRequestDeleteData(idMenuDelete));
		}
		_hideModalDelete();
		tableAuthorization.ajax.reload();
	});

	$('#tableAuthorization').on(
			'click',
			'.editButton',
			function() {

				var idAuth = $(this).attr('id');
				var id = idAuth.replace("editAuth", "");
				var enabledButton = [ "#isInsert" + id, "#isDelete" + id,
						"#isUpdate" + id, "#disabled" + id, "#saveAuth" + id,
						"#deleteAuth" + id ];
				var disableButton = [ "#editAuth" + id ];
				_enabledDisabledButton(enabledButton, null);
				_replaceText(disableButton, "Save");
				_replaceClass(disableButton, "editButton", "saveButton");
				_replaceId(disableButton, "saveAuth" + id);
				_checkHighlightTr(this);
			});

	$('#tableAuthorization').on('click', '.saveButton', function() {
		// $(".saveButton").on("click", function () {
		var idSaveAuth = $(this).attr('id');
		var id = idSaveAuth.replace('saveAuth', '');
		var vInsert, vDelete, vUpdate, vDisable, idTAU;
		idTAU = $("#idData" + id).val();
		var saveAuth = [ "#saveAuth" + id ];

		_replaceText(saveAuth, "Edit");
		_replaceClass(saveAuth, "saveButton", "editButton");
		_replaceId(saveAuth, "editAuth" + id);

		var vInsert = $("#isInsert" + id).is(':checked');
		var vDelete = $("#isDelete" + id).is(':checked');
		var vUpdate = $("#isUpdate" + id).is(':checked');
		var vDisable = $("#disabled" + id).is(':checked');

		editData(idTAU, jsonRequestEdit(vInsert, vDelete, vUpdate, vDisable));
		isEnableTriggerButtonSaveAndDelete(id);
	});

	function getLastNumberDataTables() {
		var table = $('#tableAuthorization').DataTable();
		var xx = table.row(':last').data();
		var result = xx[0].toString().replace('<div class="center">', '')
				.replace('</div>', '');
		return result;
	}

	function isEnableTriggerButtonSaveAndDelete(id) {
		var disableButton = [ "#isInsert" + id, "#isDelete" + id,
				"#isUpdate" + id, "#disabled" + id, "#saveAuth" + id,
				"#deleteAuth" + id ];
		var enabledButton = [ "#editAuth" + id ];
		_enabledDisabledButton(enabledButton, disableButton);
	}

	function deleteDataMenu(idMenu, jsonRequest) {
		$.ajax({
			type : "POST",
			url : "../api/authorization/deleteMenu/" + idMenu,
			contentType : "application/json",
			headers : {
				'X-XSRF-TOKEN' : $("#csrfToken").val()
			},
			data : jsonRequest,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				tableAuthorization.ajax.reload();
				return false;
			}
		});
	}

	function checkBoxHandle(i, booleanParam, idOrClass) {
		if (booleanParam) {
			booleanParam = false;
		} else {
			booleanParam = true;
		}
		return _checkBoxCustom(i, booleanParam, idOrClass);
	}

	function listDataTable(data, callback, settings) {
		$
				.ajax({
					type : "POST",
					url : "../api/authorization/list/" + _idRole(),
					headers : {
						'X-XSRF-TOKEN' : $("#csrfToken").val()
					},
					data : _jsonRequestListData(data, _idRole()),
					dataType : "json",
					contentType : "application/json",
					beforeSend : function() {
					},
					success : function(dataResponse, textStatus, jqXHR) {
						var out = [];
						var idUser = null;
						function buttonAction(i, idUser) {
							var buttonActionVar = '<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />'
									+ '<input type = "hidden" id ="idData'
									+ i
									+ '" value="'
									+ idUser
									+ '" '
									+ 'class="idDataHide'
									+ i
									+ '" /> '

									if(editButtonAction == editButtonActionStatic){
									    buttonActionVar += '<button type = "submit" id = "editAuth'+ i
                                    	                    + '" class = "btn btn-primary editButton" > Edit </button> '
									}

									if(deleteButtonAction == deleteButtonActionStatic){
									    buttonActionVar += '<button type = "submit" disabled id = "deleteAuth'+ i
                                                            + '" class = "btn btn-danger deleteButton" > Delete </button>';
									}
								return buttonActionVar;

						}

						for (var i = 0, ien = dataResponse.responseData.data.length; i < ien; i++) {
							idUser = dataResponse.responseData.data[i].id;
							out
									.push([
											_getNumberOfRow(data.start, i),
											dataResponse.responseData.data[i].menuName,
											dataResponse.responseData.data[i].createdTime,
											dataResponse.responseData.data[i].modifiedTime,
											checkBoxHandle(
													i,
													dataResponse.responseData.data[i].isInsert,
													"isInsert"),
											checkBoxHandle(
													i,
													dataResponse.responseData.data[i].isUpdate,
													"isUpdate"),
											checkBoxHandle(
													i,
													dataResponse.responseData.data[i].isDelete,
													"isDelete"),
											checkBoxHandle(
													i,
													dataResponse.responseData.data[i].disabled,
													"disabled"),/*
																 * dataResponse[i].isUpdate,
																 * dataResponse[i].isDelete,
																 * dataResponse[i].disabled,
																 */
											buttonAction(i, idUser) ]);
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

	var tableAuthorization = $('#tableAuthorization').DataTable({
		/*
		 * l - Length changing f - Filtering input t - The table! i -
		 * Information p - Pagination r - pRocessing < and > - div elements
		 * <"class" and > - div with a class Examples: <"wrapper"flipt>, <lf<t>ip>
		 */
		"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
		serverSide : true,
		ordering : false,
		searching : false,
		ajax : function(data, callback, settings) {
			listDataTable(data, callback, settings);
		},
		scrollY : _getScreenDataTable(),
		scroller : {
			loadingIndicator : true
		},
		pageLength : 25
	});

	$("#submitAction").on('click', function() {
		tableAuthorization.ajax.reload();
	});

	function jsonAddData(vInsert, vDelete, vUpdate, vDisable, menuId, parentId,
			roleId) {
		var jsonRequest = {};
		var request = {};
		request["isUpdate"] = vUpdate;
		request["isDelete"] = vDelete;
		request["isInsert"] = vInsert;
		request["roleId"] = roleId;
		request["parentId"] = parentId;
		request["disabled"] = vDisable;
		request["id"] = menuId;
		jsonRequest["requestData"] = request;
		return JSON.stringify(jsonRequest);
	}

	function addDataMenu(jsonRequest) {
		var csrfToken = $("#csrfToken").val();
		$.ajax({
			type : "POST",
			url : "../api/authorization/addMenu/",
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},
			contentType : 'application/json',
			data : jsonRequest,
			dataType : "json",
			beforeSend : function() {
				_startLoading();
			},
			success : function(data, textStatus, jqXHR) {
				tableAuthorization.ajax.reload();
			},
			complete : function() {
				_finishLoading();
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}

	function jsonRequestEdit(vInsert, vDelete, vUpdate, vDisable) {
		var resultJson = {};
		var requestData = {};
		requestData["isUpdate"] = vUpdate;
		requestData["isDelete"] = vDelete;
		requestData["disabled"] = vDisable;
		requestData["isInsert"] = vInsert;
		resultJson["requestData"] = requestData;
		return JSON.stringify(resultJson);
	}

	function editData(id, jsonRequest) {
		var csrfToken = $("#csrfToken").val();
		$.ajax({
			type : "POST",
			url : "../api/authorization/update/" + id,
			contentType : "application/json",
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},
			data : jsonRequest,
			dataType : "json",
			beforeSend : function() {
				_startLoading();
			},
			success : function(data, textStatus, jqXHR) {

			},
			complete : function() {
				_finishLoading();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// window.location.href =
				// "http://localhost:8080/sitepat-satelit/";
				// window.location.replace("logout.ari");
			}
		});
	}
});