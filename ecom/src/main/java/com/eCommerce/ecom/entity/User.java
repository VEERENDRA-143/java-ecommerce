package com.eCommerce.ecom.entity;

import com.eCommerce.ecom.enums.Userrole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String email;

    private  String password;

    private  String name;

    private Userrole role;

    //To store the large data we use this annotation

    @Lob  
    @Column(columnDefinition = "longblob")
    private byte[] img;

}
