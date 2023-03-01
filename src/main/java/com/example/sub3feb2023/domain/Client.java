package com.example.sub3feb2023.domain;

public class Client extends Entity<Long>{
    private String name;
    private Integer fidelityGrade;
    private Integer varsta;
    private HobbiesENUM hobbies;

    public Client(Long aLong, String name, Integer fidelityGrade, Integer varsta, HobbiesENUM hobbies) {
        super(aLong);
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.varsta = varsta;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFidelityGrade() {
        return fidelityGrade;
    }

    public void setFidelityGrade(Integer fidelityGrade) {
        this.fidelityGrade = fidelityGrade;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public HobbiesENUM getHobbies() {
        return hobbies;
    }

    public void setHobbies(HobbiesENUM hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", fidelityGrade=" + fidelityGrade +
                ", varsta=" + varsta +
                ", hobbies=" + hobbies +
                '}';
    }
}
