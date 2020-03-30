package ly.bsagar.bakingapp.POJO;

public class Ingredient {
    String ingredient;
    String measure;
    double quantity;

    public Ingredient(String ingredient, String measure, double quantity) {
        this.ingredient = ingredient;
        this.measure = measure;
        this.quantity = quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public double getQuantity() {
        return quantity;
    }
}