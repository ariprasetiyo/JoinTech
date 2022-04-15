$(function() {
	var csrfToken = $('#csrfToken').val();
	_screenTabSize();

	$("#addMenuPost").validate({
		rules : {
			nameOfMenu : {
				minlength : 3,
				required : true
			},
			urlInput : {

			}
		},
		messages : {
			nameOfMenu : {
				required : "Required"
			},
			nameOfMenu : {
			}
		},
		highlight : function(a) {
			$(a).closest(".form-group").addClass("has-error");
		},
		unhighlight : function(a) {
			$(a).closest(".form-group").removeClass("has-error");
		},
		errorElement : "span",
		errorClass : "help-blocks",
		errorPlacement : function(error, element) {
			if (element.is(":radio")) {
				error.appendTo(element.parents('.requestTypeGroup'));
			} else { // This is the default
				// behavior
				error.insertAfter(element);
			}
		},
		submitHandler : function(form) {
			buttonSubmitSaveEdit();
		}
	});

	var isTabMenu = false;
	var tableMenu = $('#tableMenu').DataTable({
		"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
		serverSide : true,
		ordering : false,
		searching : true,
		ajax : function(data, callback, settings) {
			if (isTabMenu) {
				listDataTableMenu(data, callback, settings);
			}
		},
		scrollY : _getScreenDataTableTab(),
		scroller : {
			loadingIndicator : true
		},
		pageLength : 25
	});

	$("#menuTab").on('click', function() {
		isTabMenu = true;
		tableMenu.ajax.reload();
	});

	// Button Close
	$('.buttonClose').on('click', function() {
		resetFormInputData();
	});

	// Button edit
	$('#tableMenu').on('click', ".editButton", function() {
		resetFormInputData();
		getDataOnTable(this);
		$(".modal-title").text("Edit menu");
	});
	
	// Button delete
	var idDeleteMenu = null;
	$('#tableMenu').on('click', ".deleteButton", function() {
		var idDeleteButton = $(this).attr('id');
		idDeleteMenu = idDeleteButton.replace("deleteRow", "");
		_showModalDelete("");
	});
	
	//Button delete after show modal
	$("#deleteTableRow").on("click", function() {
		if (idDeleteMenu !== null) {
			deleteDataRow(idDeleteMenu, "#tableMenu");
		}
		idDeleteMenu = null;
	});
	
	function getDataOnTable(varThis) {
		var idEditButton = $(varThis).attr('id');
		var id = idEditButton.replace("editRow", "");
		idMenu = id;
		var textMenuName, textUrl, CheckBoxIsActive;
		// find the row
		var $row = $(varThis).closest("tr");
		var $tds = $row.find("td");
		var loopColumn = 1;
		// loop the column of per row
		$.each($tds, function() {

			if (loopColumn === 4) {
				$("#nameOfMenuInput").val($(this).text());
			} else if (loopColumn === 5) {
				$("#urlInput").val($(this).text());
			}
			loopColumn += 1;
		});

		var isActive = $("#disabled" + id).is(':checked');
		$("#isActiveInput").prop("checked", isActive);

		enableFormModalInput(id);
	}

	function enableFormModalInput(id) {
		$('#addNewMenuModal').modal('show');
	}

	function resetFormInputData() {
		$("#urlInput").val("");
		$("#nameOfMenuInput").val("");
		$("#isActiveInput").prop("checked", false);
		$("#infoRestMessage").attr('class', '').text("");
		idMenu = null;
		$(".modal-title").text("Add new menu");
	}

	var idMenu = null;
	function buttonSubmitSaveEdit() {
		var url = null;
		if (!!idMenu) {
			url = '/admin/v1/api/menu/editMenu';
		} else {
			url = '/admin/v1/api/menu/saveMenu';
		}
		saveEditMenu(idMenu, url);
	}

	function jsonRequestDataEditSaver(id) {
		var nameOfMenu, urlInput, isActive;
		nameOfMenu = $("#nameOfMenuInput").val();
		urlInput = $("#urlInput").val();
		isActive = $("#isActiveInput").is(':checked');
		if(isActive == true){
			isActive = false;
		}else{
			isActive = true
		}

		var jsonRequest = {};
		var jsonData = {};

		jsonData["id"] = id;
		jsonData["menusName"] = nameOfMenu;
		jsonData["url"] = urlInput;
		jsonData["disabled"] = isActive;
		jsonRequest["requestData"] = jsonData;
		return JSON.stringify(jsonRequest);
	}

	function saveEditMenu(id, url) {
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

				if (data.statusType != "SAVE_SUCCEED") {
					$("#infoRestMessage").attr('class', 'warning-message');
				} else {
					$("#infoRestMessage").attr('class', 'success-message');
				}
				$("#infoRestMessage").text(message);
				tableMenu.ajax.reload();
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
				$("#infoRestMessage").attr('class', 'warning-message')
				$("#infoRestMessage").text(errorMessage);
			}
		});
		idMenu = null;
	}

	function listDataTableMenu(data, callback, settings) {
		$
				.ajax({
					async : true,
					type : 'POST',
					contentType : 'application/json',
					url : '/admin/v1/api/menu/list',
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
						var idUser = null;

						function buttonAction(i, idUser) {
							var buttonEditMenu = '<div class="center" style="margin-left:0%;"><input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />'
									+ '<input type = "hidden" id = "idData'
									+ idUser
									+ '" class="idDataHide'
									+ i
									+ '" /> ';

								     if(editButtonAction == editButtonActionStatic){
								     buttonEditMenu += '<button type = "submit" id = "editRow'
                                     	+ idUser
                                     	+ '" class = "btn btn-primary editButton" > Edit </button> ';
								     }

								     if(editButtonAction == editButtonActionStatic){
								      	buttonEditMenu += '<button type = "submit" id = "deleteRow'
                                      	+ idUser
                                      	+ '" class = "btn btn-danger deleteButton" > Delete </button></div>';
								     }
                        return buttonEditMenu;

						}

						for (var i = 0, ien = dataResponse.responseData.data.length; i < ien; i++) {
							idUser = dataResponse.responseData.data[i].id;
							out
									.push([
											_getNumberOfRow(data.start, i),
											dataResponse.responseData.data[i].createdTime,
											dataResponse.responseData.data[i].modifiedTime,
											dataResponse.responseData.data[i].menusName,
											dataResponse.responseData.data[i].url,
											_checkBoxCustom(
													idUser,
													dataResponse.responseData.data[i].disabled,
													"disabled"),
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

	function deleteDataRow(id, tableReload) {
		$.ajax({
			type : "DELETE",
			contentType : "application/json",
			url : "/admin/v1/api/menu/deleteMenu",
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},
			data : _jsonRequestDeleteData(id),
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				if (data.statusType == "DELETE_SUCCEED") {
					var tr = $('#rowId' + data.id).closest('tr');
					tr.css("background-color", "#00acd6");
					tr.fadeOut(400, function() {
						tr.remove();
					});
					$(tableReload).DataTable().ajax.reload();
					_hideModalDelete();
					return true;
				}else{
					_replaceModalDelete(null);
					return false;
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				_replaceModalDelete("Delete had been problem");
			}
		});
	}

});
