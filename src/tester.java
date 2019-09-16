import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class tester {

    public static void main(String[] arms)
    {
        StringBuilder sb = new StringBuilder();


        int count  = random_int(100, 100000);

        System.out.println(count);

        StringBuilder[] arr  =new StringBuilder[count];

        sb = sb.append((count-1));

        for( int i =1; i<count; i++) {
            sb.append(System.getProperty("line.separator"));
            sb.append(generate());
        }

        saveFile(sb);
    }


    public static StringBuilder generate(){
        StringBuilder result= new StringBuilder();

        int services, variations, questions, categories, sub_cat, percent, scoreMin, scoreMax ;
        float percentGeneration;

        services = 10;
        variations = 3;
        questions = 10;
        categories = 20;
        sub_cat = 5;
        percent = 80;
        percentGeneration = 0.5f;
        scoreMin = 30;
        scoreMax = 70;




        char type = randomr('C', 'D', percent);
        // if file generate input date starting with C
        if(type == 'C' ){

            result = result.append(type + " " + random_int(1, services));

           if(Math.random()>percentGeneration-0.2){

               result = result.append( "." + random_int(1, variations));
           }

            result = result.append(" " + random_int(1, questions));

           if(Math.random()>=percentGeneration - 0.1)
            {
                result = result.append( "." + random_int(1, categories));

                if(Math.random()>=percentGeneration-0.1)
                {
                    result = result.append( "." + random_int(1, sub_cat));
                }
            }
            result = result.append( " " + randomr('P','N',  percent));
           try {

               result = result.append(" " +  randomDate("01.03.2012", "01.10.2019"));
           }
            catch (ParseException ex){ex.printStackTrace();}
            result = result.append( " " + random_int(scoreMin, scoreMax));
        }

        if(type == 'D' ){

            result = result.append(type + " " + random_int(1, services));

            if(Math.random()>percentGeneration+0.1){

                result = result.append( "." + random_int(1, variations));
            }
            else result = result.append( " " + "*");

            if(!result.toString().contains("*"))

            result = result.append(" " + random_int(1, questions));

            if(!result.toString().contains("*") & Math.random()>=percentGeneration+0.2)
            {
                result = result.append( "." + random_int(1, categories));

                if(Math.random()>=percentGeneration-0.1)
                {
                    result = result.append( "." + random_int(1, sub_cat));
                }
            }
            result = result.append( " " + randomr('P','N',  percent));
            try {
                if(Math.random()>=percentGeneration-0.1)
                result = result.append(" " +  randomDate("01.03.2012", "01.03.2017") + "-"+ randomDate("01.03.2017", "01.10.2019"));
               else  result = result.append(" " +  randomDate("01.03.2012", "01.10.2019"));
            }
            catch (ParseException ex){ex.printStackTrace();}

        }
        return result;
    }

    private static int random_int(int Min, int Max){
        return (int) (Math.random()*(Max+1-Min))+Min;
    }

    private static char randomr(char first, char second, int percent){
        if(Math.random()<= (float) percent/100) return first;
        else return second;
    }

    private static String randomDate(String startDate, String endDate) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    Date d0 = date.parse(startDate);
    Date d1 = date.parse(endDate);
    long millisD0 = d0.getTime();
    long millisD1 = d1.getTime();
    Date getDate = new Date((long)((Math.random()*(millisD1+1-millisD0))+millisD0));

        return (date.format(getDate));

    }


    public static void saveFile(StringBuilder arr) {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("D://"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".txt");

                fw.write(arr.toString());
                fw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
    
}
