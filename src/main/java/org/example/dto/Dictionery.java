package org.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Dictionery {
    private Integer id;
    private String wordInUzb;
    private String wordInEng;
    private String description;

}
