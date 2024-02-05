import java.lang.Math;

public class GaussJordanElimination {
	public static void main(String args[]) {
        int x = 12;
        int y = 12;

		float[][] matrix = makeMatrix(x, y); 
        float[][] right = makeRight(y);
        float[][] newMatrix = combineMatrix(matrix, right, x);
        printMatrix(newMatrix, x);
        float[][] solved = solveMatrix(newMatrix, x);
        solved = reduce(solved, x, y);
        printMatrix(solved,x);
        solved = cancel(solved, x, y);
        printMatrix(solved,x);

	}

    public static float[][] makeMatrix(int x, int y){
        float[][] matrix = new float[x][y];
        for(int i = 0; i < x; i++){
            matrix[0][i] = 1;
        }

        matrix[1][0] = 1;
        matrix[1][1] = 1;

        matrix[2][2] = 1;
        matrix[2][3] = 1;

        matrix[3][4] = 1;
        matrix[3][5] = 1;

        matrix[4][6] = 1;
        matrix[4][7] = 1;

        matrix[5][8] = 1;
        matrix[5][9] = 1;

        matrix[6][0] = 1;
        matrix[6][11] = 1;

        matrix[7][2] = 1;
        matrix[7][9] = 1;

        matrix[8][5] = 4;
        matrix[8][6] = -3;

        matrix[9][3] = 3;
        matrix[9][4] = -2;

        matrix[10][3] = 1;
        matrix[10][8] = 1;
        matrix[10][9] = -1;

        matrix[11][0] = 1;
        matrix[11][1] = -1;
        matrix[11][2] = 1;
        matrix[11][3] = -1;
        matrix[11][4] = 1;
        matrix[11][5] = -1;
        matrix[11][6] = 1;
        matrix[11][7] = -1;
        matrix[11][8] = 1;
        matrix[11][9] = -1;
        matrix[11][10] = 1;
        matrix[11][11] = -1;

        return matrix;
    }

    public static float[][] makeRight(int y){
        float[][] right = new float[y][1];

        right[0][0] = 364;
        right[1][0] = 4;
        right[2][0] = 16;
        right[3][0] = 36;
        right[4][0] = 64;
        right[5][0] = 100;
        right[6][0] = 79;
        right[7][0] = 61;
        right[8][0] = 0;
        right[9][0] = 0;
        right[10][0] = 0;
        right[11][0] = -42;

        return right;
    }

    public static float[][] combineMatrix(float[][] matrix, float[][] right, int x){
        float[][] newMatrix = new float[x][x+1];
        
        for(int i = 0; i < x+1; i++){
            for(int j = 0; j < x; j++){
                if(i == x){
                    newMatrix[j][i] = right[j][0];
                }
                else{
                    newMatrix[j][i] = matrix[j][i];
                }
            }
        }

        return newMatrix;
    }

    public static void printMatrix(float[][] matrix, int x){
        for(int i = 0; i < x; i++){
            for(int j = 0; j <= x; j++){
                if (j == x){
                    System.out.println("  |  " + (int)matrix[i][j]);
                }
                else if((int)matrix[i][j] < -0.5){
                    System.out.print("  " + (int)matrix[i][j]);
                }
                else{
                    System.out.print("   " + (int)matrix[i][j]);
                }
            }
        }
        System.out.println("");
    }

    public static float[][] solveMatrix(float[][] matrix, int x){
        float temp;
        int pivotRow;

        for(int i = 0; i < x; i++){
            pivotRow = i;
            for(int j = (i+1); j < x; j++){
                if(Math.abs(matrix[j][i]) > Math.abs(matrix[pivotRow][i])){
                    matrix = swapRows(matrix, pivotRow, j, x);
                }
            }
            for(int j = (i+1); j < x; j++){
                if(matrix[i][i] == 0){
                    temp = 0;
                }
                else{
                    temp = matrix[j][i] / matrix[i][i];
                }
                for(int k = i; k <= x; k++){
                    matrix[j][k] -= (matrix[i][k] * temp);
                }
            }
        }
        return matrix;
    }

    public static float[][] swapRows(float[][] matrix, int i, int j, int x){
        float temp;
        for(int k = 0; k < (x+1); k++){
            temp = matrix[i][k];
            matrix[i][k] = matrix[j][k];
            matrix[j][k] = temp;
        }
        return matrix;
    }

    public static float[][] reduce(float[][] matrix, int x, int y){
        int count;
        float firstNum;
        for(int i = 0; i < y; i++){
            count = 0;
            while(matrix[i][count] > -0.1 && matrix[i][count] < 0.1){
                count++;
            }
           firstNum = matrix[i][count];
           while(count <= x){
                if(matrix[i][count] != 0){
                    matrix[i][count] = matrix[i][count] * 1/firstNum;
                }
                count++;
            }
            
        }
        return matrix;
    }

    public static float[][] cancel(float[][] matrix, int x, int y){
        int count; 
        float temp;
        for(int i = 0; i < y; i++){
            count = 0;
            while(matrix[i][count] > -0.1 && matrix[i][count] < 0.1){
                count++;
            }
           count++;
           while(count < x){
                if(matrix[i][count] != 0){
                    temp = matrix[i][count];
                    for(int j = count; j <= x; j++){
                        matrix[i][j] = matrix[i][j] - (matrix[count][j]*temp);
                    }
                }
                count++;
            }
          
        }
        return matrix;
    }
}