package com.example.notanotherworkoutapp.entity;

import java.io.Serializable;

public class WorkoutModel implements Serializable {
    private int userId, workoutId, exercisesId;
    private String firstName, lastName, email, password, workoutName, exerciseName, exerciseType, exerciseDescription, exerciseAccessory;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getExercisesId() {
        return exercisesId;
    }

    public void setExercisesId(int exercisesId) {
        this.exercisesId = exercisesId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String getExerciseAccessory() {
        return exerciseAccessory;
    }

    public void setExerciseAccessory(String exerciseAccessory) {
        this.exerciseAccessory = exerciseAccessory;
    }

    @Override
    public String toString() {
        return "WorkoutModel{" +
                "userId=" + userId +
                ", workoutId=" + workoutId +
                ", exercisesId=" + exercisesId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", workoutName='" + workoutName + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", exerciseType='" + exerciseType + '\'' +
                ", exerciseDescription='" + exerciseDescription + '\'' +
                ", exerciseAccessory='" + exerciseAccessory + '\'' +
                '}';
    }
}
