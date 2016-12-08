package matrix;

public class Matrix {

	static double[][] matrix;
	static int rows;
	static int columns;
	public double fill;
	
	// copied and pasted from http://stackoverflow.com/questions/16381486/a-matrix-class-in-java because i'm to fucked up to tell if i'm right and this is an important step
	public Matrix(double[][] array)
	{	
		this.rows = array.length;
		this.columns = array[0].length;
		this.matrix = new double[this.rows][this.columns];
	    for (int i=0; i < array.length;i++)
	        for (int j=0; j < array[i].length;j++)
	            this.matrix[i][j] = array[i][j];
	}
	
	public Matrix(int r, int c){
		rows = r;
		columns  = c;
		
		for(int x = 0; x<rows; x++){
			for(int y = 0; y<columns; y++){
				matrix[x][y] = 0;
			}
		}
	}
	
	public Matrix(int r, int c, double f){
		this.rows = r;
		this.columns  = c;
		this.fill = f;
		this.matrix = new double[r][c];
		
		for(int x = 0; x<rows; x++){
			for(int y = 0; y<columns; y++){
				matrix[x][y] = fill;
			}
		}
	}

	public Matrix add(Matrix x, Matrix y){
		if((x.rows == y.rows) && (x.columns == y.columns)){
			double[][] result = new double[x.rows][x.columns];
			for( int z = 0; z < x.rows; z++){
				for(int w = 0; w < x.columns; w++){
					result[z][w] = x.matrix[z][w] + y.matrix[z][w];
				}
			}
			Matrix results = new Matrix(result);
			return results;
		}
		else{
		return null;
		}
	}
	
	public static Matrix subtract(Matrix x, Matrix y){
		if((x.rows == y.rows) && (x.columns == y.columns)){
			double[][] result = new double[x.rows][x.columns];
			for( int z = 0; z < x.rows; z++){
				for(int w = 0; w < x.columns; w++){
					result[z][w] = x.matrix[z][w] - y.matrix[z][w];
				}
			}
			Matrix results = new Matrix(result);
			return results;
		}
		else{
		return null;
		}
	}
	
	public static Matrix multiply(Matrix x, Matrix y) throws Exception{
		if(x.columns == y.rows){
			double c = 0;
			double[][] result = new double[x.rows][y.columns];
			
			for(int rc = 0; rc < y.columns; rc++){
				for( int yc = 0; yc < y.columns; yc++){
					for(int rr = 0; rr < x.rows; rr++){
						for(int xr = 0; xr < x.rows; xr++){
							for( int xc = 0; xc < x.columns; xc++){
								c =+ x.matrix[xr][xc]*y.matrix[xc][yc];
							}
						}	
						result[rr][rc] = c;
					}
				}
			}
			Matrix results = new Matrix(result);
			return results;
		}
		else{
		throw new Exception();
		}
	}
	
	
	
	
	//Method partially written by tehgao https://github.com/tehgao/IB-Math-IA-Year-1/blob/master/src/RREF.java
	public static Matrix toRREF(double[][] M) {
		//double[][] M = x.matrix();
        int rowCount = M.length;
        if (rowCount == 0){
        	System.out.println("Invalid Entry");
            return null;
        }
        int columnCount = M[0].length;

        int lead = 0;
        for (int r = 0; r < rowCount; r++) {
            if (lead >= columnCount)
                break;
            {
                int i = r;
                while (M[i][lead] == 0) {
                    i++;
                    if (i == rowCount) {
                        i = r;
                        lead++;
                        if (lead == columnCount){
                        	System.out.println("Invalid Entry");
                            return null;
                        }
                    }
                }
                double[] temp = M[r];
                M[r] = M[i];
                M[i] = temp;
            }

            {
                double lv = M[r][lead];
                for (int j = 0; j < columnCount; j++)
                    M[r][j] /= lv;
            }

            for (int i = 0; i < rowCount; i++) {
                if (i != r) {
                    double lv = M[i][lead];
                    for (int j = 0; j < columnCount; j++)
                        M[i][j] -= lv * M[r][j];
                }
            }
            lead++;
        }
        for(int x = 0; x < columnCount; x++){
        	for(int y = 0; y< rowCount; y++){
        		if(M[y][x] == -0.0){
        			M[y][x] = 0;
        		}
        	}
        }
        Matrix ret = new Matrix(M);
        return ret;
    }
	
	private double[][] matrix() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void print(){
		for(int z = 0; z < rows; z++){
			for(int y = 0; y < columns; y++){
				System.out.print(" "+matrix[z][y]+" ");
			}
			System.out.println();
		}
	}
	
	public static double[][] toArray(){
		return matrix;
	}
	
	public static void main(String[] args) throws Exception {
//		double[][] sq = new double[2][3];
//		for(int z = 0; z < 2; z++){
//			for(int w = 0; w<3; w++){
//				sq[z][w] = (double)(Math.random()*10);
//			}
//		}
		double[][] da = {{1,2,3,9},{2, -1,1,8},{3,0,-1,3}};
		double[][] d = {{1,2,3},{2, -1,1},{3,0,-1}};
		Matrix x = new Matrix(2, 3, 2);
		Matrix y = new Matrix(3, 2, (int)((Math.random()*100)/1));
		x.toRREF(d).print();
	}
}

