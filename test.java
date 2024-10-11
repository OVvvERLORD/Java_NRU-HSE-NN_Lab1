public class test{
    public static void main(String[] args){
        // tests for complex numbrers: setting attributes, different operations and printing
        System.out.println("Complex numbers testings");
        Complex_nums p1 = new Complex_nums();
        p1.setAttr(2, -3);
        Complex_nums p2 = new Complex_nums();
        p2.setAttr(4, 5);
        p1.sum(p2);
        
        p1.print();
        System.err.println("\n");
        p1.multi(p2);
        p1.print();
        System.out.println("Matrix testings");
        Matrix p3 = new Matrix();
        p3.setAttr(10, 10);
        for (int i = 0; i< p3.size_row; i++){
            for (int j = 0; j<p3.size_col;j++){
                p3.matrix[i][j].Imag -= 3*j;
                p3.matrix[i][j].Real += i;
            }
        }
        Matrix p4 = new Matrix();
        p4.setAttr(7, 7);
        for (int i = 0; i< p4.size_row; i++){
            for (int j = 0; j<p4.size_col;j++){
                p4.matrix[i][j].Imag+=i+j;
                p4.matrix[i][j].Real-=30*i-28*j;
            }
        }
        Complex_nums check1, check2;
        check1 = p3.matrix[4][6];
        check2=p4.matrix[4][6];
        p3.sum(p4);
        Complex_nums check3 = p3.matrix[4][6];
        p3.matrix[9][9].setAttr(20, 30);
        p4.setAttr(3, 3);
        p3.setAttr(3, 2);
        // p3.matrix[9][9].print();
        // p3.print();
        System.err.println("transpose, etc");
        p3.transpose();
        p3.print();

        System.out.println("\n");
        p4.print();
        p3.multi(p4);
        System.err.println("\n");
        p3.print();

        Matrix p5 = new Matrix();
        p5.setAttr(3, 4);
        for (int i = 0; i<p5.size_row;i++){
            for (int j = 0; j<p5.size_col;j++){
                p5.matrix[i][j].Imag = 0;
                p5.matrix[i][j].Real +=i+j;
            }
        }
        Matrix p6 = new Matrix();
        p6.setAttr(3, 4);
        for (int i = 0; i<p6.size_row;i++){
            for (int j = 0; j<p6.size_col;j++){
                p6.matrix[i][j].Imag = 0;
                p6.matrix[i][j].Real +=i+j+2;
            }
        }
        p5.print();
        System.err.println("\n");
        p6.transpose();
        p6.print();
        p6.transpose();
        p5.multi(p6);
        p6.transpose();
        Matrix test = p5.multi(p6);
        test.print();
        System.out.println("Part2: Det & ^-1");
        Matrix test_Matrix = new Matrix();
        test_Matrix= p5;
        test_Matrix.setAttr(2, 2);
        Complex_nums det_test = new Complex_nums();
        System.out.println("Det_tests");
        Matrix matr_for_det = new Matrix();
        matr_for_det.setAttr(2, 2);
        for (int i = 0; i<2;i++){
            for (int j = 0; j<2;j++){
                matr_for_det.matrix[i][j].setAttr(i*42, j*(-7));
            }
        }

        det_test = test_Matrix.det();
        test_Matrix.print();
        det_test.print();
        System.out.println("\n");
        matr_for_det.print();
        det_test = matr_for_det.det();
        det_test.print();
        matr_for_det.setAttr(3, 3);
        for (int i = 0; i<3;i++){
            for (int j = 0; j<3;j++){
                matr_for_det.matrix[i][j].setAttr(i*42, j*(-7));
            }
        }
        System.out.println("\n");
        test.matrix[0][2].setAttr(228, 0);
        test.print();
        det_test =test.det();
        det_test.print();
        System.out.println("Inverse testings");
        Matrix test_for_inverse = new Matrix();
        test_for_inverse = test.inverse();
        test_for_inverse.print();
        test.setAttr(2, 2);

        System.out.println("\n");
        test.matrix[1][1].setAttr(12, 20);
        test.matrix[0][0].setAttr(1,3);
        test.matrix[0][1].setAttr(10,1);
        test.matrix[1][0].setAttr(0, 0);
        test.print();
        det_test =test.det();
        det_test.print();
        System.out.println("if we set 2x3");
        test.setAttr(2, 3);
        test.det();

    }
}