import java.util.Random;
import java.util.Scanner;

public class GBLesson3 {

    static Scanner scanner;
    static Random random;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        random = new Random();

        lessOrMoreGame();

        guessTheWordGame();

    }


    /**
     * Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается
     * 3 попытки угадать это число. При каждой попытке компьютер должен сообщить,
     * больше ли указанное пользователем число, чем загаданное, или меньше.
     * После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет)
     * */
    public static void lessOrMoreGame(){

        do {
            System.out.println("Загадано число от 0 до 9. Ваша задача отгадать его за 3 попытки.");
            if (lessOrMoreGameIteration( random.nextInt(10) ) ) {
                System.out.println("Вы победили!");
            }
            else{
                System.out.println("Вы проиграли!");
            }

            System.out.println("\nПовторить игру еще раз? 1 - да, 0 - нет");

        } while (scanner.nextInt() != 0);
    }

    /**
     * Одна игровая итерация. Возвращает true если пользователь победил и false в другом случае
     * */
    public static boolean lessOrMoreGameIteration(int num){

        int userValue;

        for (int i = 0; i < 3; i++) {
            System.out.printf("У вас осталось попыток: %d\n", (3 - i) );
            System.out.print("Ваш вариант:");
            userValue = scanner.nextInt(10);
            if ( printLessMoreEqual(userValue, num) ) {
                return true;
            }

        }
        System.out.printf("Было загадано число %d\n", num);
        return false;
    }


    /**
     * Печать на экран сообщений больше, меньше или равно
     * возвращает true если числа совпали, false если нет
     * */
    public static boolean printLessMoreEqual(int a, int b){
        //(a < b ? -1 : a > b ? 1 : 0 );
        switch (Integer.signum(a - b )) {
            case -1:{
                System.out.println("Указанное число меньше загаданного.");
                return false;
            }
            case 1:{
                System.out.println("Указанное число больше загаданного.");
                return false;
            }
            default:{
                System.out.println("Указанное число совпадает с загаданным.");
                return true;
            }
        }

    }

    /**При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
     * сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
     * Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
     */

    public static void guessTheWordGame(){
        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic", "grape",
                "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        do{


            guessTheWordGameIteration( words[ random.nextInt( words.length ) ] );

            System.out.println("\nПовторить игру еще раз? 1 - да, 0 - нет");

        } while (scanner.nextInt() != 0);

    }

    /**
     * Одна игровая итерация. Получает на вход слово, которое должен угадать пользователь.
     * */
    public static void guessTheWordGameIteration(String guessedWord){

        boolean[] guessedPositions = new boolean[guessedWord.length()];
        char letter;
        String userInput;
        System.out.println("Вам загадано слово. Попробуйте угадать его, или введите 0 чтобы закончить");

        do {
            printGuessedWordWithMask(guessedWord, guessedPositions);
            System.out.println("Введите слово:");
            userInput = scanner.next();

            if (userInput.equals("0")) {
                System.out.println("Выход!");
                break;
            }

            if (userInput.equals(guessedWord)){
                System.out.println("Поздравляем! Вы угадали слово!");
                break;
            }

            if ( checkWordContainsLetters(guessedWord, userInput, guessedPositions) ){
                System.out.println("Вы угадали позицию одной или нескольких букв!" );
            }

        } while (!isAllLettersGuessed(guessedPositions));

        System.out.printf("Загаданное слово:%s\n\n", guessedWord );

    }

    /**
     * Проверяет, все ли буквы угаданы
     * */
    public static boolean isAllLettersGuessed(boolean[] guessed){
        for (int i = 0; i < guessed.length; i++) {
            if (!guessed[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Печатает слово с маской и угаданными буквами
     * */
    public static void printGuessedWordWithMask(String word, boolean[] guessed){
        for (int i = 0; i < 15; i++) {

            //в Java ленивые операции сравнения, поэтому можно сравнивать и выход за границы массива и его элементы
            //в одном выражении, первой операцией должна быть проверка на выход за границы массива.
            if( ( i < guessed.length ) && ( guessed[i] ) ){
                System.out.print(word.charAt(i));
            } else {
                System.out.print("#");
            }
        }
        System.out.println();
    }

    /**
     * Проверяет, есть ли совпадения по буквам в выбранном слове
     * Проставляет true в массив guessed[] в тех местах, где буква есть
     * возвращает true в случае, если новая буква или буквы угаданы
     * */
    public static boolean checkWordContainsLetters(String guessedWord, String userGuess, boolean[] guessed){

        boolean contains = false;

        for (int i = 0; i < ( userGuess.length() <= guessedWord.length() ? userGuess.length() :  guessedWord.length() ); i++) {

            if(guessedWord.charAt(i) == userGuess.charAt(i) && !guessed[i]){
                contains = true;
                guessed[i] = true;
            }
        }

        return contains;
    }

}
