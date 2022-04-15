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

	$("#saveDaftarPelaku").on(
			'click',
			function() {

                var daftarIdPelaku = $('#daftarIdPelaku').val();
				var daftarPelakuNama = $('#daftarPelakuNama').val();
				var daftarPelakuRumusSidikJari = $('#daftarPelakuRumusSidikJari').val();
				var daftarPelakuAlamat = $('#daftarPelakuAlamat').val();
				var daftarPelakuTanggalLahir = $('#daftarPelakuTanggalLahir').val();
				var daftarPelakuNoKtp = $('#daftarPelakuNoKtp').val();
				var daftarPelakuNoLp = $('#daftarPelakuNoLp').val();
				var daftarPelakuModus = $('#daftarPelakuModus').val();
				var daftarPelakuPasal = $('#daftarPelakuPasal').val();
				var daftarPelakuTKP = $('#daftarPelakuTKP').val();
				var daftarPelakuNote = $('#daftarPelakuNote').val();

				if(daftarIdPelaku == '' || daftarIdPelaku == '-'){
				    daftarIdPelaku = null
				}

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

				saveDaftarPelaku(jsonAddData(vInsert, vDelete, vUpdate, vDisable,
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
					url : "../api/daftar/pelaku/list/" + _idRole(),
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
									        dataResponse.responseData.data[i].personalIdentity.photo,
											dataResponse.responseData.data[i].personalIdentity.id,
											dataResponse.responseData.data[i].personalIdentity.name,
											dataResponse.responseData.data[i].personalIdentity.fingerprint,
											dataResponse.responseData.data[i].personalIdentity.address,
											dataResponse.responseData.data[i].personalIdentity.bornDate,
											dataResponse.responseData.data[i].personalIdentity.noKtp,
											dataResponse.responseData.data[i].noLaporan,
											dataResponse.responseData.data[i].pathFileBAP,
											dataResponse.responseData.data[i].lokasiLapas,
											dataResponse.responseData.data[i].tkp,
											dataResponse.responseData.data[i].tanggalTkp,
											dataResponse.responseData.data[i].anggotaKomplotan,
											dataResponse.responseData.data[i].modus,
											dataResponse.responseData.data[i].pasal,
											dataResponse.responseData.data[i].personalIdentity.statusPelaku,
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

	$("#uploadFoto").on("change", function (e) {
        var file = $(this)[0].files[0];
        var upload = new Upload(file, "../api/daftar/pelaku/uploadFoto");
        upload.doUpload();
    });

    $("#uploadBAP").on("change", function (e) {
            var file = $(this)[0].files[0];
            var upload = new Upload(file, "../api/daftar/pelaku/uploadBAP");
            upload.doUpload();
        });

	var Upload = function (file, urlString) {
        this.file = file;
        this.urlString = urlString;
    };

    Upload.prototype.getType = function() {
        return this.file.type;
    };
    Upload.prototype.getSize = function() {
        return this.file.size;
    };
    Upload.prototype.getName = function() {
        return this.file.name;
    };

    Upload.prototype.doUpload = function () {
        var that = this;
        var formData = new FormData();

        // add assoc key values, this will be posts values
        formData.append("file", this.file, this.getName());
        formData.append("upload_file", true);

        $.ajax({
            type: "POST",
            url: ""+this.urlString,
            headers : {
            						'X-XSRF-TOKEN' : $("#csrfToken").val()
            					},
            xhr: function () {
                var myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) {
                    myXhr.upload.addEventListener('progress', that.progressHandling, false);
                }
                return myXhr;
            },
            success: function (data) {
                // your callback here
            },
            error: function (error) {
                // handle error
            },
            async: true,
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            timeout: 60000
        });
    };

    Upload.prototype.progressHandling = function (event) {
        var percent = 0;
        var position = event.loaded || event.position;
        var total = event.total;
        var progress_bar_id = "#progress-wrp";
        if (event.lengthComputable) {
            percent = Math.ceil(position / total * 100);
        }
        // update progressbars classes so it fits your code
        $(progress_bar_id + " .progress-bar").css("width", +percent + "%");
        $(progress_bar_id + " .status").text(percent + "%");
    };

	var tableAuthorization = $('#tableDaftarPelaku').DataTable({
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
		scrollX : 2000,
		scroller : {
			loadingIndicator : true
		},
		pageLength : 25
	});

	$("#submitAction").on('click', function() {
		tableAuthorization.ajax.reload();
	});

	function saveDaftarPelaku(jsonRequest) {
		var csrfToken = $("#csrfToken").val();
		$.ajax({
			type : "POST",
			url : "../api/authorization/save/",
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