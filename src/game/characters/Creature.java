package game.characters;

import java.util.Scanner;

/**
 * @author hatka_bobrikov
 * @created 08.10.2023 16:02
 */

/*
Условия:
1) В игре есть Существа. К ним относятся Игрок и Монстры.
2) У Существа есть параметры Атака и Защита. Это целые числа от 1 до 30.
3) У Существа есть Здоровье. Это натуральное число от 0 до N. Если Здоровье становится равным 0, то Существо умирает. Игрок может себя исцелить до 4-х раз на 30% от максимального Здоровья.
4) У Существа есть параметр Урон. Это диапазон натуральных чисел M - N. Например, 1-6.
5) Одно Существо может ударить другое по такому алгоритму:
  - Рассчитываем Модификатор атаки. Он равен разности Атаки атакующего и Защиты защищающегося плюс 1
  - Успех определяется броском N кубиков с цифрами от 1 до 6, где N - это Модификатор атаки. Всегда бросается хотя бы один кубик.
    - Удар считается успешным, если хотя бы на одном из кубиков выпадает 5 или 6
  - Если удар успешен, то берется произвольное значение из параметра Урон атакующего и вычитается из Здоровья защищающегося.
 */
public abstract class Creature {
    int attack; //Атака от 0 до 30
    int armor;  //Защита от 0 до 30
    int health; // Здоровье от 0 до N
    int maxHealth; // Максимальное здоровье - N

    int minDamage;

    int maxDamage;



    public Creature(Scanner in) {
        getSpecifications(in);
    }

    private void getSpecifications(Scanner in) {
        getCorrectAttack(in);
        getCorrectArmor(in);
        getCorrectHealth(in);
        getCorrectDamage(in);
    }

    private void getCorrectAttack(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.println("Введите показатель атаки: ");
                attack = Integer.parseInt(in.nextLine());
                if(attack < 0 || attack > 30) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение атаки!");
                input = true;
            }
        }
    }

    private void getCorrectArmor(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.println("Введите показатель защиты: ");
                armor = Integer.parseInt(in.nextLine());
                if(armor < 0 || armor > 30) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение защиты!");
                input = true;
            }
        }
    }

    private void getCorrectHealth(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.println("Введите показатель здоровья: ");
                health = Integer.parseInt(in.nextLine());
                if(health < 0) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение здоровья!");
                input = true;
            }
        }
    }

    private void getCorrectDamage(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.println("Введите показатель минимального урона: ");
                minDamage = Integer.parseInt(in.nextLine());
                if(minDamage < 0) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение минимального урона!");
                input = true;
            }
        }
        input = true;
        while (input) {
            try {
                System.out.println("Введите показатель максимального урона: ");
                maxDamage = Integer.parseInt(in.nextLine());
                if(maxDamage < minDamage) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение максимального урона!");
                input = true;
            }
        }
    }
}
