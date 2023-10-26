package ma.enset.job2Logs;


import ma.enset.job2Logs.RequestCountMapper;
import ma.enset.job2Logs.RequestCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class RequestCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Request Count");

        // Mapper & Reducer Classes
        job.setMapperClass(RequestCountMapper.class);
        job.setReducerClass(RequestCountReducer.class);

        // Type de sortie du Mapper
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Type de sortie du Job
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Type de fichier d'entrée
        job.setInputFormatClass(TextInputFormat.class);

        // Chemin des fichiers d'entrée/sortie
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}