package ma.enset.job2Logs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
public class RequestCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int totalRequests = 0;
            int successfulRequests = 0;

            for (IntWritable value : values) {
                totalRequests++;
                if (value.get() == 1) {
                    successfulRequests++;
                }
            }

            context.write(key, new IntWritable(totalRequests));
            context.write(new Text(key + " => 200"), new IntWritable(successfulRequests));
        }
}
