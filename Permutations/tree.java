package binaryTree;
import java.util.*;

public class Tree {

	   @SuppressWarnings("rawtypes")
	public static void print (Node list) { 
	        if (list == null) return;  
	        System.out.print (list.data + " ");
	        print(list.next);
	   }
	public static int insert (Node<Integer> a, Node<Integer> root) {
		if(root.data <= a.data){
			if(root.right == null){
				root.right = a;
				return 0;
			}
		return(insert(a, root.right));
		}
		if(root.data > a.data){
			if(root.left == null){
				root.left = a;
				return 0;
			}
		return(insert(a, root.left));
		}
		return -1;
	}
	public static int search (int a, Node<Integer> root){
		if(root == null) return 0;
		if(a == root.data) return 1;
		if(a < root.data) return 1 + search(a, root.left);
		if(a > root.data) return 1 + search(a, root.right);
		return -1;
	}
	public static void quick(int[] myArray, int arraySize){
		List<Integer[]> perms = Permutations.get(arraySize, Integer.class, myArray[0], myArray[1], myArray[2], myArray[3], myArray[4], myArray[5], myArray[6], myArray[7]);
		Random randomGenerator = new Random();
		double runningAverage = 0;
		double steps = 0;
		for(int i = 0; i < perms.size(); i++){
			Integer[] theArray = perms.get(i);
			if(!(DuplicateInArray(theArray))){
				Node<Integer> root = new Node<>(theArray[0]);
				for(int j = 0; j < theArray.length; j++){
					Node<Integer> node = new Node<>(theArray[j]);
					insert(node, root);
				}
				int index = randomGenerator.nextInt(theArray.length);
				runningAverage += search(theArray[index], root);
				steps++;
			}
			System.out.println(i + "/" + (perms.size() - 1));
		}
		System.out.println("The average steps required to find a random value in the tree was " + runningAverage/steps);
	}
	 public static boolean DuplicateInArray(Integer[] theArray){
	        Set<Integer> set = new HashSet<Integer>();
	         
	        for(int i = 0; i < theArray.length ; i++){
	            if(set.add(theArray[i]) == false){
	                return true;
	            }
	        }
	        return false;
	    }
	public static void full(int[] myArray, int arraySize){
		List<Integer[]> perms = Permutations.get(arraySize, Integer.class, myArray[0], myArray[1], myArray[2], myArray[3], myArray[4], myArray[5], myArray[6], myArray[7]);
		double otherAverage = 0;
		List<Integer[]> bestArray = new ArrayList<Integer[]>();
		double bestTime = 500;
		List<Integer[]> worstArray = new ArrayList<Integer[]>();
		double worstTime = 0;
		int steps = 0;
		for(int i = 0; i < perms.size(); i++){
			Integer[] theArray = perms.get(i);
			if(!DuplicateInArray(theArray)){
					Node<Integer> root = new Node<>(theArray[0]);
					for(int j = 1; j < theArray.length; j++){
						Node<Integer> node = new Node<>(theArray[j]);
						insert(node, root);
					}
					double averageArray = 0;
					for(int j = 0; j < theArray.length; j++){
						averageArray += search(theArray[j], root);
						otherAverage += search(theArray[j], root);
					}
					if(averageArray < bestTime){
						bestArray.clear();
						bestTime = averageArray;
					}
					if(averageArray == bestTime) bestArray.add(theArray);
					if(averageArray > worstTime){
						worstArray.clear();
						worstTime = averageArray;
					}
					if(averageArray == worstTime) worstArray.add(theArray);
					steps++;
			}
			System.out.println(i + "/" + (perms.size() - 1));
		}	
		System.out.println("The average steps required to find a value in the tree was " + otherAverage/(steps*arraySize));
		System.out.println("Some of the fastest arrays were ");
		for(int i = 0; i < bestArray.size(); i++){
			System.out.println(Arrays.toString(bestArray.get(i)));
		}
		System.out.println("Some of the slowest arrays were ");
		for(int i = 0; i < worstArray.size(); i++){
			System.out.println(Arrays.toString(worstArray.get(i)));
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean retry = true;
		int arraySize = 0;
		while(retry){
			System.out.println("How many numbers would you like to put in the tree?");
			arraySize = input.nextInt();
			if(arraySize > 8) System.out.println("That tree will take too long to calculate. Pick a lower number.");
			if(arraySize == 8){
				System.out.println("This will take around 40 seconds. Do you want to pick a lower number? (y/n)");
				String answer = "y";
				answer = input.next();
				if(answer.equalsIgnoreCase("n")) retry = false;
			}
			if(arraySize < 8) retry = false;
		}
		int[] myArray = {0, 0, 0, 0, 0, 0, 0, 0};
		for(int i = 0; i < arraySize; i++){
			if(i == 0){
				System.out.println("What is the first number?");
				int value = input.nextInt();
				myArray[i] = value;
			}
			if(i != 0){
			System.out.println("What is the next number?");
			int value = input.nextInt();
			myArray[i] = value;
			}
		}
		String speed = "";
		System.out.println("What speed would you like? Quick if faster than full, but quick only picks one number to search each tree.");
		System.out.println("Therefore, quick is faster, but less precise. Choose full/quick");
		speed = input.next();
		if(speed.equalsIgnoreCase("full")) full(myArray, arraySize);
		if(speed.equalsIgnoreCase("quick")) quick(myArray, arraySize);
		input.close();
	}
}
