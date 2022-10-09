package project.pet.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import project.pet.domain.QReviewBoard;
import project.pet.domain.ReviewBoard;

import java.util.List;

public class ReviewBoardSearchImpl extends QuerydslRepositorySupport implements ReviewBoardSearch{

    public ReviewBoardSearchImpl(){
        super(ReviewBoard.class);
    }

    @Override
    public Page<ReviewBoard> search1(Pageable pageable){

        QReviewBoard reviewBoard = QReviewBoard.reviewBoard;

        JPQLQuery<ReviewBoard> query = from(reviewBoard);

        query.where(reviewBoard.title.contains("1"));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<ReviewBoard> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<ReviewBoard> searchAll(String[] types, String keyword, Pageable pageable){

        QReviewBoard reviewBoard = QReviewBoard.reviewBoard;
        JPQLQuery<ReviewBoard> query = from(reviewBoard);

        if((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(reviewBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(reviewBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(reviewBoard.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(reviewBoard.bnum.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<ReviewBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
