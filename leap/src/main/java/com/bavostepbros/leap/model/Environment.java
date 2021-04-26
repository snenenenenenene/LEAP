package com.bavostepbros.leap.model;

@Entity
public class Environment {

    @Id
    @GeneratedValue
    private Integer environmentId;

    private String environmentName;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="ENVIRONMENTID")
    private Set<Strategy> strategies;

    public Environment() {
    }

    public Environment(String environmentName) {
        this.environmentName = environmentName;
    }

    public Integer getEnvironmentId() {
        return this.environmentId;
    }

    public void setEnvironmentId(Integer environmentId) {
        this.environmentId = environmentId;
    }

    public String getEnvironmentName() {
        return this.environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    @Override
    public String toString() {
        return "{" +
            " environmentId='" + getEnvironmentId() + "'" +
            ", environmentName='" + getEnvironmentName() + "'" +
            "}";
    }

}