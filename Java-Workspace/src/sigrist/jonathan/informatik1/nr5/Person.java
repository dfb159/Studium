package sigrist.jonathan.informatik1.nr5;

/**
 * Created by tore on 10/25/16.
 */
public class Person {

    private Person mutter;
    private Person vater;
    private Person kind;
    private String name;

    public Person(String name){
        this.name=name;
    }

    public Person(String name, Person mutter, Person vater){
        this(name);
        this.mutter = mutter;
        this.vater = vater;
    }

    public Person(String name, Person mutter, Person vater, Person kind){
        this(name, mutter, vater);
        this.kind = kind;
    }

    public Person getVater() {
        return vater;
    }

    public void setVater(Person vater) {
        this.vater = vater;
    }

    public Person getMutter() {
        return mutter;
    }

    public void setMutter(Person mutter) {
        this.mutter = mutter;
    }

    public Person getKind() {
        return kind;
    }

    public void setKind(Person kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
