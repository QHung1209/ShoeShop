package com.example.demo.service.Admin;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.example.demo.service.Admin.imp.FileAdminServiceImp;



@Service
public class FileAdminService implements FileAdminServiceImp {

    private String rootPath = "src/main/resources/static/images";
    private Path root = Paths.get(rootPath);

    
    private void init() {
        try {
            root = Paths.get(rootPath);
            if (Files.notExists(root))
                Files.createDirectories(root);

        } catch (Exception e) {
                System.out.println("Error create folder root" + e.getMessage());
        }

    }

    @Override
    public boolean saveFile(MultipartFile file) {
        init();
        try {
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error save file" + e.getMessage());
            return false;
        }
    }

    @Override
    public Resource loadFile(String filename) {
        init();
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error load file" + e.getMessage());
        }
        return null;

    }
}
