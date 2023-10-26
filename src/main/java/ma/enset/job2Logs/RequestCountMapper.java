package ma.enset.job2Logs;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;



public class RequestCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String[] parts = value.toString().split("--\\[|]\"|\"");
        String ipAddress = parts[0].trim();
        int statusCode = Integer.parseInt(parts[3].trim());

        if (statusCode == 200) {
            context.write(new Text(ipAddress), new IntWritable(1));
        } else {
            context.write(new Text(ipAddress), new IntWritable(0));
        }
    }
}
