package de.jmf.application.repositories;

import java.util.ArrayList;
import java.util.List;


public class GymPlanRepository {
    private List<String[]> gymPlan;

    public GymPlanRepository() {
        this.gymPlan = new ArrayList<>();
    }

    public List<String[]> getGymPlan(String userMail) {
        return this.gymPlan;
    }

    public void setGymPlan(List<String[]> gymPlan) {
        this.gymPlan = gymPlan;
    }
}