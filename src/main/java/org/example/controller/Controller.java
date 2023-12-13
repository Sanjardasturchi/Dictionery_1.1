package org.example.controller;

import org.example.dto.Dictionery;
import org.example.service.DictioneryService;
import org.example.utils.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {
    @Autowired
    private ScannerUtil scanner;
    @Autowired
    private DictioneryService dictioneryService;

    public void start(){
        do {
            System.out.println("""
                    ** Menu **
                    1. Add Word
                    2. WordList List
                    3. Multiple Choice
                    4. Spelling
                    5. Searching
                    6. Delete by id
                    0.Stop""");
            int action = getAction();
            switch (action) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    addWord();
                }
                case 2 -> {
                    wordList();
                }
                case 3 -> {
                    multipleChoice();
                }
                case 4 -> {
                    spelling();
                }
                case 5 -> {
                    searching();
                }
                case 6 -> {
                    deleteById();
                }

            }
        }while (true);

    }

    private void deleteById() {
        System.out.print("Enter Id: ");
        int id = scanner.nextInt();
        dictioneryService.deleteById(id);
    }

    private void searching() {
        System.out.print("Enter word: ");
        String word = scanner.nextLine();
        dictioneryService.searching(word);
    }

    private void spelling() {
        dictioneryService.spelling();
    }

    private void multipleChoice() {
        dictioneryService.multipleChoice();
    }

    private void wordList() {
        dictioneryService.wordList();
    }

    private void addWord() {
        System.out.print("Enter word(UZB): ");
        String wordUz = scanner.nextLine();
        System.out.print("Enter word(Eng): ");
        String wordEng = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Dictionery dictionery=new Dictionery();
        dictionery.setWordInUzb(wordUz);
        dictionery.setWordInEng(wordEng);
        dictionery.setDescription(description);

        dictioneryService.addWord(dictionery);

    }

    private int getAction() {
        System.out.print("Choose action: ");
        int action = scanner.nextInt();
        return action;
    }

}
