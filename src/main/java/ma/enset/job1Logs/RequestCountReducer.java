package ma.enset.job1Logs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class RequestCountReducer extends Reducer<LongWritable, IntWritable,LongWritable,LongWritable> {
    @Override
    protected void reduce(LongWritable key, Iterable<IntWritable> values, Reducer<LongWritable, IntWritable, LongWritable, LongWritable>.Context context) throws IOException, InterruptedException {
        Iterator<IntWritable> iterator = values.iterator();
        int sum=0;
        while (iterator.hasNext()){
            sum+=iterator.next().get();
        }
        context.write(new LongWritable(key.get()),new LongWritable(sum));
    }
}
