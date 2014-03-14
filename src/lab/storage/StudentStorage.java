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

    private int readYear() {
        System.out.print("Enter the year of study: ");
        int year;
        while (!Validator.isInRange(year = Main.scanner.nextInt(), 1, 6)) {
            System.out.println("Year of study lies in the range between 1 and 6. Please, pay attention to this, and try once again!");
        }
        return year;
    }

    @Override
    public void add() {
        int department = Main.storages.get("department").getSelectedIdFromQuickMenu("Enter an academic department:");
        int year = readYear();
        String name = readName();

        addEntityFromLine(new Student(getNewId(), department, year, name).toString());

        System.out.println("Added " + name + " to the database successfully.");
    }

    @Override
    public void edit() {
        Student st = (Student) readAndFindPerson();

        String dd = Main.readLine("Do you want to change the department (y/n)?");
        if (dd.equals("y")) {
            st.setDepartment(Main.storages.get("department").getSelectedIdFromQuickMenu("Select the department, then: "));
        }

        String yd = Main.readLine("Do you want to change the year of study (y/n)?");
        if (yd.equals("y")) {
            st.setYear(readYear());
        }

        String nd = Main.readLine("Do you want to change the name (y/n)?");
        if (nd.equals("y")) {
            st.setName(readName());
        }

        System.out.println("Changes made and led to the following: ");
        st.print();
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
        } else {
            super.find(by);
        }
    }

    @Override
    protected void readAndApplyFilter(ArrayList<Person> people) {
        String yDecision = Main.readLine("Do you want to specify the year of study?");

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