package project.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.pet.domain.ComuBoard;
import project.pet.repository.search.ComuBoardSearch;

public interface ComuBoardRepository extends JpaRepository<ComuBoard, Long>, ComuBoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
