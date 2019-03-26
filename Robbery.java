// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.

// You are encouraged to make helper functions!

public class Robbery {
   
   //Finds the max value
   public int max(int x, int y) {
      if(x>=y) {
         return x;
      }
      else
         return y;
   }

	// Using DP: Get the maximum value with capacity C and n items
	public int maximizeRobWorthRecur(int capacity, int[] sizes, int[] worths, int length) {
		// fill in here, change the return
	   if(length==1 && sizes[0]<=capacity) {
	      return worths[0];
	   }
	   int weight=sizes[sizes.length-1];
	   if(weight<=capacity) {
	      return worths[sizes.length-1] + maximizeRobWorthRecur((capacity-weight), sizes, worths, length-1); 
	   }
	   else
	      return maximizeRobWorthRecur(capacity, sizes, worths, length-1);
	}

	public int maximizeRobWorthBottomUp(int capacity, int[] sizes, int[] worths) {
		// fill in here, change the return
	   int max=0;
      int [][] memoarr=new int[sizes.length+1][capacity+1];
      for(int i=1; i<=sizes.length; i++) {
        for(int j=1; j<=capacity; j++) {
          if(sizes[i-1]<= j) {
            memoarr[i][j]= max(memoarr[i-1][j], memoarr[i-1][j-(sizes[i-1])] + worths[i-1]);
           }
           else
               memoarr[i][j]= memoarr[i-1][j];
        }
      }
      max= memoarr[sizes.length][capacity];
		return max;
	}

/**
* Bonus: figure out which items exactly
* Takes in a DP DPTable
* Returns an int array of the individual worths of the items you took
*/
	public int[] takeRobInventory(int[][] DPTable) {
		// fill in here, change the return
		return new int[DPTable.length];
	}

	public static void main(String[] args) {
		Robbery r = new Robbery();
		int bagCapacity = 40;
		int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};
		int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};

		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths, itemSizes.length);
		System.out.println("Max worth of the bag: " + maxWorthRecur);
		int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
		System.out.println("Max worth of the bag: " + maxWorthBottomUp);

		// Bonus: Fill in the helper method takeRobInventory that could help you
		//figure out which items go into the bag that make it max worth. Feel free
		//to change up the parameters and returned types + values of the helper
		// methods above.
		// int[] itemsPicked = r.takeRobInventory();
		// System.out.println("The items we take are worth:")
		// for (int i = 0; i < results.length; i++) {
		// 	System.out.print(results[i] + " ");
	}
}
