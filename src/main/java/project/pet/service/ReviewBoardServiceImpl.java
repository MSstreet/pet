package project.pet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.pet.domain.ReviewBoard;
import project.pet.dto.ReviewBoardDTO;
import project.pet.repository.ReviewBoardRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewBoardServiceImpl implements ReviewBoardService {

    private final ModelMapper modelMapper;

    private final ReviewBoardRepository reviewBoardRepository;

    @Override
    public Long resister(ReviewBoardDTO reviewBoardDTO){

        ReviewBoard reviewBoard = modelMapper.map(reviewBoardDTO, ReviewBoard.class);

        Long bnum = reviewBoardRepository.save(reviewBoard).getBnum();

        return bnum;
    }

    @Override
    public  ReviewBoardDTO readOne(Long bnum){

        Optional<ReviewBoard> result = reviewBoardRepository.findById(bnum);

        ReviewBoard reviewBoard = result.orElseThrow();

        ReviewBoardDTO reviewBoardDTO = modelMapper.map(reviewBoard,ReviewBoardDTO.class);

        return reviewBoardDTO;
    }

    @Override
    public void modify(ReviewBoardDTO reviewBoardDTO){

        Optional<ReviewBoard> result = reviewBoardRepository.findById(reviewBoardDTO.getBnum());

        ReviewBoard reviewBoard = result.orElseThrow();

        reviewBoard.change(reviewBoardDTO.getTitle(), reviewBoardDTO.getContent());

        reviewBoardRepository.save(reviewBoard);
    }

    @Override
    public void remove(Long bnum){
        reviewBoardRepository.deleteById(bnum);
    }
}
