package project.pet.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.pet.dto.ReviewBoardDTO;
import project.pet.service.ReviewBoardService;

@SpringBootTest
@Log4j2
public class ReviewBoardServiceTest {

    @Autowired
    private ReviewBoardService reviewBoardService;

    @Test
    public void testRegister(){
        log.info(reviewBoardService.getClass().getName());

        ReviewBoardDTO reviewBoardDTO = ReviewBoardDTO.builder().title("Sample Title...").content("Sample Content").writer("user00").build();

        Long bnum = reviewBoardService.resister(reviewBoardDTO);

        log.info("bnum: " + bnum);
    }

    @Test
    public void testModify(){

        ReviewBoardDTO reviewBoardDTO = ReviewBoardDTO.builder().bnum(101L).title("Updated...101").content("Updated content 101...").build();

        reviewBoardService.modify(reviewBoardDTO);
    }
}
