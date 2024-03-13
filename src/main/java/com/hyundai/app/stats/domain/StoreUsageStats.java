package com.hyundai.app.stats.domain;

/**
 * @author : 오수영
 * @since : 2024/03/11$
 * 통계 관련 컨트롤러
 */
import lombok.Getter;

import java.util.Date;

@Getter
public class StoreUsageStats {

    private int id;
    private Long storeId;
    private Date statsDate;
    private Integer time9To11;
    private Integer time11To13;
    private Integer time13To15;
    private Integer time15To17;
    private Integer time17To19;

}
