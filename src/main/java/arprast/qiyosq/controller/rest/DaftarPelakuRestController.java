package arprast.qiyosq.controller.rest;

import arprast.qiyosq.dto.*;
import arprast.qiyosq.service.resmob.DaftarPelakuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class DaftarPelakuRestController {

    private static final Logger logger = LoggerFactory.getLogger(DaftarPelakuRestController.class);
    private static String UPLOADED_FOLDER = "/Users/ariprasetiyo/Downloads/";
    @Autowired
    DaftarPelakuService daftarPelakuService;

    @RequestMapping(value = "/admin/v1/api/daftar/pelaku/update/{id}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDto> authorizationUpdate(@PathVariable("id") Long id,
                                                           @RequestBody RequestDto<AuthorizationDto> requestDto) {

        int inUpdate = daftarPelakuService.updateAuthorization(id, requestDto.getRequestData());

        ResponseDto responseDto = new ResponseDto();
        ResponseData responseData = new ResponseData();
        responseData.setId(id);
        responseData.setTotalRecord(inUpdate);
        responseDto.setResponseData(responseData);

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/v1/api/daftar/pelaku/uploadFoto", method = RequestMethod.POST)
    public String uploadPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            logger.debug("file empty");
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            logger.debug("You successfully uploaded {} {}", path.toAbsolutePath(), path.getFileName());
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @RequestMapping(value = "/admin/v1/api/daftar/pelaku/uploadBAP", method = RequestMethod.POST)
    public String uploadBAP(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            logger.debug("file empty");
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            logger.debug("You successfully uploaded {} {}", path.toAbsolutePath(), path.getFileName());
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

//    @RequestMapping(value = "/admin/v1/api/daftar/pelaku/save", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,
//            MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE,
//            MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<ResponseDto> authorizationSave(@RequestBody RequestDto<DaftarPelakuDto> requestDto) {
//        DaftarPelakuDto daftarPelakuDto = new DaftarPelakuDto();
//        daftarPelakuDto = requestDto.getRequestData();
//        return daftarPelakuService.saveDaftarPelaku(daftarPelakuDto);
//    }

    @RequestMapping(value = "/admin/v1/api/daftar/pelaku/list/{idRole}", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ResponseDto> getDaftarPelaku(@PathVariable("idRole") Long idRole,
                                                       @RequestBody RequestDto<RequestData> requestDto) {

        ResponseDto responseDto = new ResponseDto();
        ResponseData responseData = new ResponseData();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            responseData.setTotalRecord(daftarPelakuService.countAuthorization(requestDto.getRequestData().getId()));
        });

        List<DaftarPelakuDto> daftarPelakuList = daftarPelakuService
                .getDaftarPelakuList(requestDto.getRequestData());
        responseData.setData(daftarPelakuList);

        responseDto.setResponseData(responseData);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/v1/api/daftar/pelaku/deleteMenu/{idAuthorization}", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ResponseDto> authorizationDelete(@PathVariable("idAuthorization") Long id,
                                                           @RequestBody RequestDto<AuthorizationDto> requestDto) {

        daftarPelakuService.deleteAuthorization(requestDto.getRequestData().getId());
        ResponseDto responseDto = new ResponseDto();
        ResponseData responseData = new ResponseData();
        responseData.setId(id);
        responseDto.setResponseData(responseData);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

}
