package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new SocialApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, unable to run application.");
        }
    }
}
