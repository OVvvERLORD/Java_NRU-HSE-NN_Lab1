public class Complex_nums{
    double Real, Imag;
    Complex_nums(){
        this.Real = 0;
        this.Imag = 0;
    }
    void setAttr(double  x, double y){
        this.Real = x;
        this.Imag = y;
    }
    void convert(){
        this.Real = - this.Real;
        this.Imag = - this.Imag;
    }
    void sum(Complex_nums num){ 
        this.Real += num.Real;
        this.Imag += num.Imag;
    }
    Complex_nums matr_sum(Complex_nums num){ 
        Complex_nums temp = new Complex_nums();
        temp.Real = this.Real + num.Real;
        temp.Imag = this.Imag + num.Imag;
        return temp;
    }
    Complex_nums matr_multi(Complex_nums num){
        double r = 0;
        double im = 0;
        r+= this.Real * num.Real;
        r+=(this.Imag*num.Imag) * (-1);
        im += this.Real * num.Imag;
        im+= this.Imag * num.Real;
        Complex_nums temp = new Complex_nums();
        temp.Real = r;
        temp.Imag = im;
        return temp;
    }
    Complex_nums multi(Complex_nums num){
        int r = 0;
        int im = 0;
        r+= this.Real * num.Real;
        r+=(this.Imag*num.Imag) * (-1);
        im += this.Real * num.Imag;
        im+= this.Imag * num.Real;
        this.Imag = im;
        this.Real = r;
        return this;
    }
    Complex_nums obr(){
        Complex_nums temp = new Complex_nums();
        temp.setAttr(this.Real, -1*this.Imag);
        return temp;
    }
    Complex_nums divide(Complex_nums num2){
        Complex_nums temp1 = new Complex_nums();
        Complex_nums temp2 = new Complex_nums();
        temp1 = this.matr_multi(num2.obr());
        temp2 = num2.matr_multi(num2.obr());
        temp1.Real = temp1.Real/temp2.Real;
        temp1.Imag = temp1.Imag/temp2.Real;
        return temp1;
    }
    void print(){
        if (this.Imag==0){
            System.out.println(this.Real);
            return;
        }
        if (this.Imag>0){
            System.out.println(this.Real+"+"+this.Imag+"i");
            return;
        }
        else{
            System.out.println(this.Real+""+this.Imag+"i");
            return;
        }
    }
}
