package com.template.plus.minio.service;

import cn.fanzy.minio.MinioConfiguration;
import cn.fanzy.minio.model.MinioResp;
import cn.fanzy.minio.service.MinioService;
import com.template.plus.common.framework.common.exception.BusinessException;
import com.template.plus.minio.entity.MinioUploadResult;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class MinioServerService {

    private final String FILE_SEPARATE = "$$";

    public MinioUploadResult uploadFile(MultipartFile file, String applicationName, String directory, String fileName) {
        MinioUploadResult result = new MinioUploadResult();
        try {
            MinioService minioService = MinioConfiguration.getMinioService(applicationName);
            MinioClient client = minioService.getClient();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String folderName = Objects.isNull(directory) ? simpleDateFormat.format(new Date()) : directory;

            if (doesFolderNotExist(client, minioService.getBucketName(), folderName)) {
                putDirObject(client, minioService.getBucketName(), folderName.concat("/"));
            }
            String targetFileName = Objects.isNull(fileName) ? getFileUniquePath(folderName, FilenameUtils.getBaseName(file.getOriginalFilename()), FilenameUtils.getExtension(file.getOriginalFilename())) : getSpecifyPath(directory, fileName);
            MinioResp minioResp = minioService.upload(file.getInputStream(), targetFileName, file.getContentType());
            result.setFullUrl(minioResp.getPreviewUrl());
            result.setUrl(minioResp.getObjectName());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("上传失败");
        }
        return result;
    }

    public String getPreviewUrl(String relativePath, String applicationName) {
        MinioService minioService = MinioConfiguration.getMinioService(applicationName);
        return minioService.getObjectUrl(relativePath);
    }


    public void downloadFileFromMinio(String relativePath, String applicationName, HttpServletResponse response) {
        try {
            MinioService minioService = MinioConfiguration.getMinioService(applicationName);
            InputStream inputStream = minioService.getObject(relativePath);
            IOUtils.copy(inputStream, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            inputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("下载失败");
        }
    }

    public MinioUploadResult uploadBase64File(String baseContent, String applicationName) {
        MinioUploadResult result = new MinioUploadResult();
        result.setStatus(200);
        try {
            baseContent = StringUtils.remove(baseContent, "data:image/png;base64,");
            byte[] decodedBytes = Base64.getDecoder().decode(baseContent);
            InputStream inputStream = new ByteArrayInputStream(decodedBytes);
            MinioService minioService = MinioConfiguration.getMinioService(applicationName);
            MinioClient client = minioService.getClient();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String folderName = simpleDateFormat.format(new Date());
            if (doesFolderNotExist(client, minioService.getBucketName(), folderName)) {
                putDirObject(client, minioService.getBucketName(), folderName.concat("/"));
            }

            MinioResp minioResp = minioService.upload(inputStream, getFileUniquePath(folderName, "img", "png"), "image/png");
            result.setStatus(200);
            result.setUrl(minioResp.getObjectName());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("上传失败");
        }


        return result;
    }


    public boolean doesFolderNotExist(MinioClient minioClient, String bucketName, String objectName) {
        boolean exist = false;
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());

            for (Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && StringUtils.equalsIgnoreCase(objectName.concat("/"), item.objectName())) {
                    exist = true;
                    break;
                }
            }
        } catch (Exception ignored) {
        }
        return !exist;
    }

    public ObjectWriteResponse putDirObject(MinioClient minioClient, String bucketName, String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                new ByteArrayInputStream(new byte[]{}), 0, -1)
                        .build());
    }

    private String getFileUniquePath(String folder, String fileBaseName, String extention) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmssS");
        return String.format("%s/%s" + FILE_SEPARATE + "%s.%s", folder, fileBaseName, simpleDateFormat.format(new Date()), extention);
    }


    private String getSpecifyPath(String directory, String fileName) {
        return directory.concat("/").concat(fileName);
    }

    public MinioUploadResult uploadFileWithSpecifyMeta(MultipartFile file, String applicationName, String directory, String name) {
        return uploadFile(file, applicationName, directory, name);
    }
}
