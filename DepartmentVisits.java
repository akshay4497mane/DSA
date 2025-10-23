/**
 * Crux: Given a list of products with departments and a shopping list,
 * calculate how many department visits can be saved by grouping items
 * from the same department together instead of buying in the given order.
 */

import java.util.*;

public class DepartmentVisits {

    public static int shopping(String[][] products, List<String> shoppingList) {
        // Map product to its department
        Map<String, String> productDept = new HashMap<>();
        for (String[] p : products) {
            productDept.put(p[0], p[1]);
        }

        // Count old visits
        int oldVisits = 0;
        String prevDept = "";
        for (String item : shoppingList) {
            String dept = productDept.get(item);
            if (!dept.equals(prevDept)) {
                oldVisits++;
                prevDept = dept;
            }
        }

        // Count new visits (unique departments in the shopping list)
        Set<String> newDepts = new HashSet<>();
        for (String item : shoppingList) {
            newDepts.add(productDept.get(item));
        }
        int newVisits = newDepts.size();

        // Time saved = old visits - new visits
        return oldVisits - newVisits;
    }

    public static void main(String[] args) {
        String[][] products = {
            {"Cheese", "Dairy"}, {"Carrots", "Produce"}, {"Potatoes", "Produce"},
            {"Canned Tuna", "Pantry"}, {"Romaine Lettuce", "Produce"}, {"Chocolate Milk", "Dairy"},
            {"Flour", "Pantry"}, {"Iceberg Lettuce", "Produce"}, {"Coffee", "Pantry"},
            {"Pasta", "Pantry"}, {"Milk", "Dairy"}, {"Blueberries", "Produce"}, {"Pasta Sauce", "Pantry"}
        };

        List<String> list1 = Arrays.asList("Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots");
        List<String> list2 = Arrays.asList("Blueberries", "Carrots", "Coffee", "Milk", "Flour", "Cheese");
        List<String> list3 = Arrays.asList("Blueberries", "Carrots", "Romaine Lettuce", "Iceberg Lettuce");
        List<String> list4 = Arrays.asList("Milk", "Flour", "Chocolate Milk", "Pasta Sauce");
        List<String> list5 = Arrays.asList("Cheese", "Potatoes", "Blueberries", "Canned Tuna");

        System.out.println(shopping(products, list1)); // 2
        System.out.println(shopping(products, list2)); // 2
        System.out.println(shopping(products, list3)); // 0
        System.out.println(shopping(products, list4)); // 2
        System.out.println(shopping(products, list5)); // 0
    }
}
