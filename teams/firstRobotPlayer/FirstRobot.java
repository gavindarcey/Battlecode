package firstRobotPlayer;

import battlecode.common.*;

public class FirstRobot {

    private static RobotController rc;
    private static MapLocation rallyPoint;
    
    public static void run(RobotController myRC) {
        
        rc = myRC;
        rallyPoint = findRallyPoint();
        
        while(true){
            try{
                if (rc.getType() == RobotType.SOLDIER){
                    soliderCode();
                } else{
                    hqCode();
                }
                // Catch is a 500 bytecode penalty
            } catch (Exception e){
                System.out.println("Caught exception before we got killed");
                e.printStackTrace();
        }
            rc.yield();
        }
    }
    
    private static void soliderCode() throws GameActionException {
        int dist = rc.getLocation().distanceSquaredTo(rallyPoint);
        if (dist>0 && rc.isActive()){
            Direction dir = rc.getLocation().directionTo(rallyPoint);
            int[] directionOffsets = {0,1,-1,2,-2};
            for (int d:directionOffsets){
                Direction lookingAtCurrently = Direction.values()[(dir.ordinal())
                
            }
            if (rc.isActive() && rc.canMove(dir)){
                rc.move(dir);
            }
        }
    }

    private static MapLocation findRallyPoint() {
        MapLocation enemyLoc = rc.senseEnemyHQLocation();
        MapLocation ourLoc = rc.senseHQLocation();
        int x = enemyLoc.x + 3*ourLoc.x / 4;
        int y = enemyLoc.y + 3*ourLoc.y / 4;
        MapLocation rallyPoint = new MapLocation(x,y);
        return rallyPoint;
    }

    public static void hqCode() throws GameActionException{
        if (rc.isActive()) {
            // Spawn a soldier
            Direction dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
            if (rc.canMove(dir))
                rc.spawn(dir);
        }
    }
    
}


