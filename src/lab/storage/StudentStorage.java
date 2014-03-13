package lab.storage;

import lab.Main;
import lab.entity.Entity;
import lab.entity.Person;
import lab.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class StudentStorage extends PersonStorage {
    {
        comparators.put("year", new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                if (s1.getYear() > s2.getYear()) { return 1; }
                if (s1.getSurname().charAt(0) < s2.getYear()) { return -1; }
                return 0;
            }
        });
    }

    public StudentStorage() {
        super("res/students.dat", Student.class);
    }

    @Override
    public void add() {
        System.out.print("Input department: ");
        int department = Main.storages.get("department").findByName(Main.scanner.nextLine()).getId();

        System.out.print("Input studing year: ");
        int year = Main.scanner.nextInt();

        System.out.print("Input name: ");
        String name = new Scanner(System.in).nextLine();

        addEntityFromLine(new Student(getNewId(), department, year, name).toString());
    }

    @Override
    public void edit() {
        System.out.println("Input name, second or middle name: ");
        String fullName = Main.toUpperCaseFirstLetter(new Scanner(System.in).nextLine());
        ArrayList<Entity> students = smartFindByFullName(fullName);

        if (students.size() > 1) {
            printWithIndexes(students);
            System.out.println("Input index of students: ");
        }
        Student st = (Student) students.get(students.size() > 1 ? Main.scanner.nextInt() - 1 : 0);

        st.print();
        System.out.println("___________");

        System.out.println("Input department: ");
        String department = new Scanner(System.in).nextLine();
        if (!department.trim().isEmpty()) {
            st.setDepartment(Main.storages.get("department").findByName(department).getId());
        }

        System.out.println("Input studying year: ");
        String year = new Scanner(System.in).nextLine();
        if (!year.trim().isEmpty()) { st.setYear(Integer.parseInt(year)); }

        System.out.println("Input full name: ");
        String name = new Scanner(System.in).nextLine();
        if (!name.trim().isEmpty()) { st.setName(name); }
    }

    @Override
    public void delete() {
        System.out.print("Input name or surname or middle name: ");
        ArrayList<Entity> students = smartFindByFullName(Main.scanner.nextLine());
        printWithIndexes(students);
        entities.remove(students.get(Main.scanner.nextInt() - 1));
    }

    private ArrayList<Entity> findByYear(int year) {
        ArrayList<Entity> results = new ArrayList<Entity>();
        for (Entity e : entities) {
            Student s = (Student) e;
            if (s.getYear() == year) {
                results.add(s);
            }
        }
        return results;
    }

    @Override
    public void find(String by) {
        if (by.equals("year")) {
            System.out.println("Year:");
            printWithIndexes(findByYear(Main.scanner.nextInt()));
        }
        super.find(by);
    }

    @Override
    protected void readAndApplyFilter(ArrayList<Person> people) {
        System.out.println("Do you want to specify the year of study?");
        String yDecision;
        while ((yDecision = Main.scanner.nextLine()).isEmpty()) {}

        if (yDecision.equals("y")) {
            System.out.println("Year:");
            int year = Main.scanner.nextInt();

            Iterator<Person> it = people.iterator();
            while (it.hasNext()) {
                Student s = (Student) it.next();
                if (s.getYear() != year) {
                    it.remove();
                }
            }
        }
    }
}