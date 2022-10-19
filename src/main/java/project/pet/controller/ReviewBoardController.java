package project.pet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.pet.dto.PageRequestDTO;
import project.pet.dto.PageResponseDTO;
import project.pet.dto.ReviewBoardDTO;
import project.pet.service.ReviewBoardService;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class ReviewBoardController {

    private final ReviewBoardService reviewBoardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<ReviewBoardDTO> responseDTO = reviewBoardService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public void registerGET(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid ReviewBoardDTO reviewBoardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("board POST register......");

        if(bindingResult.hasErrors()){

            log.info("has error......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(reviewBoardDTO);

        long bnum = reviewBoardService.resister(reviewBoardDTO);

        redirectAttributes.addFlashAttribute("result", bnum);

        return "redirect:/board/list";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/read", "/modify"})
    public void read(Long bnum, PageRequestDTO pageRequestDTO, Model model){

        ReviewBoardDTO reviewBoardDTO = reviewBoardService.readOne(bnum);

        log.info(reviewBoardDTO);

        model.addAttribute("dto", reviewBoardDTO);
    }

    @PreAuthorize("principal.username == #reviewBoardDTO.writer")//principal.username = 현재 로그인된 사용자 아이디 // #reviewBoardDTO = 현재 파라미터가 수집된 DTO reviewboard 에러
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid ReviewBoardDTO reviewBoardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        log.info("board modify post......" + reviewBoardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors......");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("bnum", reviewBoardDTO.getBnum());

            return "redirect:/board/modify?+link";
        }

        reviewBoardService.modify(reviewBoardDTO);

        redirectAttributes.addFlashAttribute("result","modified");

        redirectAttributes.addAttribute("bnum", reviewBoardDTO.getBnum());  //bno -> bnum 수정

        return "redirect:/board/read";
    }
    @PreAuthorize("principal.username == #reviewBoardDTO.writer")
    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){

        log.info("remove post..." + bno);

        reviewBoardService.remove(bno);

        redirectAttributes.addFlashAttribute("result","removed");

        return "redirect:/board/list";

    }


}
