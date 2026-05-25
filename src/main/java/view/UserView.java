package view;

import controller.UserController;
import model.User;

public class UserView extends View {
    private UserController userControl;
    private MainMenuView mainMenu;

    public UserView(UserController userControl, MainMenuView mainMenu) {
        this.userControl = userControl;
        this.mainMenu = mainMenu;
    }

    @Override
    public void showMenu() throws Exception {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu User ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("Ketik apa saja untuk keluar");
            System.out.println("=================");
            System.out.print("Masukkan pilihan: ");

            String pilihan = View.input.nextLine(); 

            switch (pilihan) {
                case "1":
                    showLogin();
                    break;
                case "2":
                    showRegister();
                    break;
                default:
                    userControl.logout();
                    System.out.println("Terima kasih telah menggunakan aplikasi!");
                    running = false;
                    break;
            }
        }

        input.close();
    }

    public void showLogin() throws Exception {
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        boolean success = userControl.login(username, password);
        if (success) {
            System.out.println("Login berhasil!");
            
            mainMenu.showMenu();
        } else {
            System.out.println("Login gagal. Periksa username dan password.");
        }
    }

    public void showRegister() throws Exception {
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        try {
            userControl.handleSave(new User(username, password, "user"));
            System.out.println("Registrasi berhasil! Silakan login.");

            mainMenu.showMenu();
        } catch (Exception e) {
            System.out.println("Registrasi gagal: " + e.getMessage());
        }
    }
}
