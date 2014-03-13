package lab.storage;

import lab.Main;
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

    @Override
    public void find(String by) {
        if (by.equals("name")) {
            printWithIndexes(smartFindByFullName(Main.scanner.nextLine()));
        }
    }

    @Override
    public void print(String sortBy) {
        sortBy = sortBy.toLowerCase();

        System.out.println("Do you want to specify the faculty?");
        String fDecision = Main.scanner.nextLine();

        boolean facSpecified = false;
        int fac = 0;
        if (fDecision.equals("y")) {
            facSpecified = true;
            fac = Main.storages.get("faculty").getSelectedIdFromQuickMenu();
        }

        System.out.println("Do you want to specify the department?");
        String dDecision;
        while ((dDecision = Main.scanner.nextLine()).isEmpty()) {}

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

        for (Person s : people) {
            s.print();
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
}