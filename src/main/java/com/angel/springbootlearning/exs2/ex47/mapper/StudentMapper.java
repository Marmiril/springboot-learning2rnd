/*
 * Exercise 47 – Separate Mapper
 *
 * Purpose:
 * Move domain-to-DTO conversion into a dedicated class.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex47.mapper;

import com.angel.springbootlearning.exs2.ex47.dto.StuRes47;
import com.angel.springbootlearning.exs2.ex47.model.Stud47;

public class StudentMapper {
    public StuRes47 toResponse(Stud47 student) {
        return new StuRes47(
            student.id(),
            student.name(),
            student.role()
        );
    }
    
}
