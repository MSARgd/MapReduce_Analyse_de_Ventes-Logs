package ma.enset.job2;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class TotalventDriver2 {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job2 = Job.getInstance(conf, "VenetParVilleAnnee");

        job2.setMapperClass(TotalVenteByVilleAnneMapper.class);
        job2.setReducerClass(TotalventeByVilleAnneeReducer.class);
        //Type de Sortie de Maper
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(DoubleWritable.class);
        // Type de Sortie de Job
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(DoubleWritable.class);

        // Input Format
        job2.setInputFormatClass(TextInputFormat.class);

        // path Input/output File
        FileInputFormat.addInputPath(job2, new Path(args[0]));
        FileOutputFormat.setOutputPath(job2, new Path(args[1]));


        job2.waitForCompletion(true);
    }
}