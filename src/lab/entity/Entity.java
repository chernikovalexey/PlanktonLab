package lab.entity;

public abstract class Entity {
    protected int id;
    protected String name;

    public Entity() {}

    public Entity createInstanceFromLine(String line) {
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public void print() {
		System.out.println(this);
	}
}