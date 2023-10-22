package ma.enset.job1;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.Iterator;
public class TotalVenteReducer extends Reducer<Text, DoubleWritable,Text,DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        Iterator<DoubleWritable> iterator = values.iterator();
        double total =0;
        while (iterator.hasNext()){
            total+=iterator.next().get();
        }
        context.write(key,new DoubleWritable(total));
    }
}