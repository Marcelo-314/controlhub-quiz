package controlhub.elearning.adapter.controller;

import controlhub.elearning.adapter.dto.CourseDto;
import controlhub.elearning.adapter.dto.LessonDto;
import controlhub.elearning.adapter.dto.QuestionDto;
import controlhub.elearning.service.ELearningService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api")

public class ELearningController {

    private final ELearningService eLearningService;

    // Course Endpoints
    @GetMapping("/courses")
    public List<CourseDto> getCourses() {
        return eLearningService.getAllCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Optional<CourseDto> getCourse(@PathVariable Long courseId) {
        return eLearningService.getCourseById(courseId);
    }

    @PostMapping("/courses")
    public CourseDto createCourse(@RequestBody CourseDto courseDTO) {
        return eLearningService.createCourse(courseDTO);
    }

    @PutMapping("/courses/{courseId}")
    public CourseDto updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDTO) {
        return eLearningService.updateCourse(courseId, courseDTO);
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        eLearningService.deleteCourse(courseId);
    }

    // Lesson Endpoints
    @GetMapping("/courses/{courseId}/lessons")
    public List<LessonDto> getLessons(@PathVariable Long courseId) {
        return eLearningService.getLessonsByCourseId(courseId);
    }

    @GetMapping("/lessons/{lessonId}")
    public Optional<LessonDto> getLesson(@PathVariable Long lessonId) {
        return eLearningService.getLessonById(lessonId);
    }

    @PostMapping("/lessons")
    public LessonDto createLesson(@RequestBody LessonDto lessonDTO) {
        return eLearningService.createLesson(lessonDTO);
    }

    @PutMapping("/lessons/{lessonId}")
    public LessonDto updateLesson(@PathVariable Long lessonId, @RequestBody LessonDto lessonDTO) {
        return eLearningService.updateLesson(lessonId, lessonDTO);
    }

    @DeleteMapping("/lessons/{lessonId}")
    public void deleteLesson(@PathVariable Long lessonId) {
        eLearningService.deleteLesson(lessonId);
    }

    // Question Endpoints
    @GetMapping("/lessons/{lessonId}/questions")
    public List<QuestionDto> getQuestions(@PathVariable Long lessonId) {
        return eLearningService.getQuestionsByLessonId(lessonId);
    }

    @GetMapping("/questions/{questionId}")
    public Optional<QuestionDto> getQuestion(@PathVariable Long questionId) {
        return eLearningService.getQuestionById(questionId);
    }

    @PostMapping("/questions")
    public QuestionDto createQuestion(@RequestBody QuestionDto questionDTO) {
        return eLearningService.createQuestion(questionDTO);
    }

    @PutMapping("/questions/{questionId}")
    public QuestionDto updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDto questionDTO) {
        return eLearningService.updateQuestion(questionId, questionDTO);
    }

    @DeleteMapping("/questions/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId) {
        eLearningService.deleteQuestion(questionId);
    }

    // Additional Endpoints for Business Logic
    @PostMapping("/lessons/{lessonId}/approve")
    public boolean isLessonApproved(@PathVariable Long lessonId, @RequestBody List<String> answers) {
        return eLearningService.isLessonApproved(lessonId, answers);
    }

    @PostMapping("/courses/{courseId}/approve")
    public boolean isCourseApproved(@PathVariable Long courseId, @RequestBody List<Long> approvedLessonIds) {
        return eLearningService.isCourseApproved(courseId, approvedLessonIds);
    }
}

