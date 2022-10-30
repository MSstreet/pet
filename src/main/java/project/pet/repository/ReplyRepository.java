package project.pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.pet.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.comuBoard.bno = :bno")
    Page<Reply> listOfReviewBoard(@Param("bno")Long bno, Pageable pageable);

    //review_board로 하니까 안되는구나 ,,, reviewBoard 이게 데이터베이스에 review_board로 되있어서 근데 데이터베이스가 아니라
}
