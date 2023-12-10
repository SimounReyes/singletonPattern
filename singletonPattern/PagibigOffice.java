package singletonPattern;

import java.util.Scanner;

public class PagibigOffice {
    public static void main(String[] args) {

        QueuingSystem queueSys = QueuingSystem.getInstance();
        Scanner eat = new Scanner(System.in);

        while (true) {
            queueSys.currentStatus();

            System.out.println("-------------------------------------------------------------------\n\n" +
                    "Select the Function you need on the options below [1-4]:\n" +
                    "1. Get a Queue Number\n" +
                    "2. Reset one Queue Number\n" +
                    "3. Insert 5 Queue Numbers at the same time\n" +
                    "4. Terminate program");
            System.out.print("Function: ");

            String input;
            while (!(input = eat.nextLine().trim()).matches("[1-4]")) {
                System.out.println("Invalid input, try again");
                System.out.print("Function [1-4]: ");
            }
            int function = Integer.parseInt(input);

            System.out.println();
            switch (function) {
                case 1:
                    int num = queueSys.getQNumber();
                    System.out.println("Your Queue Number is " +String.format("%02d", num));
                    queueSys.assign();
                    break;
                case 2:
                    System.out.print("[Enter number to remove]: ");

                    while (!(input = eat.nextLine().trim()).matches("\\d+")) {
                        System.out.println("Wrong Input!");
                        System.out.print("[Enter number to remove]: ");
                    }
                    int queueNumber = Integer.parseInt(input);
                    queueSys.resetingQNumber(queueNumber);
                    queueSys.assign();
                    break;
                case 3:
                    for (int i = 0; i < 5; i++) {
                        queueSys.getQNumber();
                    }
                    queueSys.assign();
                    break;
                case 4:
                    System.out.println("Terminating Program!\nSimoun Irwin G. Reyes|3BSCS-2");
                    System.exit(0);
                default:
                    System.out.println("Invalid input, try again");
            }
            System.out.println();
        }
    }
}
