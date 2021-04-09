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
    private final static IntWritable X = new IntWritable(1);
    private final static IntWritable Y = new IntWritable(1);
    private final static IntWritable X_1 = new IntWritable(1);
    private final static IntWritable Y_1 = new IntWritable(1);
    private final static IntWritable X_2 = new IntWritable(1);
    private final static IntWritable Y_2 = new IntWritable(1);
    private final static IntWritable one = new IntWritable(1);
    private static final String[] values_key =
            {"0:0:300:50:bot", "0:50:300:150:body", "0:150:300:200:top"};
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

    /*@Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        if (Pattern.matches("^[0-9]{3} [0-9]{3} [0-9]{3} [0-9]{10}$",line)) {
            String[] ml = line.split(" ");
            String[] key_bot = values_key[0].split(":");
            String[] key_body = values_key[1].split(":");
            String[] key_top = values_key[2].split(":");

            if (!ml[0].isEmpty() && !ml[1].isEmpty()) {
                X.set(Integer.parseInt(ml[0]));
                Y.set(Integer.parseInt(ml[1]));
                X_1.set(Integer.parseInt(key_bot[0]));
                X_2.set(Integer.parseInt(key_bot[2]));
                Y_1.set(Integer.parseInt(key_bot[1]));
                Y_2.set(Integer.parseInt(key_bot[3]));

                if (X.get() >= X_1.get() && X.get() < X_2.get()
                        && Y.get() >= Y_1.get() && Y.get() < Y_2.get()) {
                    word.set(ml[2]);
                    context.write(word, new IntWritable(0));
                    return;
                }
                X_1.set(Integer.parseInt(key_body[0]));
                X_2.set(Integer.parseInt(key_body[2]));
                Y_1.set(Integer.parseInt(key_body[1]));
                Y_2.set(Integer.parseInt(key_body[3]));

                if (X.get() >= X_1.get() && X.get() < X_2.get()
                        && Y.get() >= Y_1.get() && Y.get() < Y_2.get()) {
                    word.set(ml[2]);
                    context.write(word, new IntWritable(1));
                    return;
                }
                X_1.set(Integer.parseInt(key_top[0]));
                X_2.set(Integer.parseInt(key_top[2]));
                Y_1.set(Integer.parseInt(key_top[1]));
                Y_2.set(Integer.parseInt(key_top[3]));

                if (X.get() >= X_1.get() && X.get() < X_2.get()
                        && Y.get() >= Y_1.get() && Y.get() < Y_2.get()) {
                    word.set(ml[2]);
                    context.write(word, new IntWritable(2));
                    return;
                }
            }
        } else {
            System.out.println("Zero");
            context.getCounter(CounterType.MALFORMED).increment(1);
        }
    }*/
}
