package jpa.relationship.one.many;

import jpa.relationship.one.one.Employee;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "department")  //Ò»¶Ô¶à
    private List<Employee> employees;
}