package view;

import java.util.Scanner;

public abstract class View {
    public static Scanner input = new Scanner(System.in);
    
    public abstract void showMenu() throws Exception;
}
