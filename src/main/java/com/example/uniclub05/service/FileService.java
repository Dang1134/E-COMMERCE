package com.example.uniclub05.service;

import com.example.uniclub05.exception.FileNotFoundException;
import com.example.uniclub05.exception.SaveFileException;
import com.example.uniclub05.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service

public class FileService implements FileServiceImp {
    @Value("${path.save-file}")
    private String pathSaveFile;

    @Override
    public boolean saveFile(MultipartFile file) {
        try{
            Path root = Paths.get(pathSaveFile);
            if(!Files.exists(root)){
                Files.createDirectory(root);
            }// dieu kien neu k co folder luu tru hinh anh thi no se tu tao folder do
            // resolve = dau /
            // StandardCopyOption.REPLACE_EXISTING : ghi de len hinh anh giong ten
            Files.copy(file.getInputStream(),root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return  true ;

        } catch (Exception e){
           throw new SaveFileException();
        }
    }

    @Override
    public Resource load(String fileName) {
        try{
            Path root = Paths.get(pathSaveFile).resolve(fileName);
            Resource resource = new UrlResource(root.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource ;
            }else {
                throw new FileNotFoundException();
            }
        }catch (Exception e){
            throw new FileNotFoundException(e.getMessage());
        }



    }
}
