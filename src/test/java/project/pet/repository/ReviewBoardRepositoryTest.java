package project.pet.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import project.pet.domain.ReviewBoard;
import project.pet.dto.ReviewBoardListReplyCountDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewBoardRepositoryTest {

    @Autowired
    private ReviewBoardRepository reviewBoardRepository;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            ReviewBoard reviewBoard = ReviewBoard.builder().title("title..."+i).content("content..."+i).writer("user"+(i % 10)).build();

            ReviewBoard result = reviewBoardRepository.save(reviewBoard);
            log.info("BNUM: " + result.getBnum());
        });
    }

    @Test
    public void testSelect(){
        Long bnum = 100L;

        Optional<ReviewBoard> result = reviewBoardRepository.findById(bnum);

        ReviewBoard reviewBoard = result.orElseThrow();

        log.info(reviewBoard);
    }

    @Test
    public void testUpdate(){

        Long bnum = 100L;

        Optional<ReviewBoard> result = reviewBoardRepository.findById(bnum);

        ReviewBoard reviewBoard = result.orElseThrow();

        reviewBoard.change("update...title 100", "update content 100");

        reviewBoardRepository.save(reviewBoard);
    }

    @Test
    public void testDelete(){
        Long bnum = 1L;

        reviewBoardRepository.deleteById(bnum);
    }

    @Test
    public void testPaging(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bnum").descending());

        Page<ReviewBoard> result = reviewBoardRepository.findAll(pageable);

        log.info("total count: " +result.getTotalElements());
        log.info("total pages:" + result.getTotalPages());
        log.info("page number: "+ result.getNumber());
        log.info("page size:"+result.getSize());

        List<ReviewBoard> todoList = result.getContent();

        todoList.forEach(reviewBoard -> log.info(reviewBoard));
    }

    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(1, 10, Sort.by("bnum").descending());

        reviewBoardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll(){

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bnum").descending());

        Page<ReviewBoard> result = reviewBoardRepository.searchAll(types, keyword, pageable);
    }

    @Test
    public void testSearchAll2(){

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bnum").descending());

        Page<ReviewBoard> result = reviewBoardRepository.searchAll(types, keyword, pageable);

        log.info(result.getTotalPages());

        log.info(result.getSize());

        log.info(result.getNumber());

        log.info(result.hasPrevious() +": " + result.hasNext());

        result.getContent().forEach(reviewBoard -> log.info(reviewBoard));
    }




}
