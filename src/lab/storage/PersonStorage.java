package lab.storage;

import lab.Main;
import lab.Validator;
import lab.entity.Entity;
import lab.entity.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class PersonStorage extends Storage {
    {
        comparators.put("name", new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;
                if (p1.getName().charAt(0) > p2.getName().charAt(0)) { return 1; }
                if (p1.getName().charAt(0) < p2.getName().charAt(0)) { return -1; }
                return 0;
            }
        });

        comparators.put("surname", new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;
                if (p1.getSurname().charAt(0) > p2.getSurname().charAt(0)) { return 1; }
                if (p1.getSurname().charAt(0) < p2.getSurname().charAt(0)) { return -1; }
                return 0;
            }
        });
    }

    public PersonStorage(String fileName, Class<? extends Entity> clazz) {
        super(fileName, clazz);
    }

    public ArrayList<Entity> smartFindByFullName(String name) {
        ArrayList<Entity> results = new ArrayList<Entity>();
        String[] parts = name.split(" ");

        for (int i = 0, len = parts.length; i < len; ++i) {
            for (Entity entity : entities) {
                Person person = (Person) entity;
                if (person.getName().indexOf(parts[i]) > -1 || person.getSurname().indexOf(parts[i]) > -1 || person.getMiddleName().indexOf(parts[i]) > -1) {
                    results.add(person);
                }
            }
        }

        return results;
    }

    protected String readName() {
        System.out.print("Enter the full name: ");
        String name = "";
        while (!Validator.isCorrectFullName(name)) {
            if (!name.isEmpty()) {
                System.out.println("Bad name input! The name may consist of 3 letter-only words. Try again!");
            }
            name = Main.readLine();
        }
        return Main.toUpperCaseFirstLetter(name);
    }

    protected Person readAndFindPerson() {
        System.out.println("Enter name, surname or middle name of the student, who is supposed to be found:");
        ArrayList<Entity> people;
        while ((people = smartFindByFullName(Main.readLine())).size() == 0) {
            System.out.println("Nobody has been found. Probably, you've made a typo? Try again, anyway!");
        }

        printWithIndexes(people);

        if (people.size() > 1) {
            System.out.print("Enter the index of the student from the list above: ");
        }
        Person p = (Person) people.get(people.size() > 1 ? Main.scanner.nextInt() - 1 : 0);
        p.print();
        System.out.println("......");

        return p;
    }

    @Override
    public void find(String by) {
        if (by.equals("name")) {
            printWithIndexes(smartFindByFullName(Main.readLine("Specify either name, surname or middle name: ")));
        } else {
            System.out.println("There is no such an option for search: '" + by + "'");
        }
    }

    @Override
    public void print(String sortBy) {
        sortBy = sortBy.toLowerCase();

        String fDecision = Main.readLine("Do you want to specify the faculty?");
        boolean facSpecified = false;
        int fac = 0;
        if (fDecision.equals("y")) {
            facSpecified = true;
            fac = Main.storages.get("faculty").getSelectedIdFromQuickMenu();
        }

        String dDecision = Main.readLine("Do you want to specify the department?");
        boolean depSpecified = false;
        int dep = 0;
        if (dDecision.equals("y")) {
            depSpecified = true;
            if (facSpecified) {
                dep = Main.storages.get("department").getSelectedIdFromQuickMenu(((DepartmentStorage) Main.storages.get("department")).findByFaculty(fac));
            } else {
                dep = Main.storages.get("department").getSelectedIdFromQuickMenu();
            }
        }

        ArrayList<Person> people = new ArrayList<Person>();

        for (Entity e : entities) {
            Person p = (Person) e;
            if ((!facSpecified || fac == p.getFaculty()) && (!depSpecified || dep == p.getDepartment())) {
                people.add(p);
            }
        }

        readAndApplyFilter(people);

        if (comparators.containsKey(sortBy)) {
            Collections.sort(people, comparators.get(sortBy));
        }

        if (people.size() == 0) {
            System.out.println("Nobody was found ...");
        } else {
            for (Person s : people) {
                s.print();
            }
        }
    }

    public void removeByDepartment(int dep) {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Person p = (Person) it.next();
            if (p.getDepartment() == dep) {
                it.remove();
            }
        }
    }

    @Override
    public void delete() {
        Person st = readAndFindPerson();
        entities.remove(st);
        System.out.println(st.getName() + " just said 'Ciao!'");
    }
}