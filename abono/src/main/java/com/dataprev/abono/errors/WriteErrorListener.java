package com.dataprev.abono.errors;

import com.dataprev.abono.dtos.PagamentoReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

import java.io.File;
import java.io.FileWriter;

public class WriteErrorListener implements ItemWriteListener<PagamentoReportDto>{

    @Override
    public void onWriteError(Exception exception, Chunk<? extends PagamentoReportDto> items){
        for (PagamentoReportDto pagamentoReportDto : items.getItems()) {
            createFile("src/main/resources/write_errors.txt", pagamentoReportDto.getCpf() + ' ' + pagamentoReportDto.getNome());
        }
    }

    private void createFile(String filePath, String data) {
        try(FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
            fileWriter.write(data + "\n");
        }
        catch (Exception e) { }
    }
}
