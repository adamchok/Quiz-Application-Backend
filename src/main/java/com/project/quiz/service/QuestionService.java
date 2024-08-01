package com.project.quiz.service;

import com.project.quiz.model.Question;
import com.project.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        return questionRepository.findAll(sort);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> getQuestionsByDifficulty(String difficultyLevel) {
        // Convert the input difficultyLevel to lower case for case-insensitive comparison
        String lowerCaseDifficultyLevel = difficultyLevel.toLowerCase();

        // Retrieve all questions and filter based on the difficultyLevel
        List<Question> allQuestions = getAllQuestions();
        return allQuestions.stream()
                .filter(ques -> ques.getDifficulty_level().equalsIgnoreCase(difficultyLevel))
                .toList();
    }

    public boolean deleteQuestionById(Long id) {
        try {
            if (questionRepository.existsById(id)) {
                questionRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Question> updateQuestionById(Long id, Question question) {
            try {
                Optional<Question> opt = getQuestionById(id);
                if (opt.isPresent()) {
                    Question updatedQuestion = opt.get();
                    updatedQuestion.setQuestion(question.getQuestion());
                    updatedQuestion.setAnswer(question.getAnswer());
                    updatedQuestion.setDifficulty_level(question.getDifficulty_level());
                    updatedQuestion.setOption_1(question.getOption_1());
                    updatedQuestion.setOption_2(question.getOption_2());
                    updatedQuestion.setOption_3(question.getOption_3());
                    updatedQuestion.setOption_4(question.getOption_4());
                    return Optional.of(questionRepository.save(updatedQuestion));
                } else {
                    return Optional.empty();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
    }

}
