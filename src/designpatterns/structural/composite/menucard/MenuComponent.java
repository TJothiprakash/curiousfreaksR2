package designpatterns.structural.composite.menucard;

import java.util.List;

public interface MenuComponent {
    void showmenuDetails();

    boolean isVegetarian(String dish);

    long getPrice(String dish);
}

class Menu implements MenuComponent {
    private String name;
    private String description;
    private List<MenuItem> menuItems;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void showmenuDetails() {
        System.out.println("Menu Details");
        for (MenuItem menuItem : menuItems) {
            menuItem.showmenuDetails();
        }
    }

    @Override
    public boolean isVegetarian(String dish) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isVegetarian(dish)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long getPrice(String dish) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isVegetarian(dish)) {
                return menuItem.getPrice(dish);
            }
        }
        return 0L;
    }

}

class MenuItem implements MenuComponent {

    private String name;
    private String description;
    private boolean vegetarian;
    private double price;

    @Override
    public void showmenuDetails() {
        System.out.println("MenuItem Details");
    }

    @Override
    public boolean isVegetarian(String dish) {
        return false;
    }

    @Override
    public long getPrice(String dish) {
        return 0;
    }

}

class LunchMenu implements MenuComponent {
    private String name;
    private String description;
    private List<MenuItem> menuItems;

    public LunchMenu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void showmenuDetails() {
        System.out.println("Lunch Menu Details");
        for (MenuItem menuItem : menuItems) {
            menuItem.showmenuDetails();
        }
    }

    @Override
    public boolean isVegetarian(String dish) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isVegetarian(dish)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long getPrice(String dish) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isVegetarian(dish)) {
                return menuItem.getPrice(dish);
            }
        }
        return 0L;
    }

}

// similarly for BreakfastMenu, dinner menu etc
