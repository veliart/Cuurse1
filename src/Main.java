public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        employees[0] = new Employee("Иванов И.И.", 1, 70000);
        employees[1] = new Employee("Смирнов С.С.", 1, 80000);
        employees[2] = new Employee("Сидорова С.С.", 2, 90000);
        employees[3] = new Employee("Петров П.П.", 2, 100000);
        employees[4] = new Employee("Троцкий Т.Т.", 2, 85000);
        employees[5] = new Employee("Киркоров К.К.", 3, 120000);
        employees[6] = new Employee("Распутин Р.Р.", 3, 130000);
        employees[7] = new Employee("Маск М.М.", 4, 55000);
        employees[8] = new Employee("Ленин Л.Л.", 4, 66000);
        employees[9] = new Employee("Генеральный Г.Г.", 5, 500000);
        getListEmployees(employees);
        System.out.println("Сумма затрат на зарплаты в месяц составляет " + calculateSummarySalary(employees) + " рублей");
        System.out.println("Минимальная зарплата у следующего сотрудника: " + calculateEmployeeMinSalary(employees).getFullName());
        System.out.println("Максимальная зарплата у следующего сотрудника: " + calculateEmployeeMaxSalary(employees).getFullName());
        System.out.println("Средняя зарплата по сотрудникам составляет: " + getAverageSalary(employees) + " рублей");
        printEmployeesList(employees);
        increaseSalary(employees, 20); // увеличиваем зарплату у всех на 20 процентов
        System.out.println("Минимальная зарплата в отделе у сотрудника " + findLowPaidEmployee(employees, 2).getFullName());
        System.out.println("Максимальная зарплата в отделе у сотрудника " + findHighPaidEmployee(employees, 2).getFullName());
        System.out.println("Сумма затрат по отделу (после прибавки)   " + findDepartmentSalary(employees, 2));
        System.out.println("Средняя зарплата по отделу (после прибавки) "+ findDepartmentAverageSalary(employees, 2));
        printDepartmentEmployee(employees, 2); // список всех сотрудников отдела
        System.out.println("Сотрудники с меньшей зарплатой");
        printEmployeeWithMinSalary(employees, 100000); // не забьываем, что раньше повышали ЗП.
        System.out.println("Сотрудники с большей зарплатой");
        printEmployeeWithMaxSalary(employees, 120000);
    }

    // Получить список всех сотрудников со всеми имеющимися по ним данными.
    public static void getListEmployees(Employee[] employees) {
        System.out.println("Список всех сотрудников");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // Посчитать сумму затрат на зарплаты в месяц.
    private static int calculateSummarySalary(Employee[] employees) {
        int total = 0;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }

    // Найти сотрудника с минимальной зарплатой.
    private static Employee calculateEmployeeMinSalary(Employee[] employees) {
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() < employees[index].getSalary()) {
                index = i;
            }
        }
        return employees[index];
    }

    // Найти сотрудника с максимальной зарплатой.
    private static Employee calculateEmployeeMaxSalary(Employee[] employees) {
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() > employees[index].getSalary()) {
                index = i;
            }
        }
        return employees[index];
    }

    // посчитать среднее значение зарплат
    private static double getAverageSalary(Employee[] employees) {
        return (double) calculateSummarySalary(employees) / employees.length;
    }

    // Получить Ф.И.О. всех сотрудников
    private static void printEmployeesList(Employee[] employees) {
        for (Employee employee : employees) {
            System.out.println(employee.getFullName());
        }
    }

    // Индексация зарплаты у всех сотрудников
    private static void increaseSalary(Employee[] employees, int amountSalary) {
        for (Employee employee : employees) {
            System.out.print("Зарплата до повышения " + employee.getSalary() + ", ");
            employee.setSalary(employee.getSalary() + employee.getSalary() * amountSalary / 100); // можно все сделать в double, но зачем с точки зрения логики и курса рубля.
            System.out.println("зарплата после повышения " + employee.getSalary());
        }
    }

    // Получить в качестве параметра номер отдела и найти сотрудника с минимальной зарплатой.
    private static Employee findLowPaidEmployee(Employee[] employees, int department) {
        int counter = 0; // Определяем количество сотрудников в выбранном отделе.
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == department) {
                counter++;
            }
        }
        Employee[] departmentsEmployee = new Employee[counter]; // Создаем массив c выборкой сотрудников по отделу.
        int index = 0;
        for (int i = 0; i < departmentsEmployee.length; i++) {
            for (int j = index; j < employees.length; j++) {
                if (employees[j].getDepartment() == department) {
                    departmentsEmployee[i] = employees[j];
                    index = j + 1; // +1, чтобы следующую итерацию начать со следующего элемента исходного массива.
                    break;
                }
            }
        }
        int index1 = 0;
        for (int k = 0; k < departmentsEmployee.length; k++) {
            if (departmentsEmployee[k].getSalary() < departmentsEmployee[index1].getSalary()) {
                index1 = k;
            }
        }
        return departmentsEmployee[index1];
    }
    // Получить в качестве параметра номер отдела и найти сотрудника с максимальной зарплатой
    private static Employee findHighPaidEmployee(Employee[] employees, int department) {
        int counter = 0; // Определяем количество сотрудников в выбранном отделе.
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == department) {
                counter++;
            }
        }
        Employee[] departmentsEmployee = new Employee[counter]; // Создаем массив c выборкой сотрудников по отделу.
        int index = 0;
        for (int i = 0; i < departmentsEmployee.length; i++) {
            for (int j = index; j < employees.length; j++) {
                if (employees[j].getDepartment() == department) {
                    departmentsEmployee[i] = employees[j];
                    index = j + 1; // +1, чтобы следующую итерацию начать со следующего элемента исходного массива.
                    break;
                }
            }
        }
        int index1 = 0;
        for (int k = 0; k < departmentsEmployee.length; k++) {
            if (departmentsEmployee[k].getSalary() > departmentsEmployee[index1].getSalary()) {
                index1 = k;
            }
        }
        return departmentsEmployee[index1];
    }
    // Получить в качестве параметра номер отдела и найти сумму затрат на зарплату по отделу
    private static int findDepartmentSalary(Employee[] employees, int department) {
        int totalDepartmentSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == department) {
                totalDepartmentSalary += employees[i].getSalary();
            }
        }
        return totalDepartmentSalary;
    }
    // Получить в качестве параметра номер отдела и найти среднюю зарплату по отделу
    private static int findDepartmentAverageSalary(Employee[] employees, int department) {
        int counter = 0; // Определяем количество сотрудников в выбранном отделе.
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == department) {
                counter++;
            }
        }
        int totalDepartmentSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == department) {
                totalDepartmentSalary += employees[i].getSalary();
            }
        }
        return totalDepartmentSalary / counter;
    }
    // Проиндексировать зарплату всех сотрудников на процент, который приходит в качестве параметра
    private static void indexDepartmentSalary(Employee[] employees, int department, int increaseSalary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == department) {
                employees[i].setSalary((employees[i].getSalary() + (employees[i].getSalary() * increaseSalary / 100)));
            }
        }
    }
    // Напечатать всех сотрудников отдела (все данные, кроме отдела).
    private static void printDepartmentEmployee(Employee[] employees, int department) {
        int counter = 0; // Определяем количество сотрудников в выбранном отделе.
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == department) {
                counter++;
            }
        }
        Employee[] departmentsEmployee = new Employee[counter]; // Создаем массив c выборкой сотрудников по отделу.
        int index = 0;
        for (int i = 0; i < departmentsEmployee.length; i++) {
            for (int j = index; j < employees.length; j++) {
                if (employees[j].getDepartment() == department) {
                    departmentsEmployee[i] = employees[j];
                    index = j + 1; // +1, чтобы следующую итерацию начать со следующего элемента исходного массива.
                    break;
                }
            }
        }
        for (Employee employee : departmentsEmployee) {
            System.out.println(employee.getFullName());
        }
    }
    // Получить в качестве параметра число и найти всех сотрудников с меньшей ЗП).
    private static void printEmployeeWithMinSalary(Employee[] employees, int compareSalary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() < compareSalary) {
                System.out.println(employees[i].toStringShort());
            }
        }
    }
    // Получить в качестве параметра число и найти всех сотрудников с большей ЗП).
    private static void printEmployeeWithMaxSalary(Employee[] employees, int compareSalary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() > compareSalary) {
                System.out.println(employees[i].toStringShort());
            }
        }
    }
}