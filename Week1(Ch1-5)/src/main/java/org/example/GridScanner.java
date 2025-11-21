
package org.example;

public class GridScanner {
    public static void main(String[] args) {

        char[][] grid = {
                {'.', '.', '#', '.'},
                {'a', '#', 'b', '.'},
                {'.', '7', '.', '.'}
        };

        boolean found = false;
        outerloop:
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '#') {
                    continue;
                }
                if (Character.isDigit(grid[row][col] )){
                    System.out.println("Found a digit at (" + row + ", " + col + "): " + grid[row][col]);
                    break outerloop;
                }

                if(!found){
                    System.out.println("None");
                }
            }
        }

    }
}
