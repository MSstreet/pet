package project.pet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.pet.dto.ComuBoardDTO;
import project.pet.dto.PageRequestDTO;
import project.pet.dto.PageResponseDTO;
import project.pet.service.ComuBoardService;

import javax.validation.Valid;

@Controller
@RequestMapping("/comuboard")
@Log4j2
@RequiredArgsConstructor
public class ComuBoardController {

    private final ComuBoardService comuBoardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<ComuBoardDTO> responseDTO = comuBoardService.list1(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping("/register")
    public void registerGET(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid ComuBoardDTO comuBoardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("comuboard POST regster...");

        if(bindingResult.hasErrors()){
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/comuboard/list";

        }

        log.info(comuBoardDTO);

        Long bno = comuBoardService.register(comuBoardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect://comuboard/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){

        ComuBoardDTO comuBoardDTO = comuBoardService.readOne(bno);

        log.info(comuBoardDTO);

        model.addAttribute("dto",comuBoardDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid ComuBoardDTO comuBoardDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("comuboard modify post....." + comuBoardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors......");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("bno",comuBoardDTO.getBno());

            return "redirect:/comuboard/modify?"+link;
        }

        comuBoardService.modify(comuBoardDTO);

        redirectAttributes.addFlashAttribute("result","modified");

        redirectAttributes.addAttribute("bno", comuBoardDTO.getBno());

        return "redirectL:/comuboard/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        log.info("remove post.." + bno);

        comuBoardService.remove(bno);

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirct:/comuboard/list";
    }
}
