package com.dataprev.abono.errors;

import com.dataprev.abono.dtos.PagamentoReportDto;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

@Component
public class WriteErrorListener {
    @OnSkipInWrite
    public void onSkipInWrite(PagamentoReportDto pagamentoReportDto, Throwable th) {
        if(th instanceof FlatFileParseException) {
            createFile("src/main/resources/write_errors.txt", ((FlatFileParseException) th).getInput());
        }
    }

    private void createFile(String filePath, String data) {
        try(FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
            fileWriter.write(data + "\n");
        }
        catch (Exception e) { }
    }
}
