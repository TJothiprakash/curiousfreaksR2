package lld.nokiasnakegame;

public class Main2 {
    public static void main(String[] args) {
        PopularityTracker popularityTracker = new PopularityTracker();
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        System.out.println(popularityTracker.mostPopular());          // returns 8
        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(7);
        System.out.println(popularityTracker.mostPopular());          // returns 7
        popularityTracker.decreasePopularity(8);
        popularityTracker.decreasePopularity(7);
        System.out.println(popularityTracker.mostPopular());       // returns 7, since 7
    }
}
