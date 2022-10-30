package project.pet.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import project.pet.domain.ComuBoard;
import project.pet.domain.Reply;
import project.pet.domain.ReviewBoard;

import javax.transaction.Transactional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert(){

        Long bno = 90L;

        ComuBoard comuBoard = ComuBoard.builder().bno(bno).build();

        Reply reply = Reply.builder()
                .comuBoard(comuBoard)
                .replyText("댓글.....")
                .replyer("replyer1")
                .build();

        replyRepository.save(reply);
    }

    @Transactional
    @Test
    public void testBoardReplies() {

        Long bno = 100L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.listOfReviewBoard(bno, pageable);

        result.getContent().forEach(reply -> {
            log.info(reply);
        });
    }
}
