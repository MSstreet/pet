package project.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.font.LayoutPath;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bnum;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
