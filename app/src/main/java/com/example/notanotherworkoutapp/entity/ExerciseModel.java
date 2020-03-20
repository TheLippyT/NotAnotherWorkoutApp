package com.example.notanotherworkoutapp.entity;

import java.io.Serializable;

public class ExerciseModel implements Serializable {
    private int exerciseId;
    private String exerciseName, exerciseType, exerciseDescription, exerciseAccessory;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
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
        return "ExerciseModel{" +
                "exerciseId=" + exerciseId +
                ", exerciseName='" + exerciseName + '\'' +
                ", exerciseType='" + exerciseType + '\'' +
                ", exerciseDescription='" + exerciseDescription + '\'' +
                ", exerciseAccessory='" + exerciseAccessory + '\'' +
                '}';
    }
}
