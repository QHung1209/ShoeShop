package com.example.demo.service.Admin.imp;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
public interface FileAdminServiceImp {
    boolean saveFile(MultipartFile file);
    Resource loadFile(String filename);

}
