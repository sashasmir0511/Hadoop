package bdtc.lab1;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Pattern;


public class HW1Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String  line = value.toString();
        int     X = 0;
        int     Y = 0;
        String  result = null;

        if (Pattern.matches("^\\d{1,3} \\d{1,3} \\d{5} \\d{8,10}$",line)) {
            System.out.println("true");
            String[] ml = line.split(" ");
            X = Integer.parseInt(ml[0]);
            Y = Integer.parseInt(ml[1]);
            System.out.println("X " + X);
            System.out.println("Y " + Y);
            if (X >= 0 && Y>= 0 && X <= 300 && Y <= 50){
                result = "bot";
            } else if (Y>= 50 && Y <= 150) {
                result = "body";
            } else if (Y>= 150 && Y <= 200) {
                result = "top";
            }
            word.set(result);
            context.write(word, one);
        }
        else {
            context.getCounter(CounterType.MALFORMED).increment(1);
        }
    }
}
