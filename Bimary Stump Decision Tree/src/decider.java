import java.util.ArrayList;
import java.util.Random;

public class decider {

    int[][][] repo;
    public decider(){
    repo=new int[21][18][3];
    }





    stump calc(ArrayList<ulti> all,double[] weight,allinfo demi)
    {
        //for(int i=0;i<weight.length;i++)
            //System.out.println(weight[i]);
        ArrayList<ulti> resample=new ArrayList<>();

        for(int i=1;i<=all.size();i++)
        {
            Random r = new Random();
            double rangeMin=0;double rangeMax=1;
            double ran = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            //double ran=Math.random();
            double temp=0;
            for(int j=0;j<all.size();j++)
            {
                double pre=temp;
                temp+=weight[j];
                if(ran>=pre && ran<temp)
                {
                    //System.out.println("j "+j);
                    resample.add(all.get(j));
                    break;
                }

            }
        }

        for(int i=0;i<resample.size();i++)
        {

            //System.out.println("hello");
            for(int j=1;j<=20;j++)
            {
                //if(i==18)
                    //System.out.println(resample.get(i).attributes[j]);
                repo[j][resample.get(i).attributes[j]][resample.get(i).ans]++;
            }
        }

        /*for(int i=1;i<=20;i++)
        {
            for(int j=1;j<=15;j++)
            {
                System.out.print(repo[i][j][1]+" ");
            }
            System.out.println();
            for(int j=1;j<=15;j++)
            {
                System.out.print(repo[i][j][2]+" ");
            }
            System.out.println();
            System.out.println();
        }*/

        double ll=100000000;
        int choosen=1;
        for(int i=1;i<=20;i++)
        {
            double temp=func(repo[i],demi.branches[i]);
            //System.out.println(i+" "+temp);
            //System.out.println(temp);
            if(temp<ll)
            {
                choosen=i;
                ll=temp;
            }

        }

         stump p=new stump(choosen);
        //System.out.println("chosen "+choosen);

         for(int i=1;i<=demi.branches[choosen];i++)
         {
             int x,y;
             x=repo[choosen][i][1];
             y=repo[choosen][i][2];

             if(x>=y)
                 p.mymap.put(i,1);
             else
                 p.mymap.put(i,2);
         }

         return p;



    }
    public static double log2(double num)
    {

        if(num==0)
            return Double.MIN_VALUE/2;
        return (Math.log(num)/Math.log(2));
    }


    public double func(int[][] repo,int branches)
    {
        double abs=0;
        for(int i=1;i<=branches;i++)
            abs+=repo[i][1]+repo[i][2];
        double tet=0;
        for(int i=1;i<=branches;i++)
        {
            double tot=repo[i][1]+repo[i][2];
            double x=repo[i][1]; double y=repo[i][2];
            double xx=x/tot;
            double yy=y/tot;
            //System.out.println(tot+" "+x+" "+y+" "+branches);

            double emo=xx*log2(xx)+yy*log2(yy);

            emo=emo*(-1);
            tet+=(tot/abs)*emo;

        }
        return tet;

    }

}
