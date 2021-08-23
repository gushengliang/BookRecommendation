package org.bookrec.entity.dto;

import org.bookrec.entity.Book;
import org.bookrec.entity.Evaluation;
import org.bookrec.entity.Student;

/**
 * @author jzt
 */
public class EvaluationDto extends Evaluation {
    private Student student;
    private Book book;
    public EvaluationDto(){

    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "EvaluationDto{" +
                "id=" + super.getId()+
                ", bookId=" + super.getBookId() +
                ", studentId=" + super.getStudentId() +
                ", level=" + super.getLevel() +
                ", comment='" + super.getComment() + '\'' +
                ", status=" + super.getStatus() +
                "student=" + student +
                ", book=" + book +
                '}';
    }
}
