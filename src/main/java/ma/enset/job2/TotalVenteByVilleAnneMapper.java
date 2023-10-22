package ma.enset.job2;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
public class TotalVenteByVilleAnneMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] columns = value.toString().split(" ");
        if (columns.length == 4) {
            String date = columns[0];
            String[] y_m_d = date.split("-");
            String annee = y_m_d[0];
            String ville = columns[1];
            double prix = Double.parseDouble(columns[3]);
            context.write(new Text(ville + "," + annee), new DoubleWritable(prix));
        }
    }
}