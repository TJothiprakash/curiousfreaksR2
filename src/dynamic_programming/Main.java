package dynamic_programming;

public class Main {
    public static void main(String[] args) {
     FrogJump frogJump = new FrogJump();
//        System.out.println(frogJump.countWays(7));
//        System.out.println(frogJump.countWays(35));
//        System.out.println(frogJump.countWays2(10));
        HouseRobber houseRobber = new HouseRobber();
        // System.out.println(houseRobber.rob(new int[]{1,2,3,1}));
        System.out.println(houseRobber.robCircularHouses(new int[]{2,1,2,5}));
        //System.out.println(houseRobber.robCircularHouses(new int[]{2,3,2,4,4,6,3,4,7,5,7,5,4,4,5,3,3,7}));
    }
}
