package game.characters;

import game.service.Dice;

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

    private final Dice dice = new Dice();
    private int attack; //Атака от 0 до 30
    private int armor;  //Защита от 0 до 30
    private int health; // Здоровье от 0 до N

    private int minDamage;

    private int maxDamage;


    public Creature() {};
    public Creature(Scanner in) {
        getSpecifications(in);
    }

    private void getSpecifications(Scanner in) {
        getCorrectAttack(in);
        getCorrectArmor(in);
        getCorrectHealth(in);
        getCorrectDamage(in);
    } //ввод характеристик

    private void getCorrectAttack(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.print("Введите показатель атаки (0-30): ");
                attack = Integer.parseInt(in.nextLine());
                if(attack < 0 || attack > 30) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение атаки!");
                input = true;
            }
        }
    } //ввод корректной атаки

    private void getCorrectArmor(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.print("Введите показатель защиты (0-30): ");
                armor = Integer.parseInt(in.nextLine());
                if(armor < 0 || armor > 30) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение защиты!");
                input = true;
            }
        }
    } //ввод корректной защиты

    private void getCorrectHealth(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.print("Введите показатель здоровья (>0): ");
                health = Integer.parseInt(in.nextLine());
                if(health < 0) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение здоровья!");
                input = true;
            }
        }
    } //ввод корректной здоровья

    private void getCorrectDamage(Scanner in) {
        boolean input = true;
        while (input) {
            try {
                System.out.print("Введите показатель минимального урона: ");
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
                System.out.print("Введите показатель максимального урона: ");
                maxDamage = Integer.parseInt(in.nextLine());
                if(maxDamage < minDamage) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение максимального урона!");
                input = true;
            }
        }
    } //ввод корректного урона

    public void getInfo() {
        System.out.println("Атака: " + attack);
        System.out.println("Защита: " + armor);
        System.out.println("Текущее здоровье: " + health);
        System.out.println("Урон: " + minDamage + "-" + maxDamage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean fight(Creature another) { //this атакует another
        int diceCount = Math.max((attack - another.getAttack() + 1), 1);
        if(dice.attacking(diceCount)) {
            System.out.println("Атака успешна!");
            another.setHealth(another.getHealth() - dice.roll(minDamage,maxDamage));
            return true;
        }
        else {
            System.out.println("Атака не прошла!");
            return false;
        }
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
