package controlhub.elearning.adapter.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {

    private Long id;
    private String text;
    private int score;
    private String type;
    private Long lessonId;
    private List<String> options;
    private String correctAnswer;

}
