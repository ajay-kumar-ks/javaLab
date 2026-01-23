import java.util.Scanner;
public class Student {
    int rollno;
    String name;
    float mark;
    static int count = 0;
    public Student(){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter student name");
        this.name = sc.nextLine();
        System.out.println("enter roll number");
        this.rollno = sc.nextInt();
        System.out.println("enter mark");
        this.mark = sc.nextFloat();
        count ++;
    }
    public Student(int rollnum,String name,float mark){
        this.name = name;
        this.rollno = rollnum;
        this.mark = mark;
    }
    public static void main(String[] args){
        Student s[] = new Student[10];
        for(int i=0;i<5;i++){
            s[i] = new Student();
        }
        stdsort(s);
        System.out.println("\nTotal number of students :"+count);
        System.out.println("\nRank list:");
        for (int i = 0; i < count; i++) {
            System.out.println(s[i].rollno + " " + s[i].name + " " + s[i].mark);
        }

        Student s1 = s[0];
        Student s2 = s[1];

        int result = s1.compareByMark(s2);

        if (result > 0)
            System.out.println(s1.name + " has higher marks");
        else if (result < 0)
            System.out.println(s2.name + " has higher marks");
        else
            System.out.println("Both have equal marks");

    }
    public static void stdsort(Student[] s) {
        Student temp;

        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) { 
                if (s[i].mark < s[j].mark) {
                    temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
            }
        }
    }

    public int compareByMark(Student other) {
        if (this.mark > other.mark)
            return 1;
        else if (this.mark < other.mark)
            return -1;
        else
            return 0;
    }


}
