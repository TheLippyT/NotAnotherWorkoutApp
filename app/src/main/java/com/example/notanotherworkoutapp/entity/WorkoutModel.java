package com.example.notanotherworkoutapp.entity;

public class WorkoutModel {
    private int workoutId, userId, exerciseId;
    private String workoutName;

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    @Override
    public String toString() {
        return "WorkoutModel{" +
                "workoutId=" + workoutId +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", workoutName='" + workoutName + '\'' +
                '}';
    }
}
