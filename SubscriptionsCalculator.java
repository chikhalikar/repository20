package test;


	import java.util.ArrayList;
	import java.util.List;
	public class SubscriptionsCalculator {

	    private static List<List<String>> combinations = new ArrayList<>();

	    public static List<List<String>> findCombinations(List<Newspaper> newspapers, double budget) {
	        combinations.clear();
	        findCombinationsHelper(newspapers, budget, new ArrayList<>(), 0, 0);
	        return combinations;
	    }

	    private static void findCombinationsHelper(List<Newspaper> newspapers, double budget, List<String> currentCombination, int index, int currentDay) {
	        if (budget < 0.0) {
	            return; // This combination exceeds the budget, abort.
	        }

	        if (budget == 0.0) {
	            combinations.add(new ArrayList<>(currentCombination));
	            return;
	        }

	        if (index == newspapers.size()) {
	            return;
	        }

	        Newspaper currentNewspaper = newspapers.get(index);
	        for (int i = 0; i * currentNewspaper.prices[currentDay] <= budget; i++) {
	            for (int j = 0; j < i; j++) {
	                currentCombination.add(currentNewspaper.name);
	            }
	            findCombinationsHelper(newspapers, budget - i * currentNewspaper.prices[currentDay], currentCombination, index + 1, (currentDay + 1) % 7);
	            for (int j = 0; j < i; j++) {
	                currentCombination.remove(currentCombination.size() - 1);
	            }
	        }
	        // Try the next newspaper
	        findCombinationsHelper(newspapers, budget, currentCombination, index + 1, (currentDay + 1) % 7);
	    }

	    public static void main(String[] args) {
	        // Define newspaper subscriptions
	        List<Newspaper> newspapers = new ArrayList<>();
	        newspapers.add(new Newspaper("TOI", new double[]{3, 3, 3, 3, 3, 5, 6}));
	        newspapers.add(new Newspaper("Hindu", new double[]{2.5, 2.5, 2.5, 2.5, 2.5, 4, 4}));
	        newspapers.add(new Newspaper("ET", new double[]{4, 4, 4, 4, 4, 4, 10}));
	        newspapers.add(new Newspaper("BM", new double[]{1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5}));
	        newspapers.add(new Newspaper("HT", new double[]{2, 2, 2, 2, 2, 4, 4}));

	        // Get user input for budget
	        double userBudget = 40;

	        // Find all possible combinations of subscriptions
	        List<List<String>> combinations = findCombinations(newspapers, userBudget);

	        // Display the output
	        for (List<String> combination : combinations) {
	            System.out.println(combination);
	        }
	    }
	}

