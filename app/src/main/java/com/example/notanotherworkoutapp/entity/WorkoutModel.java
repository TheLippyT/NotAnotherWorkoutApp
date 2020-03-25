package com.example.notanotherworkoutapp.entity;

import java.io.Serializable;

public class WorkoutModel implements Serializable {
    private int workoutsId, workout_exerciseId, exercisesId;
    private String workoutName, exerciseName, exerciseType, exerciseDescription, exerciseAccessory;

    public int getWorkoutsId() {
        return workoutsId;
    }

    public void setWorkoutsId(int workoutsId) {
        this.workoutsId = workoutsId;
    }

    public int getWorkout_exerciseId() {
        return workout_exerciseId;
    }

    public void setWorkout_exerciseId(int workout_exerciseId) {
        this.workout_exerciseId = workout_exerciseId;
    }

    public int getExercisesId() {
        return exercisesId;
    }

    public void setExercisesId(int exercisesId) {
        this.exercisesId = exercisesId;
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
}
