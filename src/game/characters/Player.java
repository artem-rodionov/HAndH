package game.characters;

import java.util.Scanner;

/**
 * @author hatka_bobrikov
 * @created 08.10.2023 18:40
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
public class Player extends Creature {
    private int maxHealth; // Максимальное здоровье - N
    private int healCount = 4; // Счетчик кол-ва исцелений, до 4
    private double healSize = 0.3; // Размер 1-го исцелений от максимального здоровья
    private int healthHeal;

    public Player(Scanner in) {
        super(in);
        maxHealth = getHealth();
        healthHeal = (int)(0.3*maxHealth);
    }

    public Player() {
        super();
        maxHealth = getHealth();
        healthHeal = (int)(0.3*maxHealth);
    }

    @Override
    public void getInfo() {
        System.out.println("\n=== Информация об игроке ===");
        super.getInfo();
        System.out.println("Начальное(максимальное) здоровье: " + maxHealth);
        System.out.println("Оставшееся количество исцелений: " + healCount);
    }

    public void healing() {
        if(healCount > 0) {
            setHealth((getHealth() + healthHeal) > maxHealth ? maxHealth : (getHealth() + healthHeal));
            healCount--;
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealCount() {
        return healCount;
    }

    public double getHealSize() {
        return healSize;
    }
}
