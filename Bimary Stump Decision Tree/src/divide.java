
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class divide {
    public static ArrayList<pureinfo> purelist=new ArrayList<>();
    public static ArrayList<pureinfo> xx=new ArrayList<>();
    public static ArrayList<pureinfo> yy=new ArrayList<>();
    public static ArrayList<ulti> aclist=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // open file input stream
        BufferedReader reader = new BufferedReader(new FileReader("all.csv"));

        // read file line by line
        String line = null;
        Scanner scanner = null;
        int index = 0;
        //List<Employee> empList = new ArrayList<>();
        int j=0;
        int a=0;
        int b=0;
        while ((line = reader.readLine()) != null) {

            //if(j==5)
               // break;
            //Employee emp = new Employee();
            pureinfo p=new pureinfo();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            index=0;
            while (scanner.hasNext()) {
                String data = scanner.next();
                if(data.charAt(0)=='"')
                    data=data.substring(1,data.length()-1);

                p.lst.add(data);



                /*if (index == 0)
                    emp.setId(Integer.parseInt(data));
                else if (index == 1)
                    emp.setName(data);
                else if (index == 2)
                    emp.setRole(data);
                else if (index == 3)
                    emp.setSalary(data);
                else
                    System.out.println("invalid data::" + data);*/
                //System.out.println(data+" "+j);
                data="";

                index++;

            }
            //System.out.println(index);
            if(j==0)
            {
                j++;
                continue;
            }

            //System.out.println(p.lst.size());


            p.fun();
            if(p.ans.equals("no"))
            {
                yy.add(p);
            }
            else
            {
                xx.add(p);
            }


            //System.out.println(" ");
            index = 0;
            //empList.add(emp);
            j++;
        }

        while (yy.size()>xx.size())
        {
            int randomNum = ThreadLocalRandom.current().nextInt(0, yy.size());
            yy.remove(randomNum);
        }

        for(int i=0;i<xx.size();i++)
            purelist.add(xx.get(i));
        for(int i=0;i<yy.size();i++)
            purelist.add(yy.get(i));

        Collections.shuffle(purelist);

        //close reader
        reader.close();
        //System.out.println(purelist.size());

        allinfo demi=new allinfo();
        demi.init(purelist.get(0));
        for(int i=0;i<purelist.size();i++)
            demi.update(purelist.get(i));
        demi.gameover();
        for(int i=0;i<purelist.size();i++)
            aclist.add(demi.proc(purelist.get(i)));


        for(int i=30000;i<30100;i++)
        {
            //for(int a=1;a<=20;a++)
                //System.out.print(aclist.get(i).attributes[a]+" ");
           // System.out.println();

        }

        Scanner sc=new Scanner(System.in);

        //System.out.println("Enter your rollno");
        //int rollno=sc.nextInt();

       /////input
        int k=5;
        int tot=30;
        ArrayList<ulti> q=new ArrayList<>();
        for(int i=0;i<aclist.size();i++)
            q.add(aclist.get(i));
        int turn=q.size()/k;
        double[] fscore=new double[k+1];


        for(int folds=1;folds<=k;folds++)
        {
            ArrayList<ulti> train=new ArrayList<>();
            ArrayList<ulti> test=new ArrayList<>();
            int i;
            for(i=0;i+turn<q.size();i++)
                train.add(q.get(i));
            for(;i<q.size();i++)
                test.add(q.get(i));

            double[] weight=new double[train.size()+1];
            double[] z=new double[tot+1];
            ArrayList<stump> decision=new ArrayList<>();
            for(int p=0;p<=train.size();p++)
            {
                weight[p]=1/((double)train.size());
                //System.out.println("weight "+weight[p]);
            }

            ArrayList<stump> allstumps =new ArrayList<>();
            stump tem=new stump(1);
            allstumps.add(tem);
            decider d=new decider();
            for(int t=1;t<=tot;t++)
            {
                 stump st=d.calc(train,weight,demi);
                 //System.out.println(st.index);

                 double err=0;
                 for(int c=0;c<train.size();c++)
                 {
                     //System.out.println("yooo "+st.index);
                     if(st.calc(train.get(c))!=train.get(c).ans)
                         err=err+weight[c];
                 }

                 if(err>=.5)
                 {
                     t--;
                     continue;
                 }

                for(int c=0;c<train.size();c++)
                {
                    if(st.calc(train.get(c))==train.get(c).ans)
                        weight[c]=weight[c]*(err/(1-err));
                }

                double tw=0;

                for (int c=0;c<train.size();c++)
                {
                      tw+=weight[c];
                }
                for (int c=0;c<train.size();c++)
                {
                    weight[c]=weight[c]/tw;
                }

                double lt=(1-err)/err;
                lt=Math.log10(lt);
                z[t]=lt;
                allstumps.add(st);

            }




              double recall,precision;
              double tp=0,pcon=0,con=0;
            for(int l=0;l<test.size();l++)
            {
                double pans;
                double w1=0,w2=0;
                for(int p=1;p<allstumps.size();p++)
                {
                    int tt=allstumps.get(p).calc(test.get(l));

                    if(tt==1)
                        w1+=z[p];
                    else
                        w2+=z[p];
                }
                if(w1>=w2)
                {
                    pans=1;
                    //System.out.println("olaola");
                }
                else
                    pans=2;

                int acans=test.get(l).ans;
                //double recall,precision;
                //int tp=0,pcon=0,con=0;
                if(acans==1)
                {
                    con++;
                    if(pans==1)
                        tp++;
                }
                if(pans==1)
                    pcon++;
            }
            //System.out.println(tp+"  "+con+"  "+pcon);

            for(int ii=1;ii<=turn;ii++)
            {
                ulti temp=q.remove(0);
                q.add(temp);

            }

            if(pcon==0)
            {
                folds--;
                continue;
            }

            recall=tp/con;
            precision=tp/pcon;




            double f1=(2*recall*precision)/(precision+recall);


             fscore[folds]=f1;





        }
         double tt=0;
        for(int i=1;i<=k;i++)
        {
            System.out.println(fscore[i]);
            tt+=fscore[i];
        }


        System.out.println(tt/k);

    }

}
