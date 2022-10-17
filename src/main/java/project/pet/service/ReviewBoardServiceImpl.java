package project.pet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.pet.domain.ReviewBoard;
import project.pet.dto.PageRequestDTO;
import project.pet.dto.PageResponseDTO;
import project.pet.dto.ReviewBoardDTO;
import project.pet.repository.ReviewBoardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageResponseDTO<ReviewBoardDTO> list(PageRequestDTO pageRequestDTO){

        String[] types = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        Page<ReviewBoard> result = reviewBoardRepository.searchAll(types, keyword, pageable);

        List<ReviewBoardDTO> dtoList = result.getContent().stream().map(reviewBoard -> modelMapper.map(reviewBoard,
                ReviewBoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<ReviewBoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
