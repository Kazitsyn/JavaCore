package ru.geekbrains.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {


    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static boolean complexity; // сложность
    private static int aiLogicX;
    private static int aiLogicY;

    private static final int WIN_COUNT = 4; // Выигрышная комбинация


    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if (checkState(DOT_HUMAN, "You win!"))
                    break;
                aiTurn();
                printField();
                if (checkState(DOT_AI, "Ai win!"))
                    break;
            }
            System.out.print("Play again? (Y - yas): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }



    /**
     * Инициализация объектов игры
     */
    static void initialize(){
        fieldSizeX = 5;
        fieldSizeY = 5;

        complexity = true;

        field = new char[fieldSizeX][fieldSizeY];

        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++){
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");


        for (int x = 0; x < fieldSizeX; x++){
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++){
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn(){
        int x;
        int y;
        do{
            System.out.print("Enter the coordinates X and Y\n(from 1 to "+ fieldSizeX + ") separated by a space: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn(){
        aiLogicX = 0;
        aiLogicY = 0;
        if (complexity){
            for (int i = 0; i < fieldSizeY; i++){
                for (int j = 0; j < fieldSizeX; j++){
                    if (check1(j,i,DOT_HUMAN,2) && aiLogicCheckCellEmpty(i, true) > 0) {
                        aiLogicH(j,i,true);
                    }
                    if (check2(j,i,DOT_HUMAN,2) && aiLogicCheckCellEmpty(i, false) > 0){
                        aiLogicH(j,i,false);
                    }
                    if (check3(j,i, DOT_HUMAN, 2)){
                        aiLogicX = j;
                        aiLogicY = i;
                        while ( aiLogicX < fieldSizeX && aiLogicY < fieldSizeY && !isCellEmpty(aiLogicX, aiLogicY)) {
                            if (aiLogicX == fieldSizeX - 1 || aiLogicY == fieldSizeY - 1) break;
                            aiLogicX++;
                            aiLogicY++;
                        }
                    }
                    if (check4(j,i,DOT_HUMAN,2)){
                        aiLogicX = j;
                        aiLogicY = i;
                        while ( aiLogicX < fieldSizeX && aiLogicY < fieldSizeY && !isCellEmpty(aiLogicX, aiLogicY)) {
                            if (aiLogicX == 0 || aiLogicY == fieldSizeY - 1) break;
                            aiLogicX--;
                            aiLogicY++;
                        }
                    }
                }
            }
            if (isCellEmpty(aiLogicX, aiLogicY)){
                field[aiLogicX][aiLogicY] = DOT_AI;
            }else {
                aiTurnRandom();
            }
        }else {
            aiTurnRandom();
        }
    }


    /**
     *
     * Метод рандомного хода для компьютера
     */
    static void aiTurnRandom(){
        int x;
        int y;
        do{
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Логика хода компьютера по вертикалям b горизонталям
     * @param x координата цикла горизонтали
     * @param y координата цикла вертикали
     * @param direction направление
     *                  true - вертикаль
     *                  false - горизонталь
     */
    static void aiLogicH(int x, int y, boolean direction){
        if (direction){
            aiLogicY = y;
            if (x >= 1 && isCellEmpty(x -1, aiLogicY)) {
                aiLogicX = x - 1;
            }else {
                while ( aiLogicX < fieldSizeX && !isCellEmpty(aiLogicX, aiLogicY)){
                    if (aiLogicX == fieldSizeX - 1) break;
                    aiLogicX++;
                }
            }
        }else {
            aiLogicX = x;
            if (y >= 1 && isCellEmpty(aiLogicX, y - 1)) {
                aiLogicX = y - 1;
            }else {
                while ( aiLogicX < fieldSizeY && !isCellEmpty(aiLogicX, aiLogicY)){
                    if (aiLogicY == fieldSizeY - 1) break;
                    aiLogicY++;
                }
            }
        }

    }

    /**
     * Поиск пустых ячеек
     * @param indexI индекс строки
     * @param direction направление
     *                  true - вертикаль
     *                  false - горизонталь
     * @return количество найденых пустых клеток
     */
    static int aiLogicCheckCellEmpty(int indexI, boolean direction){
        int cnt = 0;
        for (int k = 0; k < fieldSizeX; k++){
            if (direction){
                if (isCellEmpty(k, indexI)) cnt++;
            }else {
                if (isCellEmpty(indexI, k)) cnt++;
            }
        }
        return cnt;
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка валидности координат хода
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Поверка на ничью (все ячейки игрового поля заполнены фишками человека или компьютера)
     * @return
     */
    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * TODO: Переработать в рамках домашней работы
     * Метод проверки победы
     * @param dot фишка игрока
     * @return результат проверки победы
     */
    static boolean checkWin(char dot){
        for (int i = 0; i < fieldSizeX; i++){
            for (int j = 0; j < fieldSizeY; j++){
                if (    check1(i,j,dot,WIN_COUNT) ||
                        check2(i,j,dot,WIN_COUNT) ||
                        check3(i,j,dot,WIN_COUNT) ||
                        check4(i,j,dot,WIN_COUNT)) return true;
            }
        }
        return false;
    }

    /**
     * Проверка по оси Х
     * @param x координата по оси х
     * @param y координата по оси у
     * @param dot фишка игрока
     * @param win количесво фишек подряд для победы
     * @return
     */
    static boolean check1(int x, int y, char dot, int win){
        if (x <= fieldSizeX - win){
            for (int i = x ; i < x + win; i++){
                if (field[i][y] != dot) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Проверка по оси У
     * @param x координата по оси х
     * @param y координата по оси у
     * @param dot фишка игрока
     * @param win количесво фишек подряд для победы
     * @return
     */
    static boolean check2(int x, int y, char dot, int win){
        if (y <= fieldSizeY - win){
            for (int i = y ; i < y + win; i++){
                if (field[x][i] != dot) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Проверка по диагонали "\"
     * @param x координата по оси х
     * @param y координата по оси у
     * @param dot фишка игрока
     * @param win количесво фишек подряд для победы
     * @return
     */
    static boolean check3(int x, int y, char dot, int win){
        if (x <= fieldSizeX - win && y <= fieldSizeY - win){
            for (int i = x ; i < x + win; i++){
                if (field[i][y] != dot) return false;
                y++;
            }
            return true;
        }
        return false;
    }

    /**
     * Проверка по диагонали "/"
     * @param x координата по оси х
     * @param y координата по оси у
     * @param dot фишка игрока
     * @param win количесво фишек подряд для победы
     * @return
     */
    static boolean check4(int x, int y, char dot, int win){
        if (x >= win - 1 && y <= fieldSizeY - win){
            for (int i = x; i > x - win; i--){
                if (field[i][y] != dot) return false;
                y++;
            }
            return true;
        }
        return false;
    }

    /**
     * Проверка состояния игры
     * @param dot фишка игрока
     * @param s победный слоган
     * @return состояние игры
     */
    static boolean checkState(char dot, String s){
        if (checkWin(dot)){
            System.out.println(s);
            return true;
        }
        else if (checkDraw()){
            System.out.println("Drawn game!");
            return true;
        }
        // Игра продолжается
        return false;
    }

}
