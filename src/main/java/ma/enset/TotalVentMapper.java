package ma.enset;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
public class TotalVentMapper extends Mapper<LongWritable,Text,Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        String[] columns = value.toString().split(" ");
        if (columns.length == 4){
            context.write(new Text(columns[1]),new DoubleWritable(
                    Double.parseDouble(columns[3])
            ));
        }
    }
}
