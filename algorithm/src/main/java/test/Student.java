package test;

/**
 * @author chenkechao
 * @date 2019/10/10 9:19 下午
 */
public class Student implements Comparable<Student> {

    private String name;

    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        return this.score - o.score;
    }

    @Override
    public String toString() {
        return "test.Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
