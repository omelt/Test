package com.company.n_quene;

public class Plus {
    static int n=17,upperlim=(1<<n)-1,Ans=0;
    static void test(int row, int ld, int rd)
    {
        int pos, p;
        if ( row != upperlim )
        {
            pos = upperlim & (~(row | ld | rd ));
            while ( pos !=0)
            {
                p = pos & (~pos + 1);
                pos = pos - p;
                test(row | p, (ld | p) << 1, (rd | p) >> 1);
            }
        }
        else
            ++Ans;
    }

    public static void main(String[] args) {
        long before=System.currentTimeMillis();
        test(0,0,0);
        long after=System.currentTimeMillis();
        System.out.println(Ans+" "+(after-before));
    }
}
