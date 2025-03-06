package de.jmf.application.repositories;

import java.util.List;


public class GymPlanRepository {
    private List<String[]> gymPlan;

    public List<String[]> getGymPlan(String userMail) throws Exception {
        return this.gymPlan;
    }

    public void setGymPlan(List<String[]> gymPlan) {
        this.gymPlan = gymPlan;
    }
}