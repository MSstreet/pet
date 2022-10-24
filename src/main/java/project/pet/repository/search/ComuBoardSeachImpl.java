package project.pet.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import project.pet.domain.ComuBoard;
import project.pet.domain.QComuBoard;

import java.util.List;

public class ComuBoardSeachImpl extends QuerydslRepositorySupport implements ComuBoardSearch {

    public ComuBoardSeachImpl(){
        super(ComuBoard.class);
    }

    @Override
    public Page<ComuBoard> search1(Pageable pageable){

        QComuBoard comuBoard = QComuBoard.comuBoard;

        JPQLQuery<ComuBoard> query = from(comuBoard);

        query.where(comuBoard.title.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<ComuBoard> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<ComuBoard> searchAll(String[] types, String keyword, Pageable pageable){

        QComuBoard comuBoard = QComuBoard.comuBoard;
        JPQLQuery<ComuBoard> query = from(comuBoard);

        if((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder =new BooleanBuilder();

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(comuBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(comuBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(comuBoard.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(comuBoard.bno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<ComuBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable,count);
    }
}
