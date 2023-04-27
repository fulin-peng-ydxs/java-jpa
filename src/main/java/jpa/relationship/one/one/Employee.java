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

    @ManyToOne  //���һ
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne  //һ��һ����
    @JoinColumn(name = "p_space_id")  //�����ı�����
    private ParkingSpace parkingSpace;

    @ManyToMany  //��Զ�
    @JoinTable(name = "emp_proj", joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "proj_id"))
    private List<Project> projects;
}