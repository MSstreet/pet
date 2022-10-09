package project.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.pet.domain.ReviewBoard;
import project.pet.repository.search.ReviewBoardSearch;

public interface ReviewBoardRepository extends JpaRepository<ReviewBoard, Long>, ReviewBoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
