import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TestRunner.runTests();

        PerformanceTest.start();

        Scanner scanner = new Scanner(System.in);

        ChainHashMap<String, String> chainMap = null;
        LinearHashMap<String, String> linearMap = null;
        AVLTree avlTree = new AVLTree();
        avlTree.buildTreeFromFile("res/TreeInit.txt");

        while (true) {
            System.out.println("\nВыберите структуру:");
            System.out.println("1 - ChainHashMap");
            System.out.println("2 - LinearHashMap");
            System.out.println("3 - AVLTree");
            System.out.println("0 - Выход");
            System.out.print("> ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            }

            if (choice == 1 && chainMap == null) {
                System.out.print("Введите емкость для ChainHashMap: ");
                int capacity = Integer.parseInt(scanner.nextLine());
                if (capacity <= 0) {
                    System.out.println("→ Некорректное значение емкости. Будет установлено значение по умолчанию: 16");
                }
                chainMap = new ChainHashMap<>(capacity);
            } else if (choice == 2 && linearMap == null) {
                System.out.print("Введите емкость для LinearHashMap: ");
                int capacity = Integer.parseInt(scanner.nextLine());
                if (capacity <= 0) {
                    System.out.println("→ Некорректное значение емкости. Будет установлено значение по умолчанию: 16");
                }
                linearMap = new LinearHashMap<>(capacity);
            }

            boolean inMenu = true;
            while (inMenu) {
                if (choice == 3) {
                    System.out.println("\nОперации с AVLTree:");
                    System.out.println("1 - insert (вставить)");
                    System.out.println("2 - delete (удалить)");
                    System.out.println("3 - print (печать дерева)");
                    System.out.println("4 - getFirst (наименьший ключ)");
                    System.out.println("5 - getLast (наибольший ключ)");
                    System.out.println("6 - getEqual (равные)");
                    System.out.println("7 - getLongest (больше чем)");
                    System.out.println("8 - getShortest (меньше чем)");
                    System.out.println("9 - getInRange (в диапазоне)");
                    System.out.println("0 - Назад");
                    System.out.print("> ");
                    int op = Integer.parseInt(scanner.nextLine());

                    switch (op) {
                        case 0:
                            inMenu = false;
                            break;
                        case 1:
                            System.out.print("Введите ключ для вставки: ");
                            String insertKey = scanner.nextLine();
                            avlTree.insert(insertKey);
                            System.out.println("→ Вставлено.");
                            break;
                        case 2:
                            System.out.print("Введите ключ для удаления: ");
                            String deleteKey = scanner.nextLine();
                            avlTree.delete(deleteKey);
                            System.out.println("→ Удалено.");
                            break;
                        case 3:
                            System.out.println("→ Дерево (в порядке возрастания):");
                            avlTree.print();
                            System.out.println();
                            break;
                        case 4:
                            try {
                                System.out.println("→ Наименьший ключ: " + avlTree.getFirst());
                            } catch (Exception e) {
                                System.out.println("→ Ошибка: " + e.getMessage());
                            }
                            break;
                        case 5:
                            try {
                                System.out.println("→ Наибольший ключ: " + avlTree.getLast());
                            } catch (Exception e) {
                                System.out.println("→ Ошибка: " + e.getMessage());
                            }
                            break;
                        case 6:
                            System.out.print("Введите ключ для поиска равных: ");
                            String equalKey = scanner.nextLine();
                            List<String> equals = avlTree.getEqual(equalKey);
                            System.out.println("→ Найдено: " + equals);
                            break;
                        case 7:
                            System.out.print("Введите ключ для поиска больших: ");
                            String longerKey = scanner.nextLine();
                            List<String> longer = avlTree.getLongest(longerKey);
                            System.out.println("→ Больше чем " + longerKey + ": " + longer);
                            break;
                        case 8:
                            System.out.print("Введите ключ для поиска меньших: ");
                            String shorterKey = scanner.nextLine();
                            List<String> shorter = avlTree.getShortest(shorterKey);
                            System.out.println("→ Меньше чем " + shorterKey + ": " + shorter);
                            break;
                        case 9:
                            System.out.print("Введите нижнюю границу: ");
                            String min = scanner.nextLine();
                            System.out.print("Введите верхнюю границу: ");
                            String max = scanner.nextLine();
                            List<String> inRange = avlTree.getInRange(min, max);
                            System.out.println("→ В диапазоне [" + min + ", " + max + "]: " + inRange);
                            break;
                        default:
                            System.out.println("Неверный ввод!");
                    }
                } else {
                    System.out.println("\nОперации:");
                    System.out.println("1 - put (добавить/обновить)");
                    System.out.println("2 - get (получить)");
                    System.out.println("3 - remove (удалить)");
                    System.out.println("4 - size (размер)");
                    System.out.println("5 - isEmpty (проверка на пустоту)");
                    System.out.println("6 - clear (очистить)");
                    System.out.println("0 - Назад");
                    System.out.print("> ");
                    int op = Integer.parseInt(scanner.nextLine());

                    String key, value;

                    switch (op) {
                        case 0:
                            inMenu = false;
                            break;
                        case 1:
                            System.out.print("Ключ: ");
                            key = scanner.nextLine();
                            System.out.print("Значение: ");
                            value = scanner.nextLine();
                            if (choice == 1)
                                chainMap.put(key, value);
                            else
                                linearMap.put(key, value);
                            System.out.println("→ Добавлено/обновлено.");
                            break;
                        case 2:
                            System.out.print("Ключ: ");
                            key = scanner.nextLine();
                            if (choice == 1)
                                value = chainMap.get(key);
                            else
                                value = linearMap.get(key);
                            System.out.println("→ Значение: " + value);
                            break;
                        case 3:
                            System.out.print("Ключ: ");
                            key = scanner.nextLine();
                            if (choice == 1)
                                chainMap.remove(key);
                            else
                                linearMap.remove(key);
                            System.out.println("→ Удалено.");
                            break;
                        case 4:
                            int size = (choice == 1) ? chainMap.count() : linearMap.count();
                            System.out.println("→ Размер: " + size);
                            break;
                        case 5:
                            boolean empty = (choice == 1) ? chainMap.isEmpty() : linearMap.count() == 0;
                            System.out.println("→ Пусто? " + empty);
                            break;
                        case 6:
                            if (choice == 1) {
                                chainMap.clear();
                            } else {
                                System.out.print("→ Очистка: пересоздать LinearHashMap? (y/n): ");
                                if (scanner.nextLine().equalsIgnoreCase("y")) {
                                    System.out.print("Введите емкость заново: ");
                                    int newCap = Integer.parseInt(scanner.nextLine());
                                    linearMap = new LinearHashMap<>(newCap);
                                }
                            }
                            System.out.println("→ Очищено.");
                            break;
                        default:
                            System.out.println("Неверный ввод!");
                    }
                }
            }
        }

        System.out.println("Завершение программы.");
        scanner.close();
    }
}
