package com.klef.dev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sports_table")
public class Sports {

    @Id
    @Column(name = "player_id")
    private int id;

    @Column(name = "player_name", nullable = false, length = 50)
    private String name;

    @Column(name = "player_gender", nullable = false, length = 10)
    private String gender; // MALE or FEMALE

    @Column(name = "player_sport", nullable = false, length = 30)
    private String sport; // e.g., Cricket, Football, Badminton

    @Column(name = "player_team", nullable = false, length = 50)
    private String team; // Team name or group

    @Column(name = "player_position", nullable = false, length = 30)
    private String position; // e.g., Striker, Goalkeeper, Batsman

    @Column(name = "player_coach", nullable = false, length = 50)
    private String coach; // Coach name

    @Column(name = "player_level", nullable = false, length = 20)
    private String level; // e.g., College, District, State, National

    @Column(name = "player_email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "player_contact", nullable = false, unique = true, length = 20)
    private String contact;

    // ---------- Getters and Setters ----------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // ---------- toString() ----------
    @Override
    public String toString() {
        return "Sports [id=" + id + ", name=" + name + ", gender=" + gender + ", sport=" + sport
                + ", team=" + team + ", position=" + position + ", coach=" + coach + ", level=" + level
                + ", email=" + email + ", contact=" + contact + "]";
    }
}
