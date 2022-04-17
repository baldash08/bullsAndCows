package bullsAndCows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static String generateSecretNumber(int size, int alphabetsRange) {
        if (size > alphabetsRange || size == 0) {
            System.out.println("error");
            System.exit(0);
        }

        StringBuilder secret = new StringBuilder();
        Random rand = new Random();
        String[] str = "0123456789abcdefghijklmnopqrstuvwxyz".split("");


        if (alphabetsRange > 36) {

            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);

        } else {

            while (secret.length() != size) {
                String randStr = str[rand.nextInt(alphabetsRange-1)];
                if (secret.toString().contains(randStr)) {
                    continue;
                }
                secret.append(randStr);
            }

            System.out.print("The secret code prepared: ");
            for (int i = 0; i < size; i++) {
                System.out.print("*");
            }

            if (alphabetsRange > 11) {
                System.out.println(" (0-9, a-" + str[alphabetsRange-1] + ").");
            } else if (alphabetsRange == 10) {
                System.out.println(" (0-9).");
            } else if (alphabetsRange < 10) {
                System.out.println( "(0-" + str[alphabetsRange-1] + ").");
            }

        }


        return secret.toString();
    }






    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int theNumber = 0;
        int alphabets = 0;

        System.out.println("Please, enter the secret code's length:");
        String number = scanner.nextLine();
        try {
            theNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("error");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        String alphabetsRange = scanner.nextLine();

        try {
            alphabets = Integer.parseInt(alphabetsRange);
        } catch (NumberFormatException e) {
            System.out.println("error");
            System.exit(0);
        }


        String secretCode = generateSecretNumber(theNumber,alphabets);
        System.out.println("Okay, let's start a game!");

        int countBulls = 0;
        int countCows = 0;

        int turn = 1;
        while (countBulls != theNumber) {
            System.out.println("Turn " + turn + ":");
            turn++;
            String code = scanner.nextLine();


            if (code.length() == theNumber) {
                for (int i = 0; i < code.length(); i++) {

                    if (secretCode.contains(String.valueOf(code.charAt(i)))) {
                        if (String.valueOf(secretCode.charAt(i)).equals(String.valueOf(code.charAt(i)))) {
                            countBulls++;
                        } else {
                            countCows++;
                        }
                    }
                }


                if (countBulls == Integer.parseInt(number)) {
                    System.out.println("Grade: " + countBulls + " bulls\n" +
                            "Congratulations! You guessed the secret code.");
                    break;
                }

                if (countBulls > 1 && countCows > 1) {
                    System.out.println("Grade: " + countBulls + " bulls and "
                            + countCows + " cows");
                } else if (countBulls == 1 && countCows > 1) {
                    System.out.println("Grade: " + countBulls + " bull and "
                            + countCows + " cows");
                } else if (countBulls > 1 && countCows == 1) {
                    System.out.println("Grade: " + countBulls + " bulls and "
                            + countCows + " cow");
                } else if (countBulls == 1 && countCows == 1) {
                    System.out.println("Grade: " + countBulls + " bull and "
                            + countCows + " cow");
                } else if (countBulls > 1 && countCows == 0) {
                    System.out.println("Grade: " + countBulls + " bulls");
                } else if (countBulls == 1 && countCows == 0) {
                    System.out.println("Grade: " + countBulls + " bull");
                } else if (countBulls == 0 && countCows > 1) {
                    System.out.println("Grade: " + countCows + " cows");
                } else if (countBulls == 0 && countCows == 1) {
                    System.out.println("Grade: " + countCows + " cow");
                }
                else {
                    System.out.println("Grade: None");
                }

                countCows = 0;
                countBulls = 0;
            } else {
                System.out.println("Error: it's not possible to generate" +
                        " a code with a length of " + number + " with " + code.length() +  " unique symbols.");
            }


        }
    }
}