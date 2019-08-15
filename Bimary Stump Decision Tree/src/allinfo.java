import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class allinfo {

    public int[] minin,maxin;
    public double[] mind,maxd;
    public int[] intdivider;
    public double[] doubledivider;
    ArrayList<Set<String> > val;
    ArrayList<ArrayList<String> > to;
    public int[] branches;
    ArrayList<ArrayList<Integer> > allint;
    ArrayList<ArrayList<Double> >  alldouble;

    public allinfo()
    {
        minin=new int[6];
        maxin=new int[6];
        mind=new double[6];
        maxd=new double[6];
        branches=new int[22];
        intdivider=new int[6];
        doubledivider=new double[6];
        val=new ArrayList<>();
        to=new ArrayList<>();

        alldouble=new ArrayList<>();
        allint=new ArrayList<>();

        for(int i=0;i<=5;i++)
            alldouble.add(new ArrayList<Double>());

        for(int i=0;i<=5;i++)
            allint.add(new ArrayList<Integer>());

        for (int i=1;i<=11;i++)
            val.add(new HashSet<String>());
        for (int i=1;i<=11;i++)
            to.add(new ArrayList<String>());
    }
    public void init(pureinfo temp)
    {



        minin[1]=temp.age;
        minin[2]=temp.duration;
        minin[3]=temp.camp;
        minin[4]=temp.payday;
        minin[5]=temp.prev;

        maxin[1]=temp.age;
        maxin[2]=temp.duration;
        maxin[3]=temp.camp;
        maxin[4]=temp.payday;
        maxin[5]=temp.prev;


        mind[1]=temp.x;
        mind[1]=temp.y;
        mind[1]=temp.z;
        mind[1]=temp.u;
        mind[1]=temp.v;

        maxd[1]=temp.x;
        maxd[1]=temp.y;
        maxd[1]=temp.z;
        maxd[1]=temp.u;
        maxd[1]=temp.v;

    }

    public void update(pureinfo temp)
    {

        allint.get(1).add(temp.age);
        allint.get(2).add(temp.duration);
        allint.get(3).add(temp.camp);
        allint.get(4).add(temp.payday);
        allint.get(5).add(temp.prev);



        minin[1]=Math.min(minin[1],temp.age);
        minin[2]=Math.min(minin[2],temp.duration);
        minin[3]=Math.min(minin[3],temp.camp);
        minin[4]=Math.min(minin[4],temp.payday);
        minin[5]=Math.min(minin[5],temp.prev);

        maxin[1]=Math.max(maxin[1],temp.age);
        maxin[2]=Math.max(maxin[2],temp.duration);
        maxin[3]=Math.max(maxin[3],temp.camp);
        maxin[4]=Math.max(maxin[4],temp.payday);
        maxin[5]=Math.max(maxin[5],temp.prev);

        alldouble.get(1).add(temp.x);
        alldouble.get(2).add(temp.y);
        alldouble.get(3).add(temp.z);
        alldouble.get(4).add(temp.u);
        alldouble.get(5).add(temp.v);


        mind[1]=Math.min(mind[1],temp.x);
        mind[2]=Math.min(mind[2],temp.y);
        mind[3]=Math.min(mind[3],temp.z);
        mind[4]=Math.min(mind[4],temp.u);
        mind[5]=Math.min(mind[5],temp.v);

        maxd[1]=Math.max(maxd[1],temp.x);
        maxd[2]=Math.max(maxd[2],temp.y);
        maxd[3]=Math.max(maxd[3],temp.z);
        maxd[4]=Math.max(maxd[4],temp.u);
        maxd[5]=Math.max(maxd[5],temp.v);

        val.get(1).add(temp.job);
        val.get(2).add(temp.marital);
        val.get(3).add(temp.edu);
        val.get(4).add(temp.def);
        val.get(5).add(temp.housing);
        val.get(6).add(temp.loan);
        val.get(7).add(temp.contact);
        val.get(8).add(temp.month);
        val.get(9).add(temp.day);
        val.get(10).add(temp.pout);




    }

    public void gameover()
    {

        for(int i=1;i<=5;i++)
            branches[i]=2;
        //System.out.println("Random number: "+Math.random());
        //System.out.println("Random number: "+Math.random());
        //System.out.println("Random number: "+Math.random());
        for(int i=6;i<=15;i++)
        {
            branches[i]=val.get(i-5).size();
            //System.out.println(branches[i]);
        }
        for(int i=16;i<=20;i++)
            branches[i]=2;

        for(int i=1;i<=5;i++)
            Collections.sort(alldouble.get(i));
        for(int i=1;i<=5;i++)
            Collections.sort(allint.get(i));

        for(int i=1;i<=10;i++)
            to.get(i).addAll(val.get(i));


        for(int i=1;i<=5;i++)
        {
            int sz=allint.get(i).size();
            int pos=sz/2;
            if(sz%pos==1)
                pos++;
            intdivider[i]=allint.get(i).get(pos);
        }

        for(int i=1;i<=5;i++)
        {
            int sz=alldouble.get(i).size();
            int pos=sz/2;
            if(sz%pos==1)
                pos++;
            doubledivider[i]=alldouble.get(i).get(pos);
        }
    }

    ulti proc(pureinfo temp)
    {
        ulti p=new ulti();

        /*

    public int age;
    public int duration;
    public int camp;
    public int payday;
    public int prev;
    public String job;
    public String marital;
    public String edu;
    public String def;
    public String housing;
    public String loan;
    public String contact;
    public String month;
    public String day;
    String pout;
    double x;
    double y;
    double z;
    double u;
    double v;
         */

        p.attributes[1]=ina(temp.age,1);
        p.attributes[2]=ina(temp.duration,2);
        p.attributes[3]=ina(temp.camp,3);
        p.attributes[4]=ina(temp.payday,4);
        p.attributes[5]=ina(temp.prev,5);

        p.attributes[6]=sna(temp.job,1);
        p.attributes[7]=sna(temp.marital,2);
        p.attributes[8]=sna(temp.edu,3);
        p.attributes[9]=sna(temp.def,4);
        p.attributes[10]=sna(temp.housing,5);
        p.attributes[11]=sna(temp.loan,6);
        p.attributes[12]=sna(temp.contact,7);
        p.attributes[13]=sna(temp.month,8);
        p.attributes[14]=sna(temp.day,9);
        p.attributes[15]=sna(temp.pout,10);

        p.attributes[16]=dna(temp.x,1);
        p.attributes[17]=dna(temp.y,2);
        p.attributes[18]=dna(temp.z,3);
        p.attributes[19]=dna(temp.u,4);
        p.attributes[20]=dna(temp.v,5);

        if(temp.ans.equals("no"))
            p.ans=2;
        else
            p.ans=1;

        return p;

    }

    public int ina(int p,int index)
    {
       if(p<=intdivider[index])
           return 1;
       else return 2;
    }

    public int sna(String s,int index)
    {
        int j=0;
       for(j=0;;j++)
       {
           if(to.get(index).get(j).equals(s))
               break;
       }
       j++;
       return j;
    }

    public int dna(double s,int index)
    {
        if(s<=doubledivider[index])
            return 1;
        else return 2;
    }
}
