package org.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

@Component
public class DataBase { 
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void init(){
        String sql ="create table if not exists dictionery (" +
                "id serial primary key," +
                "uz_name varchar," +
                "eng_name varchar," +
                "description varchar" +
                ")";

                jdbcTemplate.execute(sql);
    }
}
