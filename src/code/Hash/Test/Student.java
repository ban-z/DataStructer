package code.Hash.Test;

public class Student {
    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B = 31;

        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.hashCode();
        //firstName.toLowerCase();
        hash = hash * B + lastName.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            //判断引用
            return true;
        }

        if (obj == null){
            //判断空
            return false;
        }

        if (getClass() != obj.getClass()){
            //判断对应类类
            return false;
        }

        Student another = (Student)obj;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName == another.firstName &&
                this.lastName == another.lastName;
    }
}
