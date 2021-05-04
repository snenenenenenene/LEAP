package com.bavostepbros.leap.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Strategy {

    @Id
    @GeneratedValue
    @Column(name = "STRATEGYID")
    private Integer strategyId;

    @OneToOne
    @JoinColumn
    private Status status;
    
    @Column(name = "STRATEGYNAME")
    private String strategyName;
    
    @Column(name = "TIMEFRAMESTART")
    private LocalDate timeFrameStart;
    
    @Column(name = "TIMEFRAMEEND")
    private LocalDate timeFrameEnd;

    @ManyToOne
    private Environment environment;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="STRATEGYID")
    private List<StrategyItem> items = new ArrayList<StrategyItem>();

    public Strategy(Status status, String strategyName, LocalDate timeFrameStart, LocalDate timeFrameEnd) {
        this.status = status;
        this.strategyName = strategyName;
        this.timeFrameStart = timeFrameStart;
        this.timeFrameEnd = timeFrameEnd;
    }

    @Override
    public String toString() {
        return "{" +
            " strategyId='" + getStrategyId() + "'" +
            ", statusId='" + getStatus() + "'" +
            ", strategyName='" + getStrategyName() + "'" +
            ", timeFrameStart='" + getTimeFrameStart() + "'" +
            ", timeFrameEnd='" + getTimeFrameEnd() + "'" +
            "}";
    }

}