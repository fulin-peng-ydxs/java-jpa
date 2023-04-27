package jpa.relationship.one.one;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ParkingSpace {
    @Id
    @GeneratedValue
    private Integer id;
 
    private String location;

    @OneToOne(mappedBy = "parkingSpace")
    @JoinColumn(name = "emp_id")  //˫��һ��һ
    private Employee employee;

}