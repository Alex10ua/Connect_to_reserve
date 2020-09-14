package app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchInLogs {
    List<String> list = Files.readAllLines(new File("Test_task_app.log").toPath(), Charset.defaultCharset() );

    public SearchInLogs() throws IOException {
    }
    public void searchInLog(){
        List<String> listToShow = new ArrayList<>();// list for showing in console
        System.out.println("Enter word for searching: ");
        Scanner scanner = new Scanner(System.in);
        String searchingWord = scanner.nextLine();
        for (String element:list) {
               boolean check =  element.contains(searchingWord);// get status
                if (check){
                    listToShow.add(element);
                }
        }
        listToShow.forEach(element->{       //sout a new list with results
            System.out.println(element);
        });

    }
}
