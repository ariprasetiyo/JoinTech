/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.controller.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.RequestDto;
import arprast.qiyosq.dto.ResponseData;
import arprast.qiyosq.dto.ResponseDto;
import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.service.UserGroupService;

@RestController
@RequestMapping(value = "/admin/v1/api/userGroup")
public class UserGroupRestController {

	@Autowired
	private UserGroupService userGroupService;

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public Future<ResponseEntity<ResponseDto>> getListUserGroup(@RequestBody @Valid RequestDto<RequestData> userGroup) {

		return CompletableFuture.supplyAsync(() -> {
			try {
				ResponseDto responseDto = new ResponseDto();
				ResponseData responseData = userGroupService.listUserGroup(userGroup.getRequestData());
				responseDto.setResponseData(responseData);
				return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseDto>(new ResponseDto(), HttpStatus.NOT_ACCEPTABLE);
		});
	}

	@RequestMapping(value = "/saveUserGroup", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public Future<ResponseEntity<ResponseDto>> saveUserGroup(@RequestBody @Valid RequestDto<RolesDto> userGroup) {

		return CompletableFuture.supplyAsync(() -> {
			try {
				ResponseDto responseDto = new ResponseDto();
				ResponseData responseData = userGroupService.saveUserGroup(userGroup.getRequestData());
				responseDto.setResponseData(responseData);
				return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseDto>(new ResponseDto(), HttpStatus.NOT_ACCEPTABLE);
		});
	}

	@RequestMapping(value = "/editUserGroup", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public Future<ResponseEntity<ResponseDto>> editUserGroup(@RequestBody @Valid RequestDto<RolesDto> userGroup) {

		return CompletableFuture.supplyAsync(() -> {
			try {
				ResponseDto responseDto = new ResponseDto();
				ResponseData responseData = userGroupService.editUserGroup(userGroup.getRequestData());
				responseDto.setResponseData(responseData);
				return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseDto>(new ResponseDto(), HttpStatus.NOT_ACCEPTABLE);
		});
	}

}
