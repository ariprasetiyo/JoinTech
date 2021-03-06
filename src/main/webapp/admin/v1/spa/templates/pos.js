$(function() {
	var csrfToken = $('#csrfToken').val();
	_screenTabSize();
	var uudIdValue = "";

	function getUUID(){
            if(uudIdValue === null || uudIdValue === '' || uudIdValue === 'undefined'){
                uudIdValue = _uuid();
            }
            return uudIdValue;
     }

	$("#inputQtyInModal").numeric();
	$("#customerNameInputId").valid();
	$("#addItemPOS").validate({
        rules : {
            nameOfItem : {
                minlength : 3,
                required : true
            },
            nameOfItemQty : {
                min : 1,
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
            addItemPOS();
        }
    });

	$("#tableMasterItems")
        .on(
                'click',
                '.addGoodsItem',
                function() {
                var itemCode = $(this).attr('id').replace('itemCodeButton', '');
                $("#itemCodeInModal").val(itemCode);
                $("#inputQtyInModal").val("");
                $("#infoRestMessage").text("");
                $("#infoRestMessage").attr('class', '')
                getDataOnTable(this);
                $('#addQty').modal('show');
	});

	$("#tmpTrxListTable")
        .on(
                'click',
                '.codeCustomerIdView',
                function() {
                uudIdValue = $(this).attr('id').replace('codeCustomerId', '');
                sourceGetPosTmpTransaction = "getPosHeaderAndTrxTmp";
                 tablePosTmpList.ajax.reload();
                $('#getItemTmpPos').modal('hide');
    });

	$("#getTmpSalesButton").on('click', function(){
	      $('#getItemTmpPos').modal('show');
	      isShowDataTrxTmpList= true;
	      varTmpTrxListTable.ajax.reload();
     })

	function jsonRequestAddItemPOS(id, itemCode, qty) {
		var jsonRequest = {};
		var jsonData = {};

		var jsonItems = {};
        jsonItems["itemCode"] =  $("#itemCodeInModal").val();
        jsonItems["qty"] = $("#inputQtyInModal").val();;

		jsonData["id"] = id;
		jsonData["customerName"] = $("#customerNameInputId").val();
		jsonData["phoneNumber"] = $("#phoneNumberInput").val();
		jsonData["address"] = $("#customerAddressInput").val();
		jsonData["paymentMethod"] = "cash";
        jsonData["items"] = jsonItems
		jsonRequest["requestData"] = jsonData;
		jsonRequest["requestId"] = getUUID();
		return JSON.stringify(jsonRequest);
	}

	function addItemPOS() {
        $.ajax({
            type : 'POST',
            url : '/admin/v1/api/pos/item/add',
            contentType : 'application/json',
            data : jsonRequestAddItemPOS(),
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            },
            datatype : 'json',
            success : function(data, textStatus, jqXHR) {
                var message = data.statusMessage;
                if (data.statusCode != 200) {
                    $("#infoRestMessage").attr('class', 'warning-message');
                } else {
                    $("#infoRestMessage").attr('class', 'success-message');
                }
                $("#infoRestMessage").text(message);
                tablePosTmpList.ajax.reload();
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
    }

	function listDataTable(data, callback, settings) {
		$.ajax({
            async : true,

            type : 'POST',
            contentType : 'application/json',
            url : '/admin/v1/api/inventory/master/item/list',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            },
            /*
             * data : { limit : data.length, offset : data.start, search :
             * data.search.value },
             */
            data : _jsonRequestListDataV2(data, _idRole()),
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
                                                            + '<button type = "submit" id = "itemCodeButton'
                                                            + itemCode
                                                            + '" class = "btn btn-primary addGoodsItem" > Add </button> ';
                    }
                    return '';
                }

                for (var i = 0, ien = dataResponse.responseData.data.length; i < ien; i++) {
                    itemCode = dataResponse.responseData.data[i].itemCode;
                    out
                            .push([
                                    _getNumberOfRow(data.start, i),
                                    _center(_convertDate(dataResponse.responseData.data[i].modifiedTime)),
                                    _center(dataResponse.responseData.data[i].itemCodeLabel),
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

	function listTmpItemDataTableJsonRequest(data, id) {
    	var jsonRequest = {};
    	jsonRequest["requestId"] = uudIdValue;
    	var request = {};
    	request["id"] = id;
    	request["limit"] = data.length;
    	request["offset"] = data.start;
    	request["search"] = data.search.value;
    	jsonRequest["requestData"] = request;
    	return JSON.stringify(jsonRequest);
    }

    function buildTmpTrxListJson(data, id) {
        var jsonRequest = {};
        jsonRequest["requestId"] = _uuid();
        var request = {};
        request["id"] = id;
        request["limit"] = data.length;
        request["offset"] = data.start;
        request["search"] = data.search.value;
        jsonRequest["requestData"] = request;
        return JSON.stringify(jsonRequest);
     }

     function buildTmpTrxtJson() {
        var jsonRequest = {};
        jsonRequest["requestId"] = getUUID();
        var request = {};
        return JSON.stringify(jsonRequest);
    }

    /**
    get list trx tmp
    */
	function listTmpItemDataTable(data, callback, settings) {
    		$.ajax({
                async : true,

                type : 'POST',
                contentType : 'application/json',
                url : '/admin/v1/api/pos/item/list',
                headers : {
                    'X-XSRF-TOKEN' : csrfToken
                },
                /*
                 * data : { limit : data.length, offset : data.start, search :
                 * data.search.value },
                 */
                data : listTmpItemDataTableJsonRequest(data, _idRole()),
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
                                                                + '<button type = "submit" id = "itemCodeButton'
                                                                + itemCode
                                                                + '" class = "btn btn-primary addGoodsItem" > Delete </button> ';
                        }
                        return '';
                    }

                    for (var i = 0, ien = dataResponse.responseData.data.length; i < ien; i++) {
                        itemCode = dataResponse.responseData.data[i].itemCode;
                        out
                                .push([
                                        _getNumberOfRow(data.start, i),
                                        _center(dataResponse.responseData.data[i].itemCodeLabel),
                                        _center(dataResponse.responseData.data[i].itemName),
                                        _center(dataResponse.responseData.data[i].qty),
                                        _center(dataResponse.responseData.data[i].sellPrice),
                                        _center(dataResponse.responseData.data[i].priceDetail.discountDetail.discountAmount),
                                        _center(dataResponse.responseData.data[i].totalSellPrice),
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

    /**
    get header trx tmp
    */
     function getTmpTrxTable(data, callback, settings) {
        $.ajax({
            async : true,
            type : 'POST',
            contentType : 'application/json',
            url : '/admin/v1/api/pos/transaction/tmp',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            },
            data : buildTmpTrxtJson(),
            dataType : "json",
            beforeSend : function() {},
            success : function(dataResponse, textStatus, jqXHR) {
                var out = [];
                var requestId = null;

                function buttonAction(i, requestId) {
                    if(editButtonAction == editButtonActionStatic){
                        return '<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />'
                                                            + '<input type = "hidden" id = "HeaderTrxTmpRequestId'
                                                            + requestId
                                                            + '" class="idDataHide'
                                                            + i
                                                            + '" /> '
                                                            + '<button type = "submit" id = "HeaderTrxTmpRequestId'
                                                            + requestId
                                                            + '" class = "btn btn-primary codeCustomerIdView" > Delete </button> ';
                    }
                    return '';
                }

                var requestId = dataResponse.responseData.data.headerTrx.requestId;
                var customerId = dataResponse.responseData.data.headerTrx.customerId;
                var customerName = dataResponse.responseData.data.headerTrx.customerName;
                var phoneNumber = dataResponse.responseData.data.headerTrx.phoneNumber;
                var address = dataResponse.responseData.data.headerTrx.address;
                var paymentMethod = dataResponse.responseData.data.headerTrx.paymentMethod;
                var totalTrxAmount = dataResponse.responseData.data.headerTrx.totalTrxAmount;
                var totalDiscountAmount = dataResponse.responseData.data.headerTrx.totalDiscountAmount;
                var totalPaidAmount = dataResponse.responseData.data.headerTrx.totalPaidAmount;
                var createdTime = dataResponse.responseData.data.headerTrx.createdTime;

                $("#customerIdInput").val(customerId);
                $("#createdDateInput").val(_convertDate(createdTime));
                $("#customerNameInputId").val(customerName);
                $("#phoneNumberInput").val(phoneNumber);
                $("#customerAddressInput").val(address);

                for (var i = 0, ien = dataResponse.responseData.data.detailTrxList.length; i < ien; i++) {
                    var requestId = dataResponse.responseData.data.detailTrxList[i].itemCode;
                            out.push([
                                        _getNumberOfRow(data.start, i),
                                        _center(dataResponse.responseData.data.detailTrxList[i].itemCodeLabel),
                                        _center(dataResponse.responseData.data.detailTrxList[i].itemName),
                                        _center(dataResponse.responseData.data.detailTrxList[i].qty),
                                        _center(dataResponse.responseData.data.detailTrxList[i].sellPrice),
                                        _center(dataResponse.responseData.data.detailTrxList[i].priceDetail.discountDetail.discountAmount),
                                        _center(dataResponse.responseData.data.detailTrxList[i].totalSellPrice),
                                        buttonAction(i, requestId) ]);
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
                sourceGetPosTmpTransaction = null;
                $("#tmpTrxListTable_wrapper .dataTables_scroll .dataTables_scrollHead .dataTables_scrollHeadInner").css({'box-sizing': 'content-box', 'width': '890px', 'padding-right': '0px',  'margin-top':'0px'});
            },
            error : function(jqXHR, textStatus, errorThrown) {
            }
        });
    }

    function getTmpTrxListTable(data, callback, settings) {
        $.ajax({
            async : true,

            type : 'POST',
            contentType : 'application/json',
            url : '/admin/v1/api/pos/header/transaction/tmp/list',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            },
            /*
             * data : { limit : data.length, offset : data.start, search :
             * data.search.value },
             */
            data : buildTmpTrxListJson(data, _idRole()),
            dataType : "json",
            beforeSend : function() {

            },
            success : function(dataResponse, textStatus, jqXHR) {
                var out = [];
                var requestId = null;

                function buttonAction(i, requestId) {
                    if(editButtonAction == editButtonActionStatic){
                        return '<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />'
                                                            + '<input type = "hidden" id = "customerId'
                                                            + requestId
                                                            + '" class="idDataHide'
                                                            + i
                                                            + '" /> '
                                                            + '<button type = "submit" id = "codeCustomerId'
                                                            + requestId
                                                            + '" class = "btn btn-primary codeCustomerIdView" > View Detail </button> ';
                    }
                    return '';
                }

                for (var i = 0, ien = dataResponse.responseData.data.length; i < ien; i++) {
                    requestId = dataResponse.responseData.data[i].requestId;
                    out
                            .push([
                                    _getNumberOfRow(data.start, i),
                                    _center(_convertDate(dataResponse.responseData.data[i].createdTime)),
                                    _center(dataResponse.responseData.data[i].customerId),
                                    _center(dataResponse.responseData.data[i].customerName),
                                    _center(dataResponse.responseData.data[i].phoneNumber),
                                    buttonAction(i, requestId) ]);
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

	var sourceGetPosTmpTransaction = null;
	var tablePosTmpList = $('#posTmptList').DataTable({
    		"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
    		serverSide : true,
    		ordering : false,
    		searching : true,
    		ajax : function(data, callback, settings) {
    		    if(sourceGetPosTmpTransaction === null){
    		        listTmpItemDataTable(data, callback, settings);
                }else if(sourceGetPosTmpTransaction == "getPosHeaderAndTrxTmp"){
                    getTmpTrxTable(data, callback, settings);
                }
    		},
    		scrollY : _getMiniScreenDataTableTab(),
    		scroller : {
    			loadingIndicator : true
    		}
    });

    var isShowDataTrxTmpList = false;
    var varTmpTrxListTable = $('#tmpTrxListTable').DataTable({
            "sDom" : '<"top"fl>rt<"bottom"p><"clear">',
            serverSide : true,
            ordering : false,
            searching : true,
            ajax : function(data, callback, settings) {
                if(isShowDataTrxTmpList){
                    getTmpTrxListTable(data, callback, settings);
                }
            },
            scrollY : _getMiniScreenDataTableTab(),
            scroller : {
                loadingIndicator : true
            }
     });


	function getDataOnTable(varThis) {

		// find the row
		var $row = $(varThis).closest("tr");
		var $tds = $row.find("td");
		var loopColumn = 1;

		// loop the column of per row
		$.each($tds, function() {
			if (loopColumn === 3) {
				$("#itemCodeLabelInModal").val($(this).text());
			}
			if (loopColumn === 4) {
            	$("#itemNameInModal").val($(this).text());
            }
            if (loopColumn === 5) {
                $("#qtyInModal").val($(this).text());
            }
			loopColumn += 1;
		});
	}

});
