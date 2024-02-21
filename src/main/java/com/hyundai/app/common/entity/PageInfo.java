package com.hyundai.app.common.entity;

/**
 * @author 엄상은
 * @since 2024/02/21
 * 페이지 정보 계산용 클래스
 */
public class PageInfo {
        private int startPage;
        private int endPage;
        private boolean prev, next;
        private int total;
        private IdWithCriteria idWithCriteria;

        public PageInfo(IdWithCriteria idWithCriteria, int total) {
            this.idWithCriteria = idWithCriteria;
            this.total = total;

            this.endPage = (int) (Math.ceil(idWithCriteria.getPageNum() / 5.0)) * 5;
            this.startPage = this.endPage - 4;

            int realEnd = (int) (Math.ceil((total * 1.0) / idWithCriteria.getAmount()));

            if (realEnd < this.endPage) {
                this.endPage = realEnd;
            }

            this.prev = this.startPage > 1;
            this.next = this.endPage < realEnd;
        }

        public int getStartPage() {
            return startPage;
        }

        public int getEndPage() {
            return endPage;
        }

        public boolean isPrev() {
            return prev;
        }

        public boolean isNext() {
            return next;
        }

        public int getTotal() {
            return total;
        }

        public IdWithCriteria getIdWithCriteria() {
            return idWithCriteria;
        }
}
