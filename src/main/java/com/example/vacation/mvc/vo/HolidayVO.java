package com.example.vacation.mvc.vo;

import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HolidayVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    private String year;
    private String m1;
    private String m2;
    private String m3;
    private String m4;
    private String m5;
    private String m6;
    private String m7;
    private String m8;
    private String m9;
    private String m10;
    private String m11;
    private String m12;
    private String memo;
    private String regDate;
    private String regId;
    private String updateDate;
    private String updateId;
    private String regName;

    // 추가된 필드 1
    private String stYear;
    private String edYear;

    private String searchBizDiv;
    private String searchTitle;
    private String searchRegSYmd;
    private String searchRegEYmd;
}



