package com.template.plus.minio.controller;

import com.template.plus.common.framework.common.api.ApiResult;
import com.template.plus.minio.entity.MinioUploadResult;
import com.template.plus.minio.service.MinioServerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MinioServerController {

    private final MinioServerService minioServerService;

    public MinioServerController(MinioServerService minioServerService) {
        this.minioServerService = minioServerService;
    }

    @PostMapping("file")
    public ApiResult<MinioUploadResult> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String applicationName) {
        return ApiResult.ok(minioServerService.uploadFile(file, applicationName, null, null));
    }

    @PostMapping("file_meta")
    public ApiResult<MinioUploadResult> uploadFileWithSpecifyMeta(@RequestParam("file") MultipartFile file, @RequestParam String applicationName, @RequestParam String directory, @RequestParam(required = false) String name) {
        return ApiResult.ok(minioServerService.uploadFileWithSpecifyMeta(file, applicationName, directory, name));
    }


    @PostMapping("base_64")
    public ApiResult<MinioUploadResult> uploadFile(@RequestBody String baseContent, @RequestParam String applicationName) throws IOException {
        return ApiResult.ok(minioServerService.uploadBase64File(baseContent, applicationName));
    }

    @PostMapping("preview")
    public void downloadFileFromMinio(@RequestBody String relativePath, @RequestParam String applicationName, HttpServletResponse response) {
        minioServerService.downloadFileFromMinio(relativePath, applicationName, response);
    }

    @GetMapping("preview")
    public void onlinePreview(@RequestParam String relativePath, @RequestParam String applicationName, HttpServletResponse response) {
        minioServerService.downloadFileFromMinio(relativePath, applicationName, response);
    }

    @GetMapping("preview_url")
    public ApiResult<String> onlinePreview(@RequestParam String relativePath, @RequestParam String applicationName) {
        return ApiResult.ok(minioServerService.getPreviewUrl(relativePath, applicationName));
    }

}
