package com.quickstudy.api.admin.domain.request.word;

import com.quickstudy.api.admin.domain.request.ListPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jason
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WordDataRequest extends ListPageRequest {

    private int id;
    private String type;
    private String en;
    private String cn;
}
