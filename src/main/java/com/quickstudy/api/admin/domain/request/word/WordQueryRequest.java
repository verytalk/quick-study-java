package com.quickstudy.api.admin.domain.request.word;

import com.quickstudy.api.admin.domain.request.ListPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jason
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WordQueryRequest extends ListPageRequest {

    private String word;
    private int id;
    private String type;

}
