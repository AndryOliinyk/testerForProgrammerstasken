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

        //magnitude of the rows that will be generated
        int count  = random_int(100, 1000);



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

        int services, variations, questions, categories, sub_cat, percent, timeMin, timeMax ;
        float percentGeneration;
        char firstMainChar, secondMainChar, fResptype, sResptype;
        String fdateRespC, sdateRespC, fStartSearchDate, sStartSearchDate, fEndSearchDate , sEndSearchDate;


        //Common, frequently used and customizable options for quick code adaptation
        services = 10;
        variations = 3;
        questions = 10;
        categories = 20;
        sub_cat = 5;
        percent = 80; // percent of  'C-data' type for this method
        percentGeneration = 0.5f; //the central value from which the other percentages oscillate

        // range time in minutes for waiting time.
        timeMin = 30;
        timeMax = 200;

        // C (waiting time line) or D (query)
        firstMainChar = 'C';
        secondMainChar = 'D';

        //Response type ‘P’ (first answer) or ‘N’ (next answer).
        fResptype = 'P';
        sResptype = 'N';
        //Generate random date for 'C-type' data
        fdateRespC = "01.03.2012";
        sdateRespC = "01.10.2019";

        //Generate random date or date range for 'C-type' data
        fStartSearchDate = "01.03.2012";
        sStartSearchDate = "01.10.2019";
        fEndSearchDate = "01.03.2017";
        sEndSearchDate = "01.10.2019";

        char type = randomr(firstMainChar, secondMainChar, percent);
        // C (waiting time line)
        if(type == firstMainChar ){

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
            result = result.append( " " + randomr(fResptype,sResptype,  percent));
           try {

               result = result.append(" " +  randomDate(fdateRespC, sdateRespC));
           }
            catch (ParseException ex){ex.printStackTrace();}
            result = result.append( " " + random_int(timeMin, timeMax));
        }
        //  D (query)
        if(type == secondMainChar ){

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
            result = result.append( " " + randomr(fResptype,sResptype,  percent));
            try {
                if(Math.random()>=percentGeneration-0.1)
                result = result.append(" " +  randomDate(fStartSearchDate , sStartSearchDate) + "-"+ randomDate(fEndSearchDate,sEndSearchDate ));
               else  result = result.append(" " +  randomDate(fStartSearchDate, sStartSearchDate));
            }
            catch (ParseException ex){ex.printStackTrace();}

        }
        return result;
    }


    //Generates a value within the specified parameters
    private static int random_int(int Min, int Max){
        return (int) (Math.random()*(Max+1-Min))+Min;
    }

      //Allows you to get one of two input char with the ability to specify the percentage of generation
    private static char randomr(char first, char second, int percent){
        if(Math.random()<= (float) percent/100) return first;
        else return second;
    }

    // generates a random date between two string values with standard pattern DD.MM.YYYY
    private static String randomDate(String startDate, String endDate) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    Date d0 = date.parse(startDate);
    Date d1 = date.parse(endDate);
    long millisD0 = d0.getTime();
    long millisD1 = d1.getTime();
    Date getDate = new Date((long)((Math.random()*(millisD1+1-millisD0))+millisD0));

        return (date.format(getDate));

    }

            // Method for save data only in txt file

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
