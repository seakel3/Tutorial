package binaryTree;
import java.lang.reflect.Array;
import java.util.*;

public class Permutations {
    private Permutations() {}
    
    public static <T> List<T[]> get(Class<T> itemClass, T ... itemsPool) {
        int itemsCount = 0;
        for(int i = 0; i < itemsPool.length; i++){
        	if((int)(itemsPool[i]) != 0){
        		itemsCount++;
        	}
        }
    	return get(itemsCount, itemClass, itemsPool);
    }

    public static <T> List<T[]> get(int size, Class<T> itemClass, T ... itemsPool) {
        int itemsPoolCount = 0;
    	if (size < 1) {
            return new ArrayList<T[]>();
        }
        for(int i = 0; i < itemsPool.length; i++){
        	if((int)(itemsPool[i]) != 0){
        		itemsPoolCount++;
        	}
        }
        System.out.println(itemsPoolCount);
        List<T[]> permutations = new ArrayList<T[]>();
        for (int i = 0; i < Math.pow(itemsPoolCount, size); i++) {
            T[] permutation = (T[]) Array.newInstance(itemClass, size);
            for (int j = 0; j < size; j++) {
                int itemPoolIndex = (int) Math.floor((double) (i % (int) Math.pow(itemsPoolCount, j + 1)) / (int) Math.pow(itemsPoolCount, j));
                permutation[j] = itemsPool[itemPoolIndex];
            }
            permutations.add(permutation);
        }

        return permutations;
    }
}
