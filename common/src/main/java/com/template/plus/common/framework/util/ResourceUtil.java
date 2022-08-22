package com.template.plus.common.framework.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author lp
 * @date 2022/6/15 14:37
 */
@Component
public class ResourceUtil {

    public void downLoadResource(String path, String fileName, HttpServletResponse response) {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(path + File.separator + fileName);
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setHeader("fileName", fileName);
            response.setHeader("Access-Control-Expose-Headers", "filename");
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while( (len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            os.close();
            is.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

