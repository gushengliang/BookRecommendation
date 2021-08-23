package org.bookrec.entity.dto;

import org.bookrec.entity.Major;
import org.bookrec.entity.School;
import org.bookrec.entity.Student;

/**
 * StudentDto
 *
 * @author a1311
 */
public class StudentDto extends Student {
    /**
     * 学校
     */
    private School school;
    /**
     * 专业
     */
    private Major major;

    public StudentDto() {

    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                ", username='" + super.getUsername() + '\'' +
                ", name='" + super.getName() + '\'' +
                "school=" + school +
                ", major=" + major +
                '}';
    }
}
