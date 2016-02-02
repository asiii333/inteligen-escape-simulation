package pl.sp.simulation.model;

/**
 * Created by Asia on 2015-06-22.
 */
import java.util.ArrayList;

public class Point {
    private ArrayList<Point> neighbors;
    private int currentState;
    private int nextState;
    private int numStates = 6;

    public Point() {
        setCurrentState(0);
        setNextState(0);
        setNeighbors(new ArrayList<Point>());
    }

    public void clicked() {
        currentState=(++currentState)%numStates;
    }

    public int getState() {
        return getCurrentState();
    }

    public void setState(int s) {
        setCurrentState(s);
    }

    public void calculateNewState() {
        if(getNumberOfActiveNeighbors()>3 ){
            nextState = 1;
        }else{
            nextState = 0;
        }
    }

    public void changeState() {
        setCurrentState(getNextState());
    }

    public void addNeighbor(Point nei) {
        getNeighbors().add(nei);
    }

    public ArrayList<Point> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Point> neighbors) {
        this.neighbors = neighbors;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public int getNumStates() {
        return numStates;
    }

    public void setNumStates(int numStates) {
        this.numStates = numStates;
    }
    public int getNumberOfActiveNeighbors(){
        int liveCount = 0;
        for(Point point: neighbors){
            if(point.isBurn()) {
                liveCount++;
            }
        }
        return liveCount;
    }
    public ArrayList<Point> getActiveNeighbors(){
        ArrayList<Point> activeNeighbors = new ArrayList<Point>();
        for(Point point: neighbors){
            if(point.isBurn()){
                activeNeighbors.add(point);
            }
        }
        return activeNeighbors;
    }
    public boolean isBurn (){
        if(getState() == 0){
            return false;
        }
        return true;
    }
}

