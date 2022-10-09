package project.pet.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.pet.domain.ReviewBoard;

public interface ReviewBoardSearch {

    Page<ReviewBoard> search1(Pageable pageable);

    Page<ReviewBoard> searchAll(String[] types, String keyword, Pageable pageable);
}
