package com.mobdeve.pawpal.Model;

public class pets {
    private String name, breed, sex;
    private Integer age;
    private int petphoto;

    public pets(String name, String breed, String sex, Integer age, int petphoto) {
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.petphoto = petphoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getPetphoto() {
        return petphoto;
    }

    public void setPetphoto(int petphoto) {
        this.petphoto = petphoto;
    }
}

