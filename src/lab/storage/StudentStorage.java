package lab.storage;

import lab.Main;
import lab.Validator;
import lab.entity.Entity;
import lab.entity.Person;
import lab.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

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

    private String readName() {
        System.out.print("Enter the full name: ");
        String name = "";
        while (!Validator.isCorrectFullName(name)) {
            if (!name.isEmpty()) {
                System.out.println("Bad input! Try again right now!");
            }
            name = Main.scanner.nextLine();
        }
        return Main.toUpperCaseFirstLetter(name);
    }

    @Override
    public void add() {
        System.out.println("Enter an academic department:");
        int department = Main.storages.get("department").getSelectedIdFromQuickMenu();

        System.out.print("Enter the year of study: ");
        int year = Main.scanner.nextInt();

        String name = readName();
        addEntityFromLine(new Student(getNewId(), department, year, name).toString());

        System.out.println("Added " + name + " to the database successfully.");
    }

    @Override
    public void edit() {
        System.out.println("Enter name, surname or middle name of the student, who is supposed to be edited: ");

        ArrayList<Entity> students;
        while ((students = smartFindByFullName(Main.scanner.nextLine())).size() == 0) {
            System.out.println("Nobody has been found. Probably, you've made a typo? Try again, anyway!");
        }

        if (students.size() > 1) {
            System.out.print("Enter the index of the student from the list above: ");
        }
        Student st = (Student) students.get(students.size() > 1 ? Main.scanner.nextInt() - 1 : 0);

        st.print();

        System.out.println("......");

        System.out.println("Do you want to change the department (y/n)?");
        String department = Main.scanner.nextLine();
        if (department.equals("y")) {
            System.out.println("Select the department, then: ");
            st.setDepartment(Main.storages.get("department").getSelectedIdFromQuickMenu());
        }

        System.out.println("Do you want to change the year of study (y/n)?");
        String year = Main.scanner.nextLine();
        if (year.equals("y")) {
            System.out.println("Enter the year, then: ");
            st.setYear(Main.scanner.nextInt());
        }

        System.out.println("Do you want to change the name (y/n)?");
        String name = Main.scanner.nextLine();
        if (name.equals("y")) {
            st.setName(readName());
        }

        System.out.println("Changes made and led to the following: ");
        st.print();
    }

    @Override
    public void delete() {
        System.out.println("Enter name, surname or middle name of the student, who is supposed to be deleted:");
        ArrayList<Entity> students = smartFindByFullName(Main.scanner.nextLine());
        printWithIndexes(students);
        System.out.print("Enter the index of the selected student from the list above: ");
        Entity e = students.get(Main.scanner.nextInt() - 1);
        entities.remove(e);
        System.out.println(e.getName() + " just said 'Ciao!'");
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