package com.example.uniclub05.controller;

import com.example.uniclub05.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")

public class FIleController {

    @Autowired
    private FileServiceImp fileServiceImp ;
    // dowload file tu du an

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename){
        Resource file = fileServiceImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
