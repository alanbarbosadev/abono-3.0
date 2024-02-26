package com.dataprev.abono.services;

import com.dataprev.abono.models.Arquivo;
import com.dataprev.abono.repositories.ArquivoRepository;
import com.dataprev.abono.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArquivoService {
    @Autowired
    private ArquivoRepository arquivoRepository;

    public String uploadArquivo(MultipartFile arquivo) throws IOException {
        var arquivoParaSalvar = new Arquivo();

        arquivoParaSalvar.setNome(arquivo.getOriginalFilename());
        arquivoParaSalvar.setTipo(arquivo.getContentType());
        arquivoParaSalvar.setArquivoData(Util.compressFile(arquivo.getBytes()));

        var arquivoSalvo = arquivoRepository.save(arquivoParaSalvar);

        if(arquivoSalvo != null) return "Arquivo Salvo com Sucesso!";

        return null;
    }

    public byte[] downloadArquivo(Long id) {
        var arquivoOptional = arquivoRepository.findById(id);

        var arquivo = Util.decompressFile(arquivoOptional.get().getArquivoData());

        return arquivo;
    }
}
