package jpa.relationship.many;

import jpa.relationship.one.one.Employee;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    private String name;
 
    @ManyToMany(mappedBy = "projects") //¶à¶Ô¶à
    private List<Employee> employees;
}