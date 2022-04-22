package Medical.MedicalRecord.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Paging Control
 */
public class Pagination {

    private int dataPerPageSize = 10;
    private int buttonPerPageSize = 10;

    private int nowPage = 1;
    private int nowButton = 1;

    private int totalDataCount;
    private int totalPageCount;
    private int totalButtonCount;

    private int startButton = 1;
    private int endButton = 1;

    private int startDbIndex = 0;
    private int prevButton;
    private int nextButton;

    public Pagination(int totalDataCount, int nowPage) {
        // 총 게시물 수와 현재 페이지를 Controller로 부터 받아온다.
        setNowPage(nowPage);
        setTotalDataCount(totalDataCount);

        if (totalDataCount == 0) {
            setTotalPageCount(1);
            setTotalButtonCount(1);
        } else {
            setTotalPageCount((int) Math.ceil(totalDataCount * 1.0 / dataPerPageSize));
            setTotalButtonCount((int) Math.ceil(totalDataCount * 1.0 / buttonPerPageSize));
        }
        /** 4. 현재 버튼 **/
        // 현재 페이지 * 1.0을 블록의 최대 개수로 나누어주고 올림한다.
        // 현재 블록을 구할 수 있다.
        setNowButton((int) Math.ceil((nowPage * 1.0) / buttonPerPageSize));
        /** 8. 버튼 시작 페이지 **/
        setStartButton((nowButton - 1) * buttonPerPageSize + 1);
        /** 9. 버튼 마지막 페이지 **/
        setEndButton(startButton + buttonPerPageSize - 1);

        /* === 블럭 마지막 페이지에 대한 validation ===*/
        if (endButton > totalPageCount) {
            this.endButton = totalPageCount;
        }

        /** 데이터 없을 경우 **/
        if (totalPageCount == 0) {
            this.endButton = 1;
        }

        /** 11. 이전 블럭(클릭 시, 이전 블럭 마지막 페이지) **/
        setPrevButton((nowButton * buttonPerPageSize) - buttonPerPageSize);

        /* === 이전 블럭에 대한 validation === */
        if (prevButton < 1) {
            this.prevButton = 1;
        }

        /** 12. 다음 블럭(클릭 시, 다음 블럭 첫번째 페이지) **/
        setNextButton((nowButton * buttonPerPageSize) + 1);

        /* === 다음 블럭에 대한 validation ===*/
        if (nextButton > totalPageCount) {
            nextButton = totalPageCount;
        }

        /** 10. DB 접근 시작 index **/
        if (nowPage == 0)
            setStartDbIndex(0);
        else
            setStartDbIndex((nowPage - 1) * dataPerPageSize);
    }
}

