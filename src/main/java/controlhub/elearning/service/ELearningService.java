package controlhub.elearning.service;

import controlhub.elearning.adapter.dto.CourseDto;
import controlhub.elearning.adapter.dto.LessonDto;
import controlhub.elearning.adapter.dto.QuestionDto;
import controlhub.elearning.adapter.mapper.CourseMapper;
import controlhub.elearning.adapter.mapper.LessonMapper;
import controlhub.elearning.adapter.mapper.QuestionMapper;
import controlhub.elearning.adapter.repository.CourseRepository;
import controlhub.elearning.adapter.repository.LessonRepository;
import controlhub.elearning.adapter.repository.QuestionRepository;
import controlhub.elearning.domain.Course;
import controlhub.elearning.domain.Lesson;
import controlhub.elearning.domain.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ELearningService {

    private final CourseRepository courseRepository;

    private final LessonRepository lessonRepository;

    private final QuestionRepository questionRepository;

    // Course related methods
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream().map(CourseMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<CourseDto> getCourseById(Long courseId) {
        return courseRepository.findById(courseId).map(CourseMapper::toDTO);
    }

    public CourseDto createCourse(CourseDto courseDTO) {
        Course course = CourseMapper.toEntity(courseDTO);
        return CourseMapper.toDTO(courseRepository.save(course));
    }

    public CourseDto updateCourse(Long courseId, CourseDto updatedCourseDTO) {
        return courseRepository.findById(courseId).map(course -> {
            course.setName(updatedCourseDTO.getName());
            return CourseMapper.toDTO(courseRepository.save(course));
        }).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    // Lesson related methods
    public List<LessonDto> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findAllByCourseId(courseId).stream().map(LessonMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<LessonDto> getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId).map(LessonMapper::toDTO);
    }

    public LessonDto createLesson(LessonDto lessonDTO) {
        Lesson lesson = LessonMapper.toEntity(lessonDTO);
        lesson.setCourse(courseRepository.findById(lessonDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found")));
        return LessonMapper.toDTO(lessonRepository.save(lesson));
    }

    public LessonDto updateLesson(Long lessonId, LessonDto updatedLessonDTO) {
        return lessonRepository.findById(lessonId).map(lesson -> {
            lesson.setName(updatedLessonDTO.getName());
            lesson.setApprovalThreshold(updatedLessonDTO.getApprovalThreshold());
            return LessonMapper.toDTO(lessonRepository.save(lesson));
        }).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    // Question related methods
    public List<QuestionDto> getQuestionsByLessonId(Long lessonId) {
        return questionRepository.findAllByLessonId(lessonId).stream().map(QuestionMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<QuestionDto> getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).map(QuestionMapper::toDTO);
    }

    public QuestionDto createQuestion(QuestionDto questionDTO) {
        Question question = QuestionMapper.toEntity(questionDTO);
        question.setLesson(lessonRepository.findById(questionDTO.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found")));
        return QuestionMapper.toDTO(questionRepository.save(question));
    }

    public QuestionDto updateQuestion(Long questionId, QuestionDto updatedQuestionDTO) {
        return questionRepository.findById(questionId).map(question -> {
            question.setText(updatedQuestionDTO.getText());
            question.setScore(updatedQuestionDTO.getScore());
            question.setType(updatedQuestionDTO.getType());
            question.setOptions(updatedQuestionDTO.getOptions());
            question.setCorrectAnswer(updatedQuestionDTO.getCorrectAnswer());
            return QuestionMapper.toDTO(questionRepository.save(question));
        }).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    // Additional business logic methods
    public boolean isLessonApproved(Long lessonId, List<String> answers) {
        Optional<Lesson> lessonOpt = lessonRepository.findById(lessonId);
        if (lessonOpt.isPresent()) {
            Lesson lesson = lessonOpt.get();
            List<Question> questions = lesson.getQuestions();
            int totalScore = 0;
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                if (question.getCorrectAnswer().equals(answers.get(i))) {
                    totalScore += question.getScore();
                }
            }
            return totalScore >= lesson.getApprovalThreshold();
        }
        throw new RuntimeException("Lesson not found");
    }

    public boolean isCourseApproved(Long courseId, List<Long> approvedLessonIds) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            List<Lesson> lessons = course.getLessons();
            for (Lesson lesson : lessons) {
                if (!approvedLessonIds.contains(lesson.getId())) {
                    return false;
                }
            }
            return true;
        }
        throw new RuntimeException("Course not found");
    }
}

