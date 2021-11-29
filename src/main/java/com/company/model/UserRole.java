package com.company.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author M
 */

@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role roleId;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public UserRole() {
    }

    public UserRole(Role roleId) {
        this.roleId = roleId;
    }

    public UserRole(Integer id) {
        this.id = id;
    }

    public UserRole(Role roleId, User userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

}