package com.zero2oneit.mall.system.dictionary.vo;

import lombok.Data;

@Data
public class CodeVO {

    private Long id;

    private Long userId;

    private String codeTable;

    private String codeField;

    private String codeDetail;

    private Integer codeNum;

    private String codeName;

}
