package objects;

public class Food {

    private String foodName;
    private boolean cooked = false;

    public Food(String foodName) {
        if (cooked == false) {
            this.foodName = "Raw " + foodName;
        } else {
            this.foodName = "Cooked " + foodName;
        }
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void cookFood() {
        this.cooked = true;
        this.foodName = "Cooked " + foodName;
    }


}
