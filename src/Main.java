public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        employees[0] = new Employee("Иванов И.И.", 1, 70000);
        employees[1] = new Employee("Смирнов С.С.", 1, 80000);
        employees[2] = new Employee("Сидорова С.С.", 1, 90000);
        employees[3] = new Employee("Петров П.П.", 2, 100000);
        employees[4] = new Employee("Троцкий Т.Т.", 2, 115000);
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
    }

    public static void getListEmployees(Employee[] employees) {
        System.out.println("Список всех сотрудников");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static int calculateSummarySalary(Employee[] employees) {
        int total = 0;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }

    private static Employee calculateEmployeeMinSalary(Employee[] employees) {
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() < employees[index].getSalary()) {
                index = i;
            }
        }
        return employees[index];
    }
    private static Employee calculateEmployeeMaxSalary(Employee[] employees) {
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() > employees[index].getSalary()) {
                index = i;
            }
        }
        return employees[index];
    }
    private static double getAverageSalary(Employee[] employees) {
        return (double) calculateSummarySalary(employees) / employees.length;
    }
    private static void printEmployeesList(Employee[] employees) {
        for (Employee employee: employees) {
            System.out.println(employee.getFullName());
        }
    }
}