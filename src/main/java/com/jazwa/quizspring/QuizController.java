package com.jazwa.quizspring;

import com.jazwa.quizspring.model.Question;
import com.jazwa.quizspring.model.QuestionRepository;
import com.jazwa.quizspring.model.Quiz;
import com.jazwa.quizspring.model.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class QuizController {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/wybierzQuiz")
    String wybierzQuiz(Model model){
        List<Quiz> quizzes = quizRepository.findAll();
        model.addAttribute("quizzes",quizzes);
        return "wyborQuiz";
    }
    @GetMapping("/question")
    String wyswietlPytania(Model model,
                           @RequestParam("id") String id){
        Optional<Quiz> quiz = quizRepository.findById(Long.parseLong(id));
        Set<Question> questions = new HashSet<>();
        quiz.ifPresent(q -> questions.addAll(q.getQuestionSet()));
        model.addAttribute("questions",questions);
        return "wyswietlPytania";
    }

    @PostMapping("/question")
    String wyswietlWynik(Model model, HttpServletRequest request,@RequestParam Map<String,String> allRequestParams){
        AtomicInteger count = new AtomicInteger(0);
        allRequestParams.forEach((key, value) -> {
            Question q =questionRepository.findById(Long.parseLong(key)).get();

            String answer = value;
            if (answer.equals(q.getCorrectAnswer())) {
                count.getAndAdd(1);
            }
        });
        model.addAttribute("result",count);
        return "resultat";
    }
}
