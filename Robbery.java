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
	   //if there's only one thing left in the sizes array and it can fit in the bag, then add it
	   if(length==1 && sizes[0]<=capacity) {
	      return worths[0];
	   }
	   //else we leave the item
	   if(length==1 && sizes[0] > capacity)
	      return 0;
	   int weight= sizes[length]; //weight of item being considered to add to the bag
	   if(weight<=capacity) {
	      return max(maximizeRobWorthRecur(capacity, sizes, worths, length-1), maximizeRobWorthRecur((capacity - sizes[length]), sizes, worths, length -1 ) + worths[length]); 
	   }
	   else
	      return maximizeRobWorthRecur(capacity, sizes, worths, length-1);
	}

	public int maximizeRobWorthBottomUp(int capacity, int[] sizes, int[] worths) {
		// fill in here, change the return
	   int max=0;
      int [][] memoarr=new int[sizes.length+1][capacity+1]; //There is one extra row and col, because there is a row and col of zeros that helps set up boundaries
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
		//I added itemSizes.length-1 so I could consider adding the last item  into the bag, so this will decrement by one 
		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths, itemSizes.length-1); 
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
