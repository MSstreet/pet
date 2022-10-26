package project.pet.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import project.pet.domain.ComuBoard;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ComuBoardRepositoryTests {

    @Autowired
    private ComuBoardRepository comuBoardRepository;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            ComuBoard comuBoard = ComuBoard.builder()
                    .title("title..."+i)
                    .content("content..."+ i)
                    .writer("user"+(i%10))
                    .build();

            ComuBoard result = comuBoardRepository.save(comuBoard);
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    public void testSelect(){
        Long bno = 100L;

        Optional<ComuBoard> result = comuBoardRepository.findById(bno);

        ComuBoard comuBoard = result.orElseThrow();

        log.info(comuBoard);
    }

    @Test
    public void testSearch2(){
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

        comuBoardRepository.search2(pageable);
    }

//    @Test
//    public void testSearch1(){
//        Pageable pageable = PageRequest.of(1,10, Sort.by("bnum").descending());
//
//        comuBoardRepository.search1(pageable);
//    }
}
