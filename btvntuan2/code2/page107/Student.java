package code2.page107;

public class Student {
    private String name;
    private int age;

    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    
    public static void main(String[] args) {
        
        Student student1 = new Student("John", 20);

       
        System.out.println("Student name: " + student1.getName());
        System.out.println("Student age: " + student1.getAge());
    }
}
// excercise 2
