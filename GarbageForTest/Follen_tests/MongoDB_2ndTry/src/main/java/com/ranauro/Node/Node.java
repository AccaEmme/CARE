/**
 * @author giuliano ranauro
 * Date: 17/05/2021 22:44
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package com.ranauro.Node;

import com.ranauro.blood.BloodBag;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String nodeID;
    private List<BloodBag> bags;
    private Location location;
    private int bagsCapacity;       //numero massimo di sacche, non so quanto possa essere utile.

    public Node(ArrayList<BloodBag> bags, Location location, int bagsCapacity, String nodeID) {
        this.bags = bags;
        this.location = location;
        this.bagsCapacity = bagsCapacity;
        this.nodeID = nodeID;
    }

    public Node(Location location, int bagsCapacity, String nodeID){
        this.bags = new ArrayList<>();
        this.location = location;
        this.bagsCapacity = bagsCapacity;
        this.nodeID = nodeID;
    }

    public void removeBag(BloodBag bag){
        bags.remove(bag);
    }
    public void removeBag(List<BloodBag> bags){
        for (BloodBag bag : bags)
            this.bags.remove(bag);
    }

    public List<BloodBag> getBags() {
        return bags;
    }

    public void setBags(List<BloodBag> bags) {
        this.bags = bags;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getBagsCapacity() {
        return bagsCapacity;
    }

    public void setBagsCapacity(int bagsCapacity) {
        this.bagsCapacity = bagsCapacity;
    }
}
