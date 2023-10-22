package ma.enset.job2;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
public class TotalventeByVilleAnneeReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double total = 0;
        for (DoubleWritable value : values) {
            total += value.get();
        }
        context.write(key, new DoubleWritable(total));
    }
}