package ma.enset.job1Logs;

import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RequestCountMapper extends Mapper<LongWritable, Text,LongWritable, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, IntWritable>.Context context) throws IOException, InterruptedException {
        String[] parts = value.toString().split("--\\[|\\]\"|\"");
        String ipAddress = parts[0].trim();
        String timestamp = parts[1].trim();
        String requestAndProtocol = parts[2].trim();
        long statusCode = Long.parseLong(parts[3].trim());
        context.write(new LongWritable(statusCode),new IntWritable(1));

    }
}
