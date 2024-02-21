package com.hyundai.app.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 엄상은
 * @since 2024/02/21
 * 페이징 처리를 위한 id가 포함된 Criteria
 */
@Getter
@AllArgsConstructor
public class IdWithCriteria {
    private int storeId;
    private int pageNum;
    private int amount;

    public static IdWithCriteria of(int storeId, int pageNum, int amount) {
        return new IdWithCriteria(storeId, pageNum, amount);
    }
}
