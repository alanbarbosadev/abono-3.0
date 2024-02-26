package com.dataprev.abono.controllers;

import com.dataprev.abono.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/arquivo")
public class ArquivoController {
    @Autowired
    private ArquivoService arquivoService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadArquivo(@RequestParam("arquivo")MultipartFile arquivo) throws IOException {
        String result = arquivoService.uploadArquivo(arquivo);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadArquivo(@PathVariable Long id) {
        var arquivo = arquivoService.downloadArquivo(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/pdf")).body(arquivo);
    }
}
