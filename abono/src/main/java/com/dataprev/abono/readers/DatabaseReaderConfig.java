package com.dataprev.abono.readers;

import com.dataprev.abono.mappers.PagamentoDatabaseRowMapper;
import com.dataprev.abono.models.Pagamento;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
public class DatabaseReaderConfig {
    @Bean
    public <T> ItemStreamReader<T> databaseReader(DataSource dataSource, String query, RowMapper<T> rowMapper) {
        JdbcCursorItemReader<T> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql(query);
        reader.setRowMapper(rowMapper);
        return reader;
    }
}