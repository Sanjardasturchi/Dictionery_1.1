package org.example.repository;

import org.example.dto.Dictionery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DictioneryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Dictionery dictionery) {
        jdbcTemplate.update("insert into dictionery (uz_name,eng_name,description) values (?,?,?)",
                dictionery.getWordInUzb(),dictionery.getWordInEng(),dictionery.getDescription());
    }

    public List<Dictionery> getList() {
        RowMapper rowMapper=(rs, rowNum) -> {
            Dictionery dictionery=new Dictionery();
            dictionery.setId(rs.getInt("id"));
            dictionery.setWordInUzb(rs.getString("uz_name"));
            dictionery.setWordInEng(rs.getString("eng_name"));
            dictionery.setDescription(rs.getString("description"));
            return dictionery;
        };
        return jdbcTemplate.query("select * from dictionery",rowMapper);
    }

    public int deleteById(int id) {
       return jdbcTemplate.update("delete from dictionery where id="+id);
    }
}
