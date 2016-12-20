package ru.innopolis.university.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by imac on 12.12.16.
 */
public class Duck {
    private String name;
    private long id;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<DuckProfile> duckProfiles = new HashSet<UserProfile>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
