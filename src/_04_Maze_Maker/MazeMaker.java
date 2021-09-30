package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

    private static int rows;
    private static int cols;

    private static Maze maze;

    private static Random randGen = new Random();
    private static Stack<Cell> uncheckedCells = new Stack<Cell>();

    public static Maze generateMaze(int r, int c) {
        rows = r;
        cols = c;
        maze = new Maze(rows, cols);

        // 1. Pick a random cell along the border and remove its exterior wall.
        //    This will be the starting point. Then select a random cell along
        //    the opposite wall and remove its exterior wall. This will be the
        //    finish line.
//        int row = (int) (Math.random () * maze.getRows());
//        int col = (int) (Math.random () * maze.getCols());
        int row;
        int col;
        int rowEnd;
        int colEnd;
      //  int side = (int)(Math.random () * 2);
       // if (true) {
        	row = (int)(Math.random () * maze.getRows());
        	col = 0;
        	maze.getCell(row, col).setWestWall(false);
        	colEnd = maze.getCols()-1;
        	rowEnd = (int)(Math.random () * maze.getRows());
        	maze.getCell(rowEnd, colEnd).setEastWall(false);
       // }
//        }else if (side == 1){
//        	row = (int)(Math.random () * maze.getRows());
//        	col = maze.getCols()-1;
//        	maze.getCell(row, col).setEastWall(false);
//        	colEnd = 0;
//        	rowEnd = (int)(Math.random () * maze.getRows());
//        	maze.getCell(rowEnd, colEnd).setWestWall(false);
//        }else if (side == 1) {
//        	row = 0;
//        	col = (int)(Math.random() * maze.getCols());
//        	maze.getCell(row, col).setNorthWall(false);
//        	colEnd = (int)(Math.random () * maze.getCols());
//        	rowEnd = maze.getRows()-1;
//        	maze.getCell(rowEnd, colEnd).setSouthWall(false);
////        }else if (side == 3) {
////        	row = maze.getRows()-1;
////        	col = (int)(Math.random() * maze.getCols());
////        	maze.getCell(row, col).setSouthWall(false);
////        	colEnd = (int)(Math.random () * maze.getCols());
////        	rowEnd = 0;
////        	maze.getCell(rowEnd, colEnd).setNorthWall(false);
//        }
        
        // 2. select a random cell in the maze to start 
       selectNextPath( maze.getCell((int) (Math.random() * maze.getRows()), (int) (Math.random() * maze.getCols())));
        // 3. call the selectNextPath method with the randomly selected cell

        return maze;
    }

    // 4. Complete the selectNextPathMethod
    private static void selectNextPath(Cell currentCell) {
        // A. SET currentCell as visited
		currentCell.setBeenVisited(true);
        // B. check for unvisited neighbors using the cell
		ArrayList <Cell> unvisited = getUnvisitedNeighbors (currentCell);
//		if (currentCell.getRow() != maze.getRows()-1 && !maze.getCell(currentCell.getRow()+1, currentCell.getCol()).hasBeenVisited()) {
//			unvisited.add(maze.getCell(currentCell.getRow()+1, currentCell.getCol()));
//		}
//		if (currentCell.getRow() != 0 && !maze.getCell(currentCell.getRow()-1, currentCell.getCol()).hasBeenVisited()) {
//			unvisited.add(maze.getCell(currentCell.getRow()-1, currentCell.getCol()));
//		}
//		if (currentCell.getCol() != maze.getCols()-1 && !maze.getCell(currentCell.getRow(), currentCell.getCol()+1).hasBeenVisited()) {
//			unvisited.add(maze.getCell(currentCell.getRow(), currentCell.getCol()+1));
//		}
//		if (currentCell.getCol() != 0 && !maze.getCell(currentCell.getRow(), currentCell.getCol()-1).hasBeenVisited()) {
//			unvisited.add(maze.getCell(currentCell.getRow()+1, currentCell.getCol()-1));
//		}
        // C. if has unvisited neighbors,
		if (unvisited.size() > 0) {
	        // C1. select one at random.
			int rand = (int)(Math.random() * unvisited.size());
	        // C2. push it to the stack
			uncheckedCells.push (unvisited.get(rand));
	        // C3. remove the wall between the two cells
			removeWalls (unvisited.get(rand), currentCell);
	        // C4. make the new cell the current cell and SET it as visited
			currentCell = unvisited.get(rand);
			currentCell.setBeenVisited (true);
	        // C5. call the selectNextPath method with the current cell
			selectNextPath (currentCell);
		}
        // D. if all neighbors are visited
		else {
        // D1. if the stack is not empty
			if (uncheckedCells.size()>0) {
        // D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
        // D1b. make that the current cell
				selectNextPath (currentCell);
        // D1c. call the selectNextPath method with the current cell
			}
		}
    }

    // This method will check if c1 and c2 are adjacent.
    // If they are, the walls between them are removed.
    private static void removeWalls(Cell c1, Cell c2) {
        if (c1.getRow() == c2.getRow()) {
            if (c1.getCol() > c2.getCol()) {
                c1.setNorthWall(false);
                c2.setSouthWall(false);
            } else {
                c2.setNorthWall(false);
                c1.setSouthWall(false);
            }
        } else {
            if (c1.getRow() > c2.getRow()) {
                c1.setWestWall(false);
                c2.setEastWall(false);
            } else {
                c2.setWestWall(false);
                c1.setEastWall(false);
            }
        }
    }

    // This method returns a list of all the neighbors around the specified
    // cell that have not been visited. There are up to 4 neighbors per cell.
    //          1
    //       2 cell 3
    //          4
    private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        int row = c.getRow();
        int col = c.getCol();

        ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

        if (row > 0 && !maze.getCell(row - 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row - 1, col));
        }

        if (col > 0 && !maze.getCell(row, col - 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col - 1));
        }

        if (row < rows - 1 && !maze.getCell(row + 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row + 1, col));
        }

        if (col < cols - 1 && !maze.getCell(row, col + 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col + 1));
        }

        return unvisitedNeighbors;
    }
}
