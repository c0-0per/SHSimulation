package objects;

import java.util.List;

/**
 * The Owen class extends the Device class and represents an oven in a smart home environment.
 * This class is responsible for baking food items.
 */
public class Owen extends Device {
    /**
     * Bakes a list of food items. If the list contains more than one food item,
     * it combines them into a single food item with a concatenated name.
     * If there's only one item, it simply bakes that item.
     *
     * @param foodList A list of Food items to be baked.
     * @return A new Food item representing the baked food. If multiple items were baked,
     * it returns a combined Food item. If only one item was baked, it returns that item.
     */
    public Food bakeFood(List<Food> foodList) {
        if (foodList.size() > 1) {
            String nameOfCookedFood = "";
            for (Food food : foodList) {
                food.cookFood();
                nameOfCookedFood += "Baked " + food.getFoodName() + " with ";
            }
            foodList.clear();
            return new Food(nameOfCookedFood);
        } else {
            Food bakedFood = foodList.get(0);
            bakedFood.cookFood();
            return bakedFood;
        }
    }
}
