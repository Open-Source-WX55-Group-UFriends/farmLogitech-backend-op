package com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public record TimeTask(int time, LocalDate endDate) {       //watch de LocalDate idk what kind of variable should be
    public TimeTask(){this(0,null);}
    public TimeTask{
        //check the validation, i'm not sure how to validate it because it is an int
        if(time == 0) throw new IllegalArgumentException("Time must be greater than 0");

        //check how can I add new validations
        if(endDate == null) throw new IllegalArgumentException("End date must be provided");
    }
    public String getFullTime(){
        return String.format("%s %s", time, endDate);
    }

}
