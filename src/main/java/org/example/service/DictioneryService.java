package org.example.service;

import org.example.dto.Dictionery;
import org.example.repository.DictioneryRepository;
import org.example.utils.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class DictioneryService {
    @Autowired
    private DictioneryRepository dictioneryRepository;
    @Autowired
    private ScannerUtil scanner;
    private Random random = new Random();

    public void addWord(Dictionery dictionery) {
        if (dictionery.getWordInUzb().trim().isEmpty()) {
            System.out.print("Enter uz word");
            return;
        } else if (dictionery.getWordInEng().trim().isEmpty()) {
            System.out.print("Enter en word");
            return;
        }

        dictioneryRepository.add(dictionery);

    }

    public void wordList() {
        List<Dictionery> dictionaries = dictioneryRepository.getList();
        for (Dictionery dictionary : dictionaries) {
            System.out.println(dictionary);
        }
    }

    public void searching(String word) {
        List<Dictionery> dictioneries = dictioneryRepository.getList();
        for (Dictionery dictionery : dictioneries) {
            if (dictionery.getWordInEng().toLowerCase().contains(word.toLowerCase())) {
                System.out.println(dictionery);
            } else if (dictionery.getWordInUzb().toLowerCase().contains(word.toLowerCase())) {
                System.out.println(dictionery);
            }
        }
    }

    public void spelling() {
        int correct = 0, incorrect = 0, questions = 0;
        List<Dictionery> dictionaries = dictioneryRepository.getList();
        int size = dictionaries.size();
        List<Integer> done = new LinkedList<>();
        do {
            if (dictionaries.size() == done.size()) {
                System.out.println("Word list done!!!!");
                return;
            }
            int i = random.nextInt(0, size);
            if (done.contains(i)) {
                continue;
            }
            done.add(i);
            questions++;
            Dictionery dictionery = dictionaries.get(i);
            do {
                System.out.println();
                System.out.println(dictionery.getWordInUzb());
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase(dictionery.getWordInEng())) {
                    System.out.println("Perfect👌👌👌");
                    correct++;
                    break;
                } else if (answer.equals("0")) {
                    System.out.println("Questions -> " + questions);
                    System.out.println("Correct answers -> " + correct);
                    System.out.println("Incorrect answers -> " + incorrect);
                    return;
                } else {
                    incorrect++;
                    System.out.println("🚧❌❌❌🚧\n");
                    System.out.println("Answers answers -> " + questions);
                    System.out.println("Correct answers -> " + correct);
                    System.out.println("Incorrect answers -> " + incorrect);
                }
            } while (true);
            System.out.println("Answers answers -> " + questions);
            System.out.println("Correct answers -> " + correct);
            System.out.println("Incorrect answers -> " + incorrect);
        } while (true);
    }

    public void deleteById(int id) {
        int res = dictioneryRepository.deleteById(id);
        if (res == 0) {
            System.out.println("Not found");
        } else {
            System.out.println("Deleted");
        }
    }

    public void multipleChoice() {
        int correct = 0, incorrect = 0, questions = 0;
        List<Dictionery> dictionaries = dictioneryRepository.getList();
        if (dictionaries.size() < 3) {
            System.out.println("Not enough words!");
            return;
        }
        List<Integer> done = new LinkedList<>();
        do {
            if (done.size() == dictionaries.size()) {
                System.out.println("Test done!");
                return;
            }
            int i = random.nextInt(0, dictionaries.size());
            if (done.contains(i)) {
                continue;
            }
            done.add(i);
            Dictionery dictionery = dictionaries.get(i);
            String correctAnswer = dictionery.getWordInEng();
            int j = 0;
            List<Integer> answers = new LinkedList<>();
            answers.add(i);
            System.out.println("***  "+dictionery.getWordInEng()+" ***");
            questions++;
            do {
                String alpha = getAlpha(j);
                if (j == 4) {
                    break;
                }
                if ((dictionery.getId() % 4) == j) {
                    System.out.println(alpha + dictionery.getWordInUzb());
                    correctAnswer = alpha + dictionery.getWordInUzb();
                    j++;
                    continue;
                }
                int res = random.nextInt(0, dictionaries.size());
                if (answers.contains(res)) {
                    continue;
                }else {
                    answers.add(res);
                }

                Dictionery dictionary1 = dictionaries.get(res);
                System.out.println(alpha + dictionary1.getWordInUzb());

                j++;

            } while (true);
            do {
                System.out.println("Enter answer: ");
                String answer = scanner.nextLine();
                if (answer.equals("0")) {
                    System.out.println("Questions -> " + questions);
                    System.out.println("Correct answers -> " + correct);
                    System.out.println("Incorrect answers -> " + incorrect);
                    return;
                }
                answer=answer+". "+dictionery.getWordInUzb();
                if (answer.equalsIgnoreCase(correctAnswer)) {
                    correct++;
                    System.out.println("Perfect");
                    System.out.println("Questions -> " + questions);
                    System.out.println("Correct answers -> " + correct);
                    System.out.println("Incorrect answers -> " + incorrect);
                    break;
                }  else {
                    incorrect++;
                    System.out.println("Try again!");
                    System.out.println("Questions -> "+questions);
                    System.out.println("Correct answers -> "+correct);
                    System.out.println("Incorrect answers -> "+incorrect);
                }
            } while (true);

        } while (true);
    }

    private String getAlpha(int j) {
        if (j == 0) return "a. ";
        else if (j == 1) return "b. ";
        else if (j == 2) return "c. ";
        return "d. ";
    }
}
