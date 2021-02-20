package com.quickstudy.api.admin.entity.word;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class Word {
    private Integer id ;
    private String en;
    private String cn;
    private String status;
    private int score;
    private int viewCount;
    private int type;

    private String typeName;

}
