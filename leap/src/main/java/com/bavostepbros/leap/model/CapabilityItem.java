package com.bavostepbros.leap.model;

@Entity
@IdClass(CapabilityItemId.class)
public class CapabilityItem {

    @Id 
    @ManyToOne
    @JoinColumn(name = "Capability_capabilityId", referencedColumnName = "capabilityId")
    private Capability capability;

    @Id 
    @ManyToOne
    @JoinColumn(name = "StrategyItem_itemId", referencedColumnName = "itemId")
    private StrategyItem strategyItem;

    private Integer strategicImportance;

    public CapabilityItem() {
    }

    public CapabilityItem(Integer strategicImportance) {
        this.strategicImportance = strategicImportance;
    }

    public Capability getCapability() {
        return this.capability;
    }

    public void setCapability(Capability capability) {
        this.capability = capability;
    }

    public CapabilityItem getStrategyItem() {
        return this.strategyItem;
    }

    public void setStrategyItem(CapabilityItem strategyItem) {
        this.strategyItem = strategyItem;
    }

    public Integer getStrategicImportance() {
        return this.strategicImportance;
    }

    public void setStrategicImportance(Integer strategicImportance) {
        this.strategicImportance = strategicImportance;
    }

    @Override
    public String toString() {
        return "{" +
            " capability='" + getCapability() + "'" +
            ", strategyItem='" + getStrategyItem() + "'" +
            ", strategicImportance='" + getStrategicImportance() + "'" +
            "}";
    }

}