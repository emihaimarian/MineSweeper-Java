import java.util.Random;

public class Game{
    
    private static final int MINE = -1;
    private int matrix[][] = new int [10][10];
    
    private Game(){
    }
    
    private static final class SingletonHolder{
        private static final Game SINGLETON = new Game();
    }
    
    public static Game getInstance(){
        return SingletonHolder.SINGLETON;
    }
    
    public void startGame(){
        init();
        generateMines(20);
        calculateEmptySpaces();
    }
    
    public int getCellValue(int i, int j){
        return matrix[i][j];
    }
    
    private void init(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = 0;
            }
        }
        
    }
    
    private void generateMines(int n){
        
        Random random = new Random();
        
        for(int i = 0; i < n; i++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            
            if (matrix[x][y] == MINE){
                i--;
            } else {
                matrix[x][y] = MINE;
            }
        }
    }
    
    private void calculateEmptySpaces(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] != MINE){
                    int numberOfMines = 0;
                    numberOfMines += isMine(i - 1, j - 1) ? 1 : 0;
                    numberOfMines += isMine(i, j - 1) ? 1 : 0;
                    numberOfMines += isMine(i + 1, j - 1) ? 1 : 0;
                    numberOfMines += isMine(i - 1, j) ? 1 : 0;
                    numberOfMines += isMine(i + 1, j) ? 1 : 0;
                    numberOfMines += isMine(i - 1, j + 1) ? 1 : 0;
                    numberOfMines += isMine(i, j + 1) ? 1 : 0;
                    numberOfMines += isMine(i + 1, j + 1) ? 1 : 0;
                
                    matrix[i][j] = numberOfMines;
                }
            }
        }
    }
    
    private boolean isMine(int i, int j){
        try{
            return matrix[i][j] == MINE;
        } catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
    }
}