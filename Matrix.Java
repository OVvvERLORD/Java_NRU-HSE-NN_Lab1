public class Matrix{
    int size_row;
    int size_col;
    Complex_nums multipl;
    Complex_nums [][] matrix;
    Matrix(){
        matrix = new Complex_nums[10][10];
        this.setAttr(10, 10);
        this.multipl = new Complex_nums();
        this.multipl.setAttr(1, 0);
        for (int i = 0; i< this.size_row; i++){
            for (int j = 0; j<this.size_col;j++){
                this.matrix[i][j] = new Complex_nums();
                this.matrix[i][j].setAttr(0,0);
            }
        }
    }
    Matrix convert(){
        for (int i = 0; i< this.size_row;i++){
            for (int j = 0 ; j<this.size_col;j++){
                this.matrix[i][j].convert();
            }
        }
        return this;
    }
    void sum(Matrix matrix_b){
        if (matrix_b.size_row<=this.size_row && matrix_b.size_col<=this.size_col){
            for (int i = 0; i<  matrix_b.size_row;i++){
                for (int j = 0 ; j<matrix_b.size_col;j++){
                    this.matrix[i][j].sum(matrix_b.matrix[i][j]);
                }
            }
        }
        else{
            return;
        }
    }
    void sub(Matrix matrix_b){
            this.sum(matrix_b.convert()); 
    }
    void setAttr(int x,int y){
        this.size_row = x;
        this.size_col = y;
    }
    Matrix multi(Matrix matrix_b){ 
        if (this.size_col == matrix_b.size_row){
            Complex_nums [][] temp_matrix = new Complex_nums[this.size_row][matrix_b.size_col];
            for (int i = 0; i<this.size_row;i++){
                for (int j =0;j<matrix_b.size_col;j++){
                    temp_matrix[i][j] = new Complex_nums();
                    temp_matrix[i][j].Real = 0;
                    temp_matrix[i][j].Imag = 0;
                }
            }
            Complex_nums temp = new Complex_nums();
            Complex_nums cell = new Complex_nums();
            for (int i = 0; i<this.size_row;i++){
                for (int j = 0; j<matrix_b.size_col;j++){
                    for (int r = 0;r < this.size_col;r++){
                        final Complex_nums var = this.matrix[i][r];
                        final Complex_nums var2 = matrix_b.matrix[r][j];
                        temp = var.matr_multi(var2);
                        cell = cell.matr_sum(temp);
                    }
                temp_matrix[i][j].setAttr(cell.Real, cell.Imag);
                cell.setAttr(0, 0);
                }
            }
            this.matrix = temp_matrix;
            this.size_col = matrix_b.size_col;
            this.multipl = this.multipl.matr_multi(matrix_b.multipl);
            return this;
        }
        else{
            System.out.println("Invalid matrix. Returning original matrix");
            return this;
        }
    }
    void transpose(){
        Complex_nums [][] temp_matrix = new Complex_nums[this.size_col][this.size_row];
        for (int i = 0; i<this.size_row;i++){
            for (int j = 0; j<this.size_col;j++){
                temp_matrix[j][i]=this.matrix[i][j];
            }
        }
        int temp = this.size_col;
        this.size_col = this.size_row;
        this.size_row = temp;
        this.matrix = temp_matrix;
    }
    private Matrix getLeftMatr(int start_row, int start_col){

        Matrix left_matrix = new Matrix();
        left_matrix.setAttr(this.size_row-1, this.size_col-1);
        int temp = -1;
        for (int i = 0; i<this.size_row;i++){
            if (i==start_row){
                continue;
            }
            else{
                temp++;
                int temp2 = 0;
                for (int j = 0; j<this.size_row;j++){
                    if (j==start_col){
                        continue;
                    }
                    else{
                        left_matrix.matrix[temp][temp2] = this.matrix[i][j];
                        temp2++;
                    }
                }
            }
        }
        return left_matrix;
    }
    Complex_nums det(){
        if (this.size_col == this.size_row){
            if (this.size_row ==1){
                return this.matrix[0][0];
            }
            if (this.size_row==2){
                Complex_nums temp1 = new Complex_nums();
                Complex_nums temp2 = new Complex_nums();
                temp1 = this.matrix[0][0].matr_multi(this.matrix[1][1]);
                temp2 = this.matrix[0][1].matr_multi(this.matrix[1][0]);
                temp2.convert();
                temp1.sum(temp2);
                return temp1;
            }
            Complex_nums temp = new Complex_nums();
            for (int i = 0; i<this.size_row;i++){
                Matrix temp_Matrix = new Matrix();
                temp_Matrix = this.getLeftMatr(0, i);
                int val = (int) Math.pow(-1,i);
                Complex_nums num_for_multi = new Complex_nums();
                num_for_multi = this.matrix[0][i].matr_multi(temp_Matrix.det());
                if (val ==-1){
                    num_for_multi.convert();
                }
                temp.sum(num_for_multi);
            }
            return temp;
        }
        else{
            System.err.println("Can't get the det from your matrix");
            Complex_nums zero = new Complex_nums();
            return zero;
        }
    }
    private Matrix alg_dop(){
        Matrix temp_matr = new Matrix();
        for (int i =0; i<this.size_row;i++){
            for (int j = 0; j < this.size_row;j++){
                Matrix temp = this.getLeftMatr(i, j);
                Complex_nums det_of_temp = temp.det();
                double check = Math.pow(-1, i+j);
                temp_matr.matrix[i][j]=det_of_temp;
                if (check == -1){
                    temp_matr.matrix[i][j].convert();
                }
            }
        }
        temp_matr.setAttr(this.size_row, this.size_row);
        return temp_matr;
    }
    Matrix inverse(){
        if (this.size_col == this.size_row){
            Complex_nums det = this.det();
            if (det.Real==0 && det.Imag==0){
                System.err.println("Matrix has zero-det, can't found an inversed one");
                return this;
            }
            this.transpose(); 
            Matrix dops = this.alg_dop();
            Complex_nums one = new Complex_nums();
            Complex_nums multipl = new Complex_nums();
            one.setAttr(1, 0);
            multipl = one.divide(det);
            for (int i = 0; i < this.size_row; i++) {
                for (int j = 0; j < this.size_row; j++) {
                    Complex_nums temp = dops.matrix[i][j];
                    temp = dops.matrix[i][j].matr_multi(multipl);
                    dops.matrix[i][j] = temp;
                }
                
            }
            this.transpose();
            return dops;
        }
        else{
            System.out.println("Can't find an inversed one");
            return this;
        }
    }
    Matrix division(Matrix matrix_b){
        Matrix temp = matrix_b.inverse();
        this.multi(temp);
        return this;
    }
    void print(){
        String [][] temp_matrix = new String [this.size_row][this.size_col];
        for (int i = 0; i< this.size_row;i++){
            for (int j = 0 ; j<this.size_col;j++){
                if (this.matrix[i][j].Imag==0){
                    temp_matrix[i][j]= ""+this.matrix[i][j].Real;
                    continue;
                }
                if (this.matrix[i][j].Imag>0){
                    temp_matrix[i][j]= ""+this.matrix[i][j].Real+"+"+this.matrix[i][j].Imag+"i";
                    continue;
                }
                else{
                    temp_matrix[i][j]= ""+this.matrix[i][j].Real+this.matrix[i][j].Imag+"i";
                    continue;
                }
            }
        }
        System.out.println(Arrays.deepToString(temp_matrix).replace("], ", "]\n"));
    }
}