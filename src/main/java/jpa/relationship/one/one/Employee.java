package jpa.relationship.one.one;

import jpa.relationship.many.Project;
import jpa.relationship.one.many.Department;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Long salary;

    @ManyToOne  //多对一
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne  //一对一关联
    @JoinColumn(name = "p_space_id")  //关联的表的外键
    private ParkingSpace parkingSpace;

    @ManyToMany  //多对多
    @JoinTable(name = "emp_proj", joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "proj_id"))
    private List<Project> projects;
}