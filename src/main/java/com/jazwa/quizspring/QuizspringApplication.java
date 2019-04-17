package com.jazwa.quizspring;

import com.jazwa.quizspring.model.Question;
import com.jazwa.quizspring.model.Quiz;
import com.jazwa.quizspring.model.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class QuizspringApplication implements CommandLineRunner {

    @Autowired
    QuizRepository quizRepository;
    public static void main(String[] args) {
        SpringApplication.run(QuizspringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Question question = new Question();
        question.setContent("Kto ty jestes?");
        question.setAnswer1("Polak maly");
        question.setAnswer2("Niemiec maly");
        question.setAnswer3("Rusek maly");
        question.setCorrectAnswer("A");

        Question question2 = new Question();
        question2.setContent("Jaki znak twoj?");
        question2.setAnswer1("Orzel bialy");
        question2.setAnswer2("Czarna wrona");
        question2.setAnswer3("Jakis ptak");
        question2.setCorrectAnswer("B");

        Quiz quiz = new Quiz();
        quiz.setTitle("Wiedza o wierszykach");
        question.setQuiz(quiz);
        question2.setQuiz(quiz);


        Set<Question> questionSet = new HashSet<>();
        questionSet.add(question);
        questionSet.add(question2);
        quiz.setQuestionSet(questionSet);
        quizRepository.save(quiz);

    }
}
