package com.common.common.Uploadfiles;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Upload {

    /**
     * 单文件上传
     * @param FilePath 保存路径
     * @param file 文件
     * @return
     */
    public String upload(String FilePath, MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(FilePath, fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {

        }
        return "上传失败！";

    }

    /**
     * 多文件上传
     * @param FilePath
     * @param files
     * @return
     */
    public String multiUpload(String FilePath, List<MultipartFile> files){
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(FilePath , fileName);
            try {
                file.transferTo(dest);
                //LOGGER.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
               // LOGGER.error(e.toString(), e);
                return "上传第" + (i++) + "个文件失败";
            }
        }

        return "上传成功";

    }
}
